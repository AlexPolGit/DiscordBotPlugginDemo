package discordpluggins;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.events.Event;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.EventListener;

public class JDABaseEvent implements EventListener
{
    public JDABuilder jda = new JDABuilder();
    public String name;
    public String command;
    public String description;
    
    JDABaseEvent(String name, String command, String description)
    {
        name = this.name;
        command = this.command;
        description = this.description;
    }
    
    public static void main(String[] args) throws LoginException
    {
        JDA jda = new JDABuilder("ProffessorAlexP+bot@gmail.com", "botpass").addListener(new JDABaseEvent("", "", "")).build();
    }

    public void onEvent(MessageReceivedEvent e)
    {
        TextChannel channel = e.getTextChannel();
        channel.sendMessage("xd");
    }

    @Override
    public void onEvent(Event event)
    {
        
    }
}