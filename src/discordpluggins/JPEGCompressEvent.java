package discordpluggins;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class JPEGCompressEvent extends BaseEvent
{
    public JPEGCompressEvent(String name, String command, String description)
    {
        super(name, command, description);
        this.api = super.api;
        this.name = name;
        this.command = command;
        this.description = description;
    }

    @Override
    public void doCommand(UserChatEvent e)
    {
        String toJPG = e.getMsg().getMessage().substring(6);
        
        try
        { 
            BufferedImage in = ImageIO.read(compress(toJPG));
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject(in)
            .build(api));
            
        }
        catch (IOException ex)
        {
            System.err.println(ex.toString());
        }
    }
    
    public File compress(String imageURL) throws FileNotFoundException, IOException
    {
        File compressedImage = new File("comp.jpg");
        InputStream is = new URL(imageURL).openStream();
        OutputStream os = new FileOutputStream(compressedImage);

        float quality = 0.001f;
        BufferedImage image = ImageIO.read(is);

        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
        if (!writers.hasNext())
        throw new IllegalStateException("No writers found");

        ImageWriter writer = (ImageWriter)writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(quality);

        writer.write(null, new IIOImage(image, null, null), param);

        is.close();
        os.close();
        ios.close();
        writer.dispose();

        return compressedImage;
    }
    
    public String getCommand()
    {
        return command;
    }
}