package discordpluggins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.event.EventListener;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;


public class BaseEvent implements EventListener
{
    public DiscordAPI api = StartAPI.api;
    public String name;
    public String command;
    public String description;
    public UserChatEvent e;
    public ArrayList<BaseEvent> commandsEquals = new ArrayList<>();
    public ArrayList<BaseEvent> commandsStarts = new ArrayList<>();
    private boolean initiated = false;
    public ArrayList<String> magic8List = new ArrayList<>();

    public BaseEvent(String name, String command, String description)
    {
        this.api = StartAPI.api;
        name = this.name;
        command = this.command;
        description = this.description;
    }
    
    public void initializeEvents()
    {
        HelpEvent he = new HelpEvent("Help Command", "!help", "Lists all available commands.");
        Magic8Event m8e = new Magic8Event("Magic 8 Ball Command", "!magic8", "Ask magic 8 ball a question, after the command. Will generate random reply.");
        
        commandsEquals.add(new CoinEvent("Flip a Coin Command", "!coin", "Flips a coin randomly, retrun heads or tails (or side if you're a lucker)."));
        commandsEquals.add(new ServerInfoEvent("Server Info Command", "!serverinfo", "Posts various data about the current server (ID, name, etc.)."));
        commandsEquals.add(new GoogleMapRandomEvent("Random Google Map Coord Command", "!randgmap", "Generates a random google maps link."));
        commandsEquals.add(he);

        commandsStarts.add(new DiceEvent("Roll a Die Command", "!dice", "Roll dice of a specified sided-ness (first variable) for specific amount of times (second variable, max 10)."));
        commandsStarts.add(new InfoEvent("User Info Command", "!info", "Enter name of user after the command to get their info (ID, name, etc.)."));
        commandsStarts.add(new SetBotName("Set Bot's Name Command", "!nameme", "Set's this bot's name to the entered text after the command."));
        commandsStarts.add(new SetBotAvatar("Set Bot's Avatar Command", "!avatarme" , "Put an image link after the command to make it the bot's avatar."));
        commandsStarts.add(new RandomImgurEvent("Random Imgur Link Command", "!randomimgur", "Posts specified amount of random Imgur links (max 10)."));
        commandsStarts.add(new ChoiceEvent("Random Choice Command", "!choice", "Put choices divided by semi-colons, the bot will pick one at random."));
        commandsStarts.add(new RandomUnicodeEvent("Random Unicode Chars Command", "!unicode", "Prints specified amount of random Unicode chars (max 10)."));
        commandsStarts.add(new RPSEvent("Rock, Paper, Scissors! Command", "!rps", "Enter rock, paper or scissors to challenge the bot."));
        commandsStarts.add(new GoogleMapEvent("Google Map Coord Command", "!gmap", "Generates a google map link with a specified x and y coord."));
        commandsStarts.add(new GoogleTranslateEvent("Google Translate Command", "!gtran", "Generates a google translate link with: [your lang], [tran lang], [text]."));
        commandsStarts.add(m8e);
        
        he.setLists(commandsEquals, commandsStarts);
        
        File inFileMagic8 = new File("magic8.txt");

        try
        {
            Scanner scanMagic8 = new Scanner(inFileMagic8);
            
            while (scanMagic8.hasNextLine())
            {
                magic8List.add(scanMagic8.nextLine());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.err.println(ex.toString());
        }

        m8e.setFileData(magic8List);
    }
    
    public void onChat(UserChatEvent e) throws Exception
    {
        while (!initiated)
        {
            this.initializeEvents();
            for (BaseEvent b: commandsEquals)
            {
                System.err.println("Created Command (Equal): " + b.name);
            }
            for (BaseEvent b: commandsStarts)
            {
                System.err.println("Created Command (Starting): " + b.name);
            }
            initiated = true;
        }
        
        String m = e.getMsg().toString();
        
        if (!e.getGroup().getId().equals("147204256334610433"))
        {
            if (e.getMsg().toString().startsWith("!"))
            {
                e.getGroup().sendMessage(new MessageBuilder()
                .addBold("WRONG CHAT")
                .build(api));
            }
        }
        else if (e.getGroup().getId().equals("147204256334610433") && m.startsWith("?"))
        {
            ExplainCommand explain = new ExplainCommand();
            explain.setLists(commandsEquals, commandsStarts);
            explain.explain(e, m);
        }
        else if (e.getGroup().getId().equals("147204256334610433") && (m.startsWith("!") || m.startsWith("/")))
        {
            boolean isCommand = false;
            for (int i = 0; i < this.commandsEquals.size(); i++)
            {
                if (m.equalsIgnoreCase(commandsEquals.get(i).command))
                {
                    commandsEquals.get(i).doCommand(e);
                    System.out.println("Doing command #" + (i + 1) + " from commands equaling.");
                    isCommand = true;
                }
            }
            try
            {
                for (int j = 0; j < this.commandsStarts.size(); j++)
                {
                    if (m.startsWith(commandsStarts.get(j).command))
                    {
                        commandsStarts.get(j).doCommand(e);
                        System.out.println("Doing command #" + (j + 1) + " from commands starting at.");
                        isCommand = true;
                    }
                }
            }
            catch (Exception ex)
            {
                System.err.println(ex.toString());
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("Command entered wrong, please use '!help' for more info.")
                .build(api));
                isCommand = true;
            }
            if (!isCommand)
            {
                System.err.println("Error. Not a proper command.");
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("Not a command. Please use '!help' to look up commands, thanks.")
                .build(api));
            }
        }
    }
    
    public void doCommand(UserChatEvent e)
    {}
    
    public void sleep(int time)
    {
        try
        {
            System.err.println("Bot sleeping for " + time + " ms");
            Thread.sleep(time);
        }
        catch (InterruptedException ex)
        {
            System.err.println("Oh no!: " + ex.toString());
        }
    }
}