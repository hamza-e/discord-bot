package commands.defaut;

import java.awt.Color;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import com.vdurmont.emoji.EmojiManager;

import commands.Command;
import commands.Command.ExecuterType;
import ma.hamza.disc.Discord;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

public class GameTests {
	private final Discord bot;
	private String emoji2 = "";


	public GameTests(Discord bot) {
		this.bot = bot;
	}

	@Command(name = "rps", type = ExecuterType.USER)
	private void testEmo(MessageChannel channel,User user, Message message) {
		String x = message.getContentDisplay();
		String texte = x.replaceFirst("!rps ", "");
        
            String hand = "", emoji = "";
            String hand2 = getHand();
            if("rock".equals(texte) || "rocks".equals(texte) || "r".equals(texte) || "stone".equals(texte))
            {
            	message.delete().reason("dlkfdlsf").queue();;
                emoji = commands.Emoji.ROCK;
                hand = "rock";
            }
            else if("paper".equals(texte) || "papers".equals(texte) || "p".equals(texte))
            {
                emoji = commands.Emoji.PAPER;
                hand = "paper";
            }
            else if("scissor".equals(texte) || "scissors".equals(texte) || "s".equals(texte))
            {
                emoji = commands.Emoji.SCISSORS;
                hand = "scissors";
            }
            else
            {
                channel.sendMessage(commands.Emoji.ERROR + " Please enter a valid choice.").queue();
                return;
            }
            
            String output = compare(hand, hand2);
            
            channel.sendMessage(output + "\n You: " + emoji + " Me: " + emoji2).queue();
            message.delete().reason("testing").queue();
	}

	public String compare(String hand, String hand2)
    {
        String result = "";
        if(hand.equals(hand2))
            result = commands.Emoji.TIE + " It's a tie!";
        else if(hand.equals("rock"))
        {
            if(hand2.equals("paper"))
                result = "I won!";
            if(hand2.equals("scissors"))
                result = "You won!";
        }
        else if(hand.equals("paper"))
        {
            if(hand2.equals("scissors"))
                result = "I won!";
            if(hand2.equals("rock"))
                result = "You won!";
        }
        else if(hand.equals("scissors"))
        {
            if(hand2.equals("rock"))
                result = "I won!";
            if(hand2.equals("paper"))
                result = "You won!";
        }
        
        return result;
    }
	
	public String getHand()
    {
        String hand = "";
        int choice = new Random().nextInt(3)+1;
        switch(choice)
        {
            case 1: 
            	hand = "rock";
            	emoji2 = commands.Emoji.ROCK;
            	System.out.println("ROOOOOOOOOOOCK !!!!!!!!!!!!!!!!!!!!!!!!!!");
            	break;
            case 2: 
            	hand = "paper";
            	emoji2 = commands.Emoji.PAPER;
            	System.out.println("PAPER !!!!!!!!!!!!!!!!!!!!!!!!!!");
            	break;
            case 3: 
            	hand = "scissors";
            	emoji2 = commands.Emoji.SCISSORS;
            	System.out.println("SCISSORS !!!!!!!!!!!!!!!!!!!!!!!!!!");
            	break;
            default: hand = "no hand";
            break;
        }
        return hand;
    }
	//*******************************************************************
	
}
