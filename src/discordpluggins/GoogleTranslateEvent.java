package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class GoogleTranslateEvent extends BaseEvent
{

    public GoogleTranslateEvent(String name, String command, String description)
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
        
        String langFrom = input[1];
        String langTo = input[2];
        String toTranslate = "";
        
        for (int i = 3; i < input.length; i++)
        {
            toTranslate += input[i] + "%20";
        }

        e.getGroup().sendMessage(new MessageBuilder()
            .addString("https://translate.google.ca/?hl=en&tab=TT&authuser=0#" + langFrom + "/" + langTo + "/" + toTranslate)
            .build(api));
    }
}