package discordpluggins;

import java.util.ArrayList;
import me.itsghost.jdiscord.DiscordAPI;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class ExplainCommand
{
    public DiscordAPI api = StartAPI.api;
    public ArrayList<BaseEvent> CE;
    public ArrayList<BaseEvent> CS;
    
    public void setLists(ArrayList<BaseEvent> e, ArrayList<BaseEvent> s)
    {
        CE = e;
        CS = s;
    }

    public void explain(UserChatEvent e, String m)
    {
        System.out.println("Explaining: " + m);
        m = m.substring(1);
        
        System.err.println("Fetched Commands Equal: " + CE);
        
        for (BaseEvent ce : CE)
        {
            if (ce.command.equalsIgnoreCase("!" + m))
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("EXPLANATION:\n")
                    .addItalic((char)34 + ce.name + (char)34 + ": " + ce.description)
                .build(api));
            }
        }
        
        System.err.println("Fetched Commands Start: " + CS);
        
        for (BaseEvent cs : CS)
        {
            if (cs.command.equalsIgnoreCase("!" + m) || cs.command.equalsIgnoreCase("/" + m))
            {
                e.getGroup().sendMessage(new MessageBuilder()
                    .addBold("EXPLANATION:\n")
                    .addItalic((char)34 + cs.name + (char)34 + ": " + cs.description)
                .build(api));
            }
        }
        
        e.getGroup().sendMessage(new MessageBuilder()
            .addString("")
        .build(api));
    }
}