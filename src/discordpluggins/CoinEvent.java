package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class CoinEvent extends BaseEvent
{
    public CoinEvent(String name, String command, String description)
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
        e.getGroup().sendMessage(new MessageBuilder()
            .addBold(result)
        .build(api));
    }
    
    public String getCommand()
    {
        return command;
    }
}