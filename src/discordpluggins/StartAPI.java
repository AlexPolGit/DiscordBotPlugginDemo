package discordpluggins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.security.auth.login.LoginException;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.DiscordBuilder;
import me.itsghost.jdiscord.exception.BadUsernamePasswordException;
import me.itsghost.jdiscord.exception.DiscordFailedToConnectException;
import me.itsghost.jdiscord.exception.NoLoginDetailsException;

public class StartAPI
{
    public static final String sp = System.lineSeparator();
    private static final String creds[] = new String[2];
    protected static DiscordAPI api;

    public static void sleep(int time)
    {
        try
        {
            System.err.println("Bot sleeping for " + time + " ms");
            Thread.sleep(time);
        }
        catch (InterruptedException ie)
        {
            System.err.println(ie.toString());
        }
    }
    
    public static void createBot() throws LoginException
    {
        api = new DiscordBuilder(creds[0], creds[1]).build();
        try
        {
            api.login();
        }
        catch (NoLoginDetailsException | BadUsernamePasswordException | DiscordFailedToConnectException e)
        {
            System.err.println("Exception: " + e.getMessage());
        }
        BaseEvent b = new BaseEvent("Base Event", "!null", "SAMPLE EVENT SAMPLE EVENT SAMPLE EVENT");
        api.getEventManager().registerListener(b);
    }
    
    public static void killBot()
    {
        api.getAccountManager().setOnlineStatus(false);
        api.stop();
    }

    public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException
    {
        File inFile = new File("credentials.txt");
        try (Scanner scan = new Scanner(inFile))
        {
            int n = 0;
            while(scan.hasNext())
            {
                creds[n] = scan.nextLine();
                n++;
            }
            
            System.out.println("LOGGING IN WITH: ");
            System.out.println(" ✔ " + creds[0]);
            System.out.println(" ✔ " + creds[1]);
            
            createBot();
        }
        catch (FileNotFoundException ex)
        {
            System.err.println(ex);
        }
    }
}