package discordpluggins;

import java.util.ArrayList;
import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class FunnyMemeEvent extends BaseEvent
{
    ArrayList<String> received;
    
    public FunnyMemeEvent(String name, String command, String description)
    {
        super(name, command, description);
        this.api = super.api;
        this.name = name;
        this.command = command;
        this.description = description;
    }

    public void setFileData(ArrayList<String> data)
    {
        received = data;
    }
    
    @Override
    public void doCommand(UserChatEvent e)
    {
        Random r = new Random();
        String result = received.get(r.nextInt(received.size()));
        e.getGroup().sendMessage(new MessageBuilder()
            .addString(result)
        .build(api));
    }
}