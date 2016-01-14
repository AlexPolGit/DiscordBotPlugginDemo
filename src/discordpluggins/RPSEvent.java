package discordpluggins;

import java.util.Random;
import me.itsghost.jdiscord.events.UserChatEvent;
import me.itsghost.jdiscord.message.MessageBuilder;

public class RPSEvent extends BaseEvent
{

    public RPSEvent(String name, String command, String description)
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
        final String R = "rock";
        final String P = "paper";
        final String S = "scissors";
        final String WIN = "YOU WIN";
        final String LOSE = "YOU LOSE";
        final String TIE = "NO WINNER";
        String input[] = e.getMsg().getMessage().split(" ");
        String userChoice = input[1];
        int botNum = new Random().nextInt(3) + 1;
        String botChoice = "";

        switch(botNum)
        {
            case 1:
            {
                botChoice = R;
                break;
            }
            case 2:
            {
                botChoice = P;
                break;
            }
            case 3:
            {
                botChoice = S;
                break;
            }
        }
        
        e.getGroup().sendMessage(new MessageBuilder()
                .addString("Bot chose: " + botChoice + "\n")
                .addString("You chose: " + userChoice + "\n")
                .build(api));
        
        System.out.println("In RPS, bot chose: " + botChoice + " (" + botNum + ")");
        
        if (botChoice.equalsIgnoreCase(userChoice))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(TIE)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(R) && userChoice.equalsIgnoreCase(P))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(WIN)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(R) && userChoice.equalsIgnoreCase(S))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(LOSE)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(P) && userChoice.equalsIgnoreCase(R))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(LOSE)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(P) && userChoice.equalsIgnoreCase(S))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(WIN)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(S) && userChoice.equalsIgnoreCase(R))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(WIN)
                .build(api));
        }
        else if (botChoice.equalsIgnoreCase(S) && userChoice.equalsIgnoreCase(P))
        {
            e.getGroup().sendMessage(new MessageBuilder()
                .addBold(LOSE)
                .build(api));
        }
    }
}