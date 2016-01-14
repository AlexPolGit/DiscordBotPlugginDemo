package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class InfoEvent extends BaseEvent
{
    public InfoEvent(String name, String command, String description)
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
        String who = e.getMsg().getMessage().substring(6);
            e.getGroup().sendMessage(new MessageBuilder()
                .addString("ID: " + e.getServer().getGroupUserByUsername(who).getUser().getId())
                .addString("\nName: " + e.getServer().getGroupUserByUsername(who).getUser().getUsername())
                .addString("\nPlaying: " + e.getServer().getGroupUserByUsername(who).getUser().getGame())
                .addString("\nAvatar: " + e.getServer().getGroupUserByUsername(who).getUser().getAvatar())
                .build(api));
    }
    
    public String getCommand()
    {
        return command;
    }
}