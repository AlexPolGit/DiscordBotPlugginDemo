package discordpluggins;

import java.util.List;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class ServerInfoEvent extends BaseEvent
{
    public ServerInfoEvent(String name, String command, String description)
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
    
    public String getCommand()
    {
        return command;
    }
}