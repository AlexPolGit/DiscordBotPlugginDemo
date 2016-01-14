package discordpluggins;

import java.io.*;
import java.net.*;
import java.util.*;
import me.itsghost.jdiscord.*;
import me.itsghost.jdiscord.event.EventListener;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;
import me.itsghost.jdiscord.message.Message;

public class BotListener implements EventListener
{

    DiscordAPI api;

    public BotListener(DiscordAPI api)
    {
        this.api = api;
    }

    public void sleep(int time)
    {
        StartAPI.sleep(time);
    }

    public void onChat(UserChatEvent e) throws MalformedURLException, IOException, URISyntaxException
    {
        Message message = e.getMsg();
        if (!e.getGroup().getId().equals("128014479622406144"))
        {
            return;
        }
        
        if (message.toString().equalsIgnoreCase("!help"))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                    .addString("```")
                    .addString("\n")
                    .addString("```")
                    .addString("\n")
                .addString("Listing available commands:\n")
                .addString("\n`!help`   Lists all available commands.")
                .addString("\n`!coin`   Flips a coin.")
                .addString("\n`!dice`   Rolls a dice.")
                .addString("\n`!magic8 [question]`   Uses a magic 8 ball.")
                .addString("\n`!randomimgur [times; (0 to 10)]`   Gives a random Imgur link.")
                .addString("\n`/r/[subreddit name]`   Gives you the link for that subreddit.")
                .addString("\n`!info [username]`   Displays user's information.")
                .addString("\n`!serverinfo`   Displays server's information.")
                    .addString("\n\n")
                    .addString("```")
                    .addString("\n")
                    .addString("```")
                .build(api));
        }
        /* :)
        if (message.toString().equalsIgnoreCase("!kickme"))
        {
            String toKick = e.getUser().getUser().getUsername();
            e.getServer().kick(toKick);
        }
        */
        else if (message.toString().equalsIgnoreCase("!coin"))
        {
            int r = (int)(Math.random()*1000+1);
            String result = "";
            if (r < 500)
            {
                result = "Heads";
            }
            else if (r > 500)
            {
                result = "Tails";
            }
            else if (r == 500)
            {
                result = "On its Side.";
            }
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("*" + result + "*")
                .build(api));
        }
        else if (message.toString().startsWith("!dice"))
        {
            String args[] = e.getMsg().getMessage().split(" ");
            int n = Integer.parseInt(args[1]);
            Random random = new Random();
            int dice = random.nextInt(n) + 1;
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("*" + dice + "*")
                .build(api));
        }
        else if (message.toString().startsWith("!magic8"))
        {
            String cases[] = {  "Yes.", "No.", "Absolutely.", "Not at all.", "A little.", "Not really.",
                                "Ask again later.", "Maybe.", "If you truly believe.", "Why not?", "YES.",
                                "NO.", "Yeah.", "At times.", "I don't feel like answering.", "That's a secret.",
                                "All day, every day.", "Not once in a million years.", "Sure.", "Nah.",
                                "Without a doubt.", "My sources say no.", "You may rely on it.", "It is decidedly so.",
                                "Don't count on it.", "9 times out of 10.", "1 time out of 10.", "Ever since the universe came into existence.",
                                "Concentrate and ask again.", "Very doubtful.", "Yoda knows. Find him in the Dagobah System: starwars.wikia.com/wiki/Dagobah ",
                                "Try harder next time.", "Why would I know?", "OH YEAH!", ":smiley:", "Nope.", "Uhuh, yeah, sure..."};
            Random random = new Random();
            String result = cases[random.nextInt(cases.length - 1)];
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("` " + result + " `")
                .build(api));
        }
        else if (message.toString().equalsIgnoreCase("!funnymeme"))
        {
            String cases[] = {  "http://i.imgur.com/eSmDjRN.png", "http://i.imgur.com/pEjVdhw.jpg", "http://i.imgur.com/Cf0fZR7.jpg",
                                "http://i.imgur.com/TmKRA4h.jpg", "http://i.imgur.com/gr2WwZw.png", "http://i.imgur.com/s0RnDJs.jpg",
                                "http://i.imgur.com/4ObdrUh.png", "http://i.imgur.com/2vKiG3V.jpg", "http://i.imgur.com/qgEwCY8.jpg",
                                "http://i.imgur.com/hwJhNIV.jpg", "http://i.imgur.com/UXgdNLm.jpg", "http://i.imgur.com/gkSWLCX.jpg",
                                "http://i.imgur.com/ySGzHjX.jpg", "http://i.imgur.com/Nn4ENex.jpg", "http://i.imgur.com/k3sIApU.jpg"};
            Random random = new Random();
            String result = cases[random.nextInt(cases.length - 1)];
            e.getGroup().sendMessage(new MessageBuilder()
                .addString(result)
                .build(api));
        }
        else if (message.toString().equalsIgnoreCase("!serverinfo"))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("ID: " + e.getServer().getId())
                .addString("\nName: " + e.getServer().getName())
                .addString("\nLocation: " + e.getServer().getLocation())
                .addString("\nCreated by: " + e.getServer().getGroupUserById(e.getServer().getCreatorId()))
                .addString("\nCurrent users: ")
                .build(api));
            
            List users = e.getServer().getConnectedClients();
            
            for (Object u : users)
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addString("✔ " + u)
                    .build(api));
            }
            
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("\nGroup Avatar: " + e.getServer().getAvatar())
                .build(api));
        }
        else if (message.toString().startsWith("!info"))
        {
            String who = e.getMsg().getMessage().substring(6);
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("ID: " + e.getServer().getGroupUserByUsername(who).getUser().getId())
                .addString("\nName: " + e.getServer().getGroupUserByUsername(who).getUser().getUsername())
                .addString("\nPlaying: " + e.getServer().getGroupUserByUsername(who).getUser().getGame())
                .addString("\nAvatar: " + e.getServer().getGroupUserByUsername(who).getUser().getAvatar())
                .build(api));
        }
        else if (message.toString().startsWith("!avatarme "))
        {
            String args[] = e.getMsg().getMessage().split(" ");
            String msg = args[1];
            InputStream input = new URL(msg).openStream();
            for (int i = 0; i < 100; i++)
                api.getAccountManager().setAvatar(input);
        }
        else if (message.toString().startsWith("!nameme "))
        {
            String args[] = e.getMsg().getMessage().split(" ");
            String msg = "";
            for (int i = 1; i < args.length; i++)
            {
                msg += (args[i] + " ");
            }
            for (int i = 0; i < 100; i++)
                api.getAccountManager().setDisplayName(msg);
        }
        else if (message.toString().equalsIgnoreCase("!getout"))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Oh.")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Ok...")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("I'll go now.")
                .build(api));
            sleep(1000);
            StartAPI.killBot();
            sleep(5000);
            //StartAPI.createBot();
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("I'm back!")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Sorry for dissapointing you...")
                .build(api));
        }
        else if (message.toString().startsWith("!countforme "))
        {
            String args[] = e.getMsg().getMessage().split(" ");
            int min = Integer.parseInt(args[1]);
            int max = Integer.parseInt(args[2]);
            Message msg = e.getGroup().sendMessage(new MessageBuilder()
                    .addString("Counting from " + min + " to " + max + ".")
                    .build(api));
            sleep(1000);
            for (int i = min; i <= max + 1; i++)
            {
                msg.editMessage(Integer.toString(i));
                sleep(500);
            }
        }
        else if (message.toString().startsWith("!randomimgur"))
        {
            String args[] = e.getMsg().getMessage().split(" ");
            int times = Integer.parseInt(args[1]);
            int i = 0;
            if (times > 10)
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addString("TOO MUCH")
                    .build(api));
            }
            else if (times < 0)
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addString("TOO LITTLE")
                    .build(api));
            }
            else
            {
                while (i <= (times - 1))
                {
                    String result = ImgurLinkGenerator.getPage();
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
        else if (message.toString().startsWith("/r/"))
        {
            String reddit = message.toString();
            reddit = reddit.substring(3);
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("https://www.reddit.com/r/" + reddit)
                .build(api));
        }
        else if (message.toString().equalsIgnoreCase("!hacksme"))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("http://www.mediafire.com/download/3hbkfbqt7wcp876/TmCHeats.com+Csgo+TriggerBOT.rar")
                .build(api));
        }           
    }
}