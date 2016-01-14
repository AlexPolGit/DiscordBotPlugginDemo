package discordpluggins;

import java.util.ArrayList;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class HelpEvent extends BaseEvent
{
    public ArrayList<BaseEvent> CE;
    public ArrayList<BaseEvent> CS;
    
    public HelpEvent(String name, String command, String description)
    {
        super(name, command, description);
        this.api = super.api;
        this.name = name;
        this.command = command;
        this.description = description;
    }
    
    public void setLists(ArrayList<BaseEvent> e, ArrayList<BaseEvent> s)
    {
        CE = e;
        CS = s;
    }

    @Override
    public void doCommand(UserChatEvent e)
    {
        e.getGroup().sendMessage(new MessageBuilder() 
            .addString("```")
            .addString("\n")
            .addString("```")
            .addString("\n")
        .build(api));
        
        System.err.println("Fetched Commands Equal: " + CE);

        for (BaseEvent ce : CE)
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("⏩   ")
                .addItalic("`" + ce.command + "`   ")
                .addBold(ce.description + "\n")
            .build(api));
        }

        System.err.println("Fetched Commands Start: " + CS);

        for (BaseEvent cs : CS)
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("⏩   ")
                .addItalic("`" + cs.command + "`   ")
                .addBold(cs.description + "\n")
            .build(api));
        }

        e.getGroup().sendMessage(new MessageBuilder()     
            .addString("\n")
            .addString("```")
            .addString("\n")
            .addString("```")
        .build(api));
    }
}