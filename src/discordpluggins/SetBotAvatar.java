package discordpluggins;

import java.io.InputStream;
import java.net.URL;
import me.itsghost.jdiscord.events.UserChatEvent;

public class SetBotAvatar extends BaseEvent
{
    public SetBotAvatar(String name, String command, String description)
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
        String toAvatar = e.getMsg().getMessage().substring(9);
        InputStream input = null;
        try
        {
            input = new URL(toAvatar).openStream();
            api.getAccountManager().setAvatar(input);
        }
        catch (Exception ex)
        {
            System.err.println(ex.toString());
        }
    }
    
    public String getCommand()
    {
        return command;
    }
}