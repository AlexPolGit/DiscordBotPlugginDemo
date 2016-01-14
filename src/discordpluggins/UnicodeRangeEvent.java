package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class UnicodeRangeEvent extends BaseEvent
{

    public UnicodeRangeEvent(String name, String command, String description)
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
        String inputs[] = e.getMsg().getMessage().split(" ");
        int start = Integer.parseInt(inputs[1]);
        int end = Integer.parseInt(inputs[2]);
        for (int x = start; x <= end; x++)
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addString(x + ": " + Character.toString((char)x))
                .build(api));
            super.sleep(1000);
        }
    }
}