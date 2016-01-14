package discordpluggins;

import java.util.ArrayList;
import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class UnicodeEmojiEvent extends BaseEvent
{
    ArrayList<String> received;
    
    public UnicodeEmojiEvent(String name, String command, String description)
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

        int times = Math.abs(Integer.parseInt(e.getMsg().getMessage().substring(7)));
        if (times >= 11)
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold("TOO MUCH.")
                .build(api));
        }
        else if (times == 0)
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold("NOT EVEN ONCE?")
                .build(api));
        }
        else
        {
            for (int i = 0; i < times; i++)
            {
                String result = received.get(r.nextInt(received.size()));
                e.getGroup().sendMessage(new MessageBuilder()
                    .addString(result)
                .build(api));;
            }
        }
    }
}