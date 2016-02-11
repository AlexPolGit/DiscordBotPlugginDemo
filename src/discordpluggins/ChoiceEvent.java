package discordpluggins;

import java.util.ArrayList;
import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class ChoiceEvent extends BaseEvent
{ 
    final ArrayList<BaseEvent> CE = super.commandsEquals;
    final ArrayList<BaseEvent> CS = super.commandsStarts;
    
    public ChoiceEvent(String name, String command, String description)
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
        String input = e.getMsg().getMessage().substring(8);
        String choices[] = input.split(";");
        int n = r.nextInt(choices.length);
        e.getGroup().sendMessage(new MessageBuilder()
                .addBold(choices[n])
                .build(api));
    }
}