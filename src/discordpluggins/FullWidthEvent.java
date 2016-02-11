package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class FullWidthEvent extends BaseEvent
{
    public FullWidthEvent(String name, String command, String description)
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
        String m = e.getMsg().getMessage().split(" ")[1];
        String text = "";
        for (int i = 0; i < m.length(); i++)
        {
            text += (char)(m.charAt(i) + 0xFEE0);
        }
        e.getGroup().sendMessage(new MessageBuilder()
            .addString(text)
        .build(api));
    }
}