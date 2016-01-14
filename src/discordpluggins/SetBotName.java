package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;

public class SetBotName extends BaseEvent
{
    public SetBotName(String name, String command, String description)
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
        String toName = e.getMsg().getMessage().substring(8);

        for (int i = 0; i < 100; i++)
            api.getAccountManager().setDisplayName(toName);
    }
    
    public String getCommand()
    {
        return command;
    }
}