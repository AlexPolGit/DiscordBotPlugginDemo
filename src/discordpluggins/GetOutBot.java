package discordpluggins;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.login.LoginException;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class GetOutBot extends BaseEvent
{
    public GetOutBot(String name, String command, String description)
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
        e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Oh.")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Ok...")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("I'll go now.")
                .build(api));
            sleep(1000);
            StartAPI.killBot();
            sleep(5000);
        try
        {
            StartAPI.createBot();
        }
        catch (LoginException ex)
        {
            System.err.println(ex.toString());
        }
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("I'm back!")
                .build(api));
            sleep(1000);
            e.getGroup().sendMessage(new MessageBuilder()
                .addObject("Sorry for dissapointing you...")
                .build(api));
    }
    
    public String getCommand()
    {
        return command;
    }
}