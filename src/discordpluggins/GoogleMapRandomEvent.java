package discordpluggins;

import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class GoogleMapRandomEvent extends BaseEvent
{

    public GoogleMapRandomEvent(String name, String command, String description)
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
        Random r = new Random();       
        int x = r.nextInt(85 - -85 + 1) + -85;
        int y = r.nextInt(178 - -178 + 1) + -178;
        
        e.getGroup().sendMessage(new MessageBuilder()
            .addString("https://www.google.ca/maps/@" + x + "," + y + ",10z?hl=en")
            .build(api));
    }
}