package discordpluggins;

import java.io.IOException;
import java.net.URISyntaxException;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class RandomImgurEvent extends BaseEvent
{
    public RandomImgurEvent(String name, String command, String description)
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
         String args[] = e.getMsg().getMessage().split(" ");
            int times = Math.abs(Integer.parseInt(args[1]));
            int i = 0;
            if (times >= 11)
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("TOO MUCH.")
                    .build(api));
            }
            else if (times == 0)
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("NOT EVEN ONCE?")
                    .build(api));
            }
            else
            {
                while (i <= (times - 1))
                {
                    String result = null;
                    try
                    {
                        result = ImgurLinkGenerator.getPage();
                    }
                    catch (IOException | URISyntaxException ex)
                    {
                        System.err.println(ex.toString());
                    }
                    System.err.println("Result: " + result);
                    System.err.println("Current i: " + i);
                    if (result != null)
                    {
                        e.getGroup().sendMessage(new MessageBuilder()
                            .addString("(" + (i + 1) + ") " + result)
                            .build(api));
                        i++;
                    }
                }
            }
    }
    
    public String getCommand()
    {
        return command;
    }
}