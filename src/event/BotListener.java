package event;

import java.util.ArrayList;

import com.vdurmont.emoji.EmojiParser;

import commands.CommandMap;
import commands.Emoji;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class BotListener implements EventListener {

	private final CommandMap commandMap;
	private static ArrayList<User> muted = new ArrayList<>();
	
	public BotListener(CommandMap commandMap) {
		this.commandMap = commandMap;
	}
	
	@Override
	public void onEvent(Event event) {
		System.out.println(event.getClass().getSimpleName());
		if (event instanceof MessageReceivedEvent) {
			onMessage((MessageReceivedEvent) event);
		}
	}

	private void onMessage(MessageReceivedEvent event) {
		if (event.getAuthor().equals(event.getJDA().getSelfUser()))
			return;
		
		//399738440742338561 Sihim
		//377226676268040213 hamza
		//444214741410512896 rihib
		//445719648252592139 ucef
		System.out.println(event.getAuthor());
		String message = event.getMessage().getContentDisplay();
		System.out.println(message);
		
		User sihim = event.getGuild().getMemberById("399738440742338561").getUser();
		User hamza = event.getGuild().getMemberById("377226676268040213").getUser();
		//System.out.println(sihim);
		//String sometext = event.getMessage().getContentDisplay().toLowerCase();
		if(event.getMessage().getAuthor() == sihim){
			//System.out.println("yes baby *********************");
			event.getMessage().addReaction("\ud83c\udded").queue();
			event.getMessage().addReaction("\ud83c\uddf2").queue();
			event.getMessage().addReaction("\uD83C\uDDE6").queue();
			event.getMessage().addReaction("\ud83c\uddf7").queue();
			event.getMessage().addReaction("\uD83C\uDD70").queue();
			//event.getMessage().delete().queue();
		}
//		if(event.getAuthor() == hamza ){
//			//System.out.println(EmojiParser.parseFromUnicode(arg0, arg1));
//			event.getMessage().addReaction("\uD83C\uDD74").queue();
//		}
		

		for (int i = 0; i < muted.size(); i++) {
			if (event.getAuthor() == muted.get(i))
				event.getMessage().delete().queue();
		}
			
		if(message.startsWith(commandMap.getTag())){
			message = message.replaceFirst(commandMap.getTag(), "");
			if(commandMap.commandUser(event.getAuthor(), message, event.getMessage())){
				if(event.getTextChannel() != null && event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)){
					//event.getMessage().delete().queue();
				}
			}
		}
		
	}
	
	public static void addtomuted(User u){
		muted.add(u);
	}

	public static void unmute(User u){
		for (int i = 0; i < muted.size(); i++) {
			if(u == muted.get(i)){
				muted.remove(i);
			}
		}
	}
	
	public static ArrayList<User> getMuted(){
		return muted;
	}
}
