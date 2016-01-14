package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class RandomUnicodeEvent extends BaseEvent
{
    public RandomUnicodeEvent(String name, String command, String description)
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
        int times = Math.abs(Integer.parseInt(e.getMsg().getMessage().substring(9)));
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
                char randChar = (char)(Math.random()*0xFFFF+0x0000);
                e.getGroup().sendMessage(new MessageBuilder()
                    .addString((i + 1) + ": " + Character.toString(randChar) + "\n")
                .build(api));
                StartAPI.sleep(150);
            }
        }
    }
    
    public String getCommand()
    {
        return command;
    }
}