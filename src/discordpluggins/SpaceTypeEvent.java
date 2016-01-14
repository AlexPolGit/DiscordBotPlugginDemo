package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class SpaceTypeEvent extends BaseEvent
{
    public SpaceTypeEvent(String name, String command, String description)
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
        String input = e.getMsg().getMessage().substring(11);
        char letters[] = input.toCharArray();
        String out = "";
        for (char c: letters)
        {
            if (c != ' ')
            {
                out += c + " ";
            }
        }
        
        e.getGroup().sendMessage(new MessageBuilder()
            .addString(out)
            .build(api));
    }
}