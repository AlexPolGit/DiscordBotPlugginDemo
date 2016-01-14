package discordpluggins;

import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class SubredditEvent extends BaseEvent
{
    public SubredditEvent(String name, String command, String description)
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
        String reddit = e.getMsg().getMessage().substring(3);
        e.getGroup().sendMessage(new MessageBuilder()
            .addString("https://www.reddit.com/r/" + reddit)
            .build(api));
    }
    
    public String getCommand()
    {
        return command;
    }
}