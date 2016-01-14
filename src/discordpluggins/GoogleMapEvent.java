package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class GoogleMapEvent extends BaseEvent
{

    public GoogleMapEvent(String name, String command, String description)
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
        String input[] = e.getMsg().getMessage().split(" ");
        double x = Double.parseDouble(input[1]);
        double y = Double.parseDouble(input[2]);
        
        e.getGroup().sendMessage(new MessageBuilder()
            .addString("https://www.google.ca/maps/@" + x + "," + y + ",10z?hl=en")
            .build(api));
    }
}