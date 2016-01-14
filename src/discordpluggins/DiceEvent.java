package discordpluggins;

import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class DiceEvent extends BaseEvent
{
    public DiceEvent(String name, String command, String description)
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
        String args[] = e.getMsg().getMessage().split(" ");
        int faces = Math.abs(Integer.parseInt(args[1]));
        int times = Math.abs(Integer.parseInt(args[2]));
        Random r = new Random();
        if (faces != 0)
        {
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
                    int dice = r.nextInt(faces) + 1;
                    e.getGroup().sendMessage(new MessageBuilder()
                        .addItalic("Roll " + (Integer.toString(i + 1)) + ":      ")
                        .addBold(Integer.toString(dice))
                    .build(api));
                }
            }
        }
        else
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold("ZERO FACES. NICE JOB.")
            .build(api));
        }
    }
}