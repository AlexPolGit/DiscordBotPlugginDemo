package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class HVSpaceTypeEvent extends BaseEvent
{
    public HVSpaceTypeEvent(String name, String command, String description)
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
        String input = e.getMsg().getMessage().substring(13);
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
            .addString(out + "\n")
            .build(api));
        super.sleep(500);
        
        for (int i = 1; i < letters.length; i++)
        {
            e.getGroup().sendMessage(new MessageBuilder()
            .addString(Character.toString(letters[i]) + "\n")
            .build(api));
            super.sleep(500);
        }
    }
}