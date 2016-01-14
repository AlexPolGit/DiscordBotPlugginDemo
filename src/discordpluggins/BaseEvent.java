package discordpluggins;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.Role;
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
    public ArrayList<String> funnyMemesList = new ArrayList<>();
    public ArrayList<String> emotesList = new ArrayList<>();
    public ArrayList<String> emojisList = new ArrayList<>();
    public ArrayList<String> magic8List = new ArrayList<>();
    public ArrayList<String> goodshitList = new ArrayList<>();

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
        FunnyMemeEvent fme = new FunnyMemeEvent("Funny Meme Command", "!funnymeme", "Posts a random funny meme from a HUGE list.");
        RandomEmoteEvent ree = new RandomEmoteEvent("Random Emote Command", "!emote", "Posts a random Twitch-like emote for your pleasure.");
        UnicodeEmojiEvent uee = new UnicodeEmojiEvent("Random Emoji Chars Command", "!emoji", "Prints specified amount of random Unicode emojis (max 10).");
        Magic8Event m8e = new Magic8Event("Magic 8 Ball Command", "!magic8", "Ask magic 8 ball a question, after the command. Will generate random reply.");
        GoodShitEvent gse = new GoodShitEvent("That's Some Good Shit Command", "!goodshit", "Rolls for a random good shit meme. 👌");
        
        commandsEquals.add(new CoinEvent("Flip a Coin Command", "!coin", "Flips a coin randomly, retrun heads or tails (or side if you're a lucker)."));
        commandsEquals.add(new ServerInfoEvent("Server Info Command", "!serverinfo", "Posts various data about the current server (ID, name, etc.)."));
        commandsEquals.add(new GetOutBot("Kick Bot Command", "!getout", "Tells the stupid bot to take a time out. :angry:"));
        commandsEquals.add(new GoogleMapRandomEvent("Random Google Map Coord Command", "!randgmap", "Generates a random google maps link."));
        commandsEquals.add(he);
        commandsEquals.add(fme);
        commandsEquals.add(ree);
        commandsEquals.add(gse);
        
        commandsStarts.add(new DiceEvent("Roll a Die Command", "!dice", "Roll dice of a specified sided-ness (first variable) for specific amount of times (second variable, max 10)."));
        commandsStarts.add(new InfoEvent("User Info Command", "!info", "Enter name of user after the command to get their info (ID, name, etc.)."));
        commandsStarts.add(new SetBotName("Set Bot's Name Command", "!nameme", "Set's this bot's name to the entered text after the command."));
        commandsStarts.add(new SetBotAvatar("Set Bot's Avatar Command", "!avatarme" , "Put an image link after the command to make it the bot's avatar."));
        commandsStarts.add(new RandomImgurEvent("Random Imgur Link Command", "!randomimgur", "Posts specified amount of random Imgur links (max 10)."));
        commandsStarts.add(new SubredditEvent("Subreddit Shortcut Command", "/r/", "Completes the entire reddit URL; put /r/ and name of subreddit."));
        commandsStarts.add(new ChoiceEvent("Random Choice Command", "!choice", "Put choices divided by semi-colons, the bot will pick one at random."));
        commandsStarts.add(new JPEGCompressEvent("Compress a JPEG Command", "!jpeg", "`NOT CURRENTLY FUNCTIONING.`"));
        commandsStarts.add(new RandomUnicodeEvent("Random Unicode Chars Command", "!unicode", "Prints specified amount of random Unicode chars (max 10)."));
        commandsStarts.add(new UnicodeRangeEvent("Unicode Chars from Range", "!unispam", "Prints the unicode characters from a start value to end value range."));
        commandsStarts.add(new RPSEvent("Rock, Paper, Scissors! Command", "!rps", "Enter rock, paper or scissors to challenge the bot."));
        commandsStarts.add(new GoogleMapEvent("Google Map Coord Command", "!gmap", "Generates a google map link with a specified x and y coord."));
        commandsStarts.add(new GoogleTranslateEvent("Google Translate Command", "!gtran", "Generates a google translate link with: [your lang], [tran lang], [text]."));
        commandsStarts.add(new SpaceTypeEvent("S P A C E T Y P E Command", "!spacetype", "E N T E R T E X T T O L O O K L I K E T H I S."));
        commandsStarts.add(new HVSpaceTypeEvent("S P A C E T Y P E H & V Command", "!hvspacetype", "E N T E R T E X T T O L O O K L I K E T H I S, plus vertically."));
        commandsStarts.add(m8e);
        commandsStarts.add(uee);
        
        he.setLists(commandsEquals, commandsStarts);
        
        File inFileMemes = new File("funnymemes.txt");
        File inFileEmotes = new File("emotes.txt");
        File inFileEmojis = new File("emojis.txt");
        File inFileMagic8 = new File("magic8.txt");
        File inFileGoodShit = new File("goodshit.txt");
        try
        {
            Scanner scanMemes = new Scanner(inFileMemes);
            Scanner scanEmotes = new Scanner(inFileEmotes);
            Scanner scanEmojis = new Scanner(inFileEmojis);
            Scanner scanMagic8 = new Scanner(inFileMagic8);
            Scanner scanGoodShit = new Scanner(inFileGoodShit);
            while (scanMemes.hasNextLine())
            {
                funnyMemesList.add(scanMemes.nextLine());
            }
            while (scanEmotes.hasNextLine())
            {
                emotesList.add(scanEmotes.nextLine());
            }
            while (scanEmojis.hasNextLine())
            {
                emojisList.add(scanEmojis.nextLine());
            }
            while (scanMagic8.hasNextLine())
            {
                magic8List.add(scanMagic8.nextLine());
            }
            while (scanGoodShit.hasNextLine())
            {
                goodshitList.add(scanGoodShit.nextLine());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.err.println(ex.toString());
        }
        
        fme.setFileData(funnyMemesList);
        ree.setFileData(emotesList);
        uee.setFileData(emojisList);
        m8e.setFileData(magic8List);
        gse.setFileData(goodshitList);
        
        //e.getGroup().getGroupUserById("🔥 Alex 🔥").setRoles(e.getServer().getGroupUserById("Robert").getRoles());
    }
    
    public void onChat(UserChatEvent e) throws Exception //idklol
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
        
        if (!e.getGroup().getId().equals("128014479622406144"))
        {    
            if (e.getMsg().toString().startsWith("!"))
            {
                e.getGroup().sendMessage(new MessageBuilder()
                .addBold(":laughing:   WRONG CHAT LOL   :laughing:")
                .build(api));
            }
        }
        else if (e.getGroup().getId().equals("128014479622406144") && m.startsWith("?"))
        {
            ExplainCommand explain = new ExplainCommand();
            explain.setLists(commandsEquals, commandsStarts);
            explain.explain(e, m);
        }
        else if (e.getGroup().getId().equals("128014479622406144") && (m.startsWith("!") || m.startsWith("/")))
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
    
    public void say(String something)
    {
        e.getGroup().sendMessage(new MessageBuilder() 
            .addString(something)
            .build(api));
    }
    
    public void sayItalic(String something)
    {
        e.getGroup().sendMessage(new MessageBuilder() 
            .addItalic(something)
            .build(api));
    }
    
    public void sayBold(String something)
    {
        e.getGroup().sendMessage(new MessageBuilder()
            .addBold(something)
            .build(api));
    }
    
    public void sayObject(Object something)
    {
        e.getGroup().sendMessage(new MessageBuilder()
            .addObject(something)
            .build(api));
    }
    
    public void sleep(int time)
    {
        try
        {
            System.err.println("Bot sleeping for " + time + " ms");
            Thread.sleep(time);
        }
        catch (InterruptedException ex)
        {
            System.err.println("You dun fucked up: " + ex.toString());
        }
    }
}