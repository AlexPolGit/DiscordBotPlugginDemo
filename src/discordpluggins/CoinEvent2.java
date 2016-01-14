package discordpluggins;

import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.events.message.MessageReceivedEvent;


public class CoinEvent2 extends JDABaseEvent
{
    public CoinEvent2(String name, String command, String description)
    {
        super(name, command, description);
        this.name = name;
        this.command = command;
        this.description = description;
    }

    public void onEvent(MessageReceivedEvent e)
    {
        int r = (int)(Math.random()*1000+1);
        String result = "";
        if (r < 500)
        {
            result = "Heads.";
        }
        else if (r > 500)
        {
            result = "Tails.";
        }
        else if (r == 500)
        {
            result = "On its Side.";
        }
       
        
            TextChannel channel = e.getTextChannel();
            Guild guild = channel.getGuild();
            StringBuilder builder = new StringBuilder();

            builder.append("xd");
        
    }
    
    public String getCommand()
    {
        return command;
    }
}