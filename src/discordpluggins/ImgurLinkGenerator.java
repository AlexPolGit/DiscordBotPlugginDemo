package discordpluggins;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class ImgurLinkGenerator
{
    public static String getPage() throws IOException, URISyntaxException
    {
        String createdURL = generateURL();
        URLConnection url = new URL(createdURL).openConnection();
        url.connect();
        InputStream is = url.getInputStream();
        is.close();

        if (createdURL.equals(url.getURL().toString()))
        {
            return createdURL;
        }
        else
        {
            return null;
        }
    }
    
    public static String generateURL()
    {
        Random r = new Random();
        final char ch[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                           'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                           '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String url = "http://i.imgur.com/";
        for (int i = 0; i < 5; i++)
        {
            url += ch[r.nextInt(ch.length)];
        }
        return url + ".png";
    }
}