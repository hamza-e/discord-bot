package commands.defaut;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import commands.Command;
import commands.Emoji;
import commands.Command.ExecuterType;
import event.BotListener;
import game.TicTacToe;
import ma.hamza.disc.Discord;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.managers.GuildController;

public class CommandDefault {

	private final Discord bot;

	private final Map<User, Integer> betTable = new HashMap<>();
	private final List<User> players = new ArrayList<>();
	private static TicTacToe ticTacToe;

	public CommandDefault(Discord bot) {
		this.bot = bot;
	}

	
	
	@Command(name = "stop", type = ExecuterType.CONSOLE)
	private void stop() {
		bot.setRunning(false);
	}

	@Command(name = "info", type = ExecuterType.USER)
	private void info(User user, MessageChannel channel) {
		if (channel instanceof TextChannel) {
			TextChannel textChannel = (TextChannel) channel;
			if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS))
				return;
		}
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
		builder.setTitle("INFO");
		builder.setDescription("JOJ 3AM MAJIT L DISCORD :ok_hand: :ok_hand: ");
		builder.setColor(Color.GREEN);
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "sebtima", type = ExecuterType.USER)
	private void whostheboss(MessageChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Fakio TIMA !!!");
		builder.setColor(Color.RED);
		channel.sendMessage(builder.build()).queue();

	}

	@Command(name = "sebhamza", type = ExecuterType.USER)
	private void nosebhamza(User user, MessageChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("FAKIO nta a " + user.getAsMention() + " !!!");
		builder.setColor(Color.RED);
		channel.sendMessage(builder.build()).queue();

	}

	@Command(name = "malmok", type = ExecuterType.USER)
	private void malmok(User user, MessageChannel channel) {
		channel.sendMessage("Mal MOK a " + user.getAsMention() + " !!!!!! :middle_finger:").queue();
	}

	@Command(name = "whoshmar", type = ExecuterType.USER)
	private void whoshmar(User user, MessageChannel channel) {
		channel.sendMessage("u HMAR BIEN SUR!!!!!!").queue();
	}

	@Command(name = "join9merclub", type = ExecuterType.USER)
	private void join(User user, MessageChannel channel) {
		if (channel instanceof TextChannel) {
			TextChannel textChannel = (TextChannel) channel;
			if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS))
				return;
		}
		betTable.put(user, 1000);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
		builder.setTitle("Halal 9mer");
		builder.setDescription("Mer7ba bel 9emar jdid m3ana :moneybag: :moneybag: !!");
		builder.addField("HowTO", "9emer b !9emer", true);
		builder.addField("ur BINGA", "1000 Dirham", true);
		builder.setColor(Color.GREEN);
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "9emer", type = ExecuterType.USER)
	private void bets(User user, MessageChannel channel, Message message) {
		String x = message.getContentDisplay();
		int val = Integer.parseInt(x.replaceFirst("!9emer ", ""));
		if (betTable.get(user) == null) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
			builder.addField("ERROOOOR !!!", "Join club tl 9mer b3da !!!! kteb !join9merclub", true);
			builder.setColor(Color.RED);
			channel.sendMessage(builder.build()).queue();
		} else if (betTable.get(user) < val) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
			builder.addField("FA9IR !!!", "taykono 3endek had l flos 3ad 9emer bihom !!! :angry: 3endek ghi "
					+ betTable.get(user) + " Dirham", true);
			builder.setColor(Color.RED);
			channel.sendMessage(builder.build()).queue();
		} else {
			Random random = new Random();
			boolean winorlose = random.nextBoolean();
			if (winorlose) {
				betTable.put(user, betTable.get(user) + val);
				EmbedBuilder builder = new EmbedBuilder();
				builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
				builder.setTitle("9emerti b " + val);
				builder.setDescription(":money_mouth: RBE7TIIII :money_mouth: ");
				builder.addField("ur BINGA", String.valueOf(betTable.get(user)) + " Dirham", true);
				builder.setColor(Color.GREEN);
				channel.sendMessage(builder.build()).queue();
			} else {
				betTable.put(user, betTable.get(user) - val);
				EmbedBuilder builder = new EmbedBuilder();
				builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
				builder.setTitle("9emerti b " + val + " Dirham");
				builder.setDescription("Ha li rbe7ti :poop:");
				builder.setColor(Color.YELLOW);
				builder.addField("ur BINGA", String.valueOf(betTable.get(user)), true);
				channel.sendMessage(builder.build()).queue();
			}
		}

	}

	@Command(name = "dirham", type = ExecuterType.USER)
	private void howmuch(User user, MessageChannel channel) {
		if (channel instanceof TextChannel) {
			TextChannel textChannel = (TextChannel) channel;
			if (!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS))
				return;
		}
		if (betTable.get(user) == null) {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
			builder.addField("Poor !!!", "3endek l :poop: kteb !join9merclub bach t9yed", true);
			builder.setColor(Color.RED);
			channel.sendMessage(builder.build()).queue();
		} else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
			builder.addField("ur BINGA", String.valueOf(betTable.get(user)) + " Dirham", true);
			builder.setColor(Color.GREEN);
			channel.sendMessage(builder.build()).queue();
		}
	}

	@Command(name = "chfer", type = ExecuterType.USER)
	private void test(User user, MessageChannel channel, Message message) {
		List<User> l = message.getMentionedUsers();
		if (betTable.get(l.get(0)) != null) {
			Random random = new Random();
			Integer ranval = random.nextInt(betTable.get(l.get(0)) + 1);
			boolean winorlose = random.nextBoolean();
			if (winorlose) {
				betTable.put(user, betTable.get(user) + ranval);
				betTable.put(l.get(0), betTable.get(l.get(0)) - ranval);
				EmbedBuilder builder = new EmbedBuilder();
				builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
				builder.setTitle("Tamat sari9a bi naja7");
				builder.addField("", "chferti : " + ranval, false);
				builder.setColor(Color.GREEN);
				builder.addField("ur BINGA", String.valueOf(betTable.get(user)) + " Dirham", true);
				channel.sendMessage(builder.build()).queue();
			} else {
				if (betTable.get(user) < ranval) {
					betTable.put(l.get(0), betTable.get(l.get(0)) + betTable.get(user));
					betTable.put(user, 0);
					EmbedBuilder builder = new EmbedBuilder();
					builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
					builder.setTitle("Aji Lmok baghi tchefer !!!");
					builder.addField("", "mchat lik : " + ranval, false);
					builder.addField("ur BINGA", String.valueOf(betTable.get(user)) + " Dirham", true);
					builder.setColor(Color.RED);
					channel.sendMessage(builder.build()).queue();
				} else {
					betTable.put(l.get(0), betTable.get(l.get(0)) + ranval);
					betTable.put(user, betTable.get(user) - ranval);
					EmbedBuilder builder = new EmbedBuilder();
					builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
					builder.setTitle("Aji Lmok baghi tchefer !!!");
					builder.addField("", "mchat lik : " + ranval, false);
					builder.setColor(Color.RED);
					builder.addField("ur BINGA", String.valueOf(betTable.get(user)) + " Dirham", true);
					channel.sendMessage(builder.build()).queue();
				}
			}
		} else {
			EmbedBuilder builder = new EmbedBuilder();
			builder.setAuthor(user.getName(), null, user.getAvatarUrl() + "?size=256");
			builder.setDescription("Dak syed maki9emerch");
			builder.setColor(Color.RED);
			channel.sendMessage(builder.build()).queue();
		}
	}

	@Command(name = "board", type = ExecuterType.USER)
	private void board(User user, MessageChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("LeaderBoard");
		for (Map.Entry<User, Integer> entry : betTable.entrySet()) {
			builder.addField("", entry.getKey().getName() + " : " + entry.getValue(), false);
		}
		builder.setColor(Color.YELLOW);
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "help", type = ExecuterType.USER)
	private void help(MessageChannel channel) {
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Commands");
		builder.setThumbnail(
				"http://spongebobia.com/spongebob-captures/content/episodes/galleries/068a%20-%20Patrick%20SmartPants/068a%20-%20Patrick%20SmartPants%20(005).jpg");
		builder.addField("", "- !join9merclub for 1000 dirham w inscription", false);
		builder.addField("", "- !9emer <flos> for te9mar", false);
		builder.addField("", "- !chfer <victim> for tachfart", false);
		builder.addField("", "- !dirham for ch7al 3ndek", false);
		builder.addField("", "- !board for nata2ij", false);
		builder.setColor(Color.BLUE);
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "secretcommand", type = ExecuterType.USER)
	private void secret(User user, MessageChannel channel, Message message) {
		betTable.put(user, 1000000000);
		message.delete().queue();
		channel.sendMessage("shshshshshshsh !!").queue();
	}

	@Command(name = "memePEPE", type = ExecuterType.USER)
	private void memepepe(MessageChannel channel) {
		channel.sendMessage(
				"https://image.spreadshirtmedia.com/image-server/v1/compositions/1012773656/views/1,width=650,height=650,appearanceId=1,backgroundColor=d6daf0,version=1547454572/classic-sad-frog.jpg")
				.queue();
	}

	@Command(name = "memehagay", type = ExecuterType.USER)
	private void memeHaGay(MessageChannel channel, User user) {
		channel.sendMessage(
				"http://www.quickmeme.com/img/af/af473b328160e31269c0656af34badcd8ec6db35114e5d0f296e801a79d6b155.jpg")
				.queue();
	}

	@Command(name = "fakiosihimi", type = ExecuterType.USER)
	private void khelethom(MessageChannel channel, User user, Message message) {
		// List<User> l = message.getMentionedUsers();
		// //message.delete().reason("hakak").queue();
		// message.addReaction(":hagay:").queue();
		// String mentionedUsers = "";
		// for (User user2 : l) {
		// mentionedUsers += user2.getAsMention()+ " ";
		// }
		// System.out.println(mentionedUsers);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(new Color(227, 51, 247));
		builder.setTitle("Fakio sihim");
		builder.setImage("https://cdn.discordapp.com/attachments/479826201750405125/541604509877534720/20190203_140255.gif");
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "play", type = ExecuterType.USER)
	private void game(MessageChannel channel, User user, Message message) {
		players.add(user);
		EmbedBuilder builder = new EmbedBuilder();
		builder.setTitle("Welcome to Solve a riddle blablabla");
		builder.addField("HTP", "use !answer [the answer]", true);
		builder.addField("Question is :", "yes or no ?", false);
		builder.setColor(new Color(34, 65, 114));
		channel.sendMessage(builder.build()).queue();
	}

	@Command(name = "answer", type = ExecuterType.USER)
	private void answer(MessageChannel channel, User user, Message message) {
		if (players.contains(message.getAuthor())) {
			String x = message.getContentDisplay();
			String answer = x.replaceFirst("!answer ", "");
			if (answer.equals("yes")) {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle("Correct !!!!");
				builder.setColor(new Color(102, 219, 98));
				channel.sendMessage(builder.build()).queue();
				players.remove(0);
			} else {
				EmbedBuilder builder = new EmbedBuilder();
				builder.setTitle("false !!!!");
				builder.setColor(new Color(255, 0, 0));
				channel.sendMessage(builder.build()).queue();
			}
		}
	}

	@Command(name = "sp", type = ExecuterType.USER)
	private void sp(User user, Message message) {
		message.delete().reason("").queue();
		String x = message.getContentDisplay();
		x = x.replaceFirst("!sp ", "");
		String text = x.split(Pattern.quote("|"))[1];
//		User user1 = message.getMentionedUsers(); 
		System.out.println(message.getMentionedUsers());
//		user1.openPrivateChannel().queue((channel) -> {
//			channel.sendMessage(text).queue();
//		});
	}

	
	@Command(name = "totot", type = ExecuterType.USER)
	private void testem(MessageChannel channel,User user, Message message) {
		
		channel.sendMessage(":laughing_j:").queue();
	}
	
	// *********************

	@Command(name = "ttt", type = ExecuterType.USER)
	private void titac(MessageChannel channel,User user, Message message) {
		message.delete().reason("").queue();
		System.out.println(message.getMentionedUsers());
		if(!message.getMentionedUsers().isEmpty()){
			System.out.println("gets here ---------------------------");
			ticTacToe = new TicTacToe(channel,message);
		}else{
			String x = message.getContentDisplay();
			x = x.replaceFirst("!ttt ", "");
			
			int val = Integer.parseInt(x = x.replace(" ", ""));
			System.out.println(val+" <--------------------");
			if(val == (int) val){
				System.out.println(ticTacToe+" HELOOOOO VAL");
				ticTacToe.sendInput(val, message);	
			}else{
				channel.sendMessage("enter a number").queue();
			}
		}
	}

    public static void resetTicTacToe() {
      ticTacToe = null;
    }
    // mute
    @Command(name = "mute", type = ExecuterType.USER)
	private void mute(MessageChannel channel,User user, Message message) {
    	List<User> l = message.getMentionedUsers();
    	for (User user2 : l) {
    		BotListener.addtomuted(user2);
		}
		message.delete().reason("").queue();	
    }
    
    @Command(name = "unmute", type = ExecuterType.USER)
	private void unmute(MessageChannel channel,User user, Message message) {
    	List<User> l = message.getMentionedUsers();
    	for (User user2 : l) {
    		BotListener.unmute(user2);
		}
		message.delete().reason("").queue();	
    }
    //mute

    @Command(name = "texttoemot", type = ExecuterType.USER)
	private void textEmot(MessageChannel channel,User user, Message message) {
    	message.delete().reason("").queue();
    	//User hamza = message.getGuild().getMemberById("377226676268040213").getUser();
    	String x = message.getContentDisplay();
    	String input = x.replaceFirst("!texttoemot ", "");
    	//if(message.getAuthor() == hamza){
    		channel.sendMessage(Emoji.stringToEmoji(input)).queue();
    	//}
    }
    
}
