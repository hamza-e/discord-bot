package ma.hamza.disc;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import commands.CommandMap;
import event.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class Discord implements Runnable{

	private final JDA jda;
	private final CommandMap commandmap = new CommandMap(this);
	
	private final Scanner scanner = new Scanner(System.in);
	private boolean running;
	
	public Discord() throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("your token").buildAsync();
		jda.getPresence().setGame(Game.watching("SEXO KEROKORE !!!"));
		jda.addEventListener(new BotListener(commandmap));
		System.out.println("yes");
	}
		@Override
	public void run() {
		running = true;
		while (running) {
			if(scanner.hasNextLine()) commandmap.commandConsole(scanner.nextLine());
		}
		scanner.close();
		System.out.println("Bot Stopped");
		jda.shutdown();
		System.exit(0);
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public JDA getJda() {
		return jda;
	}
	public static void main(String[] args) {
		try {
			Discord bot = new Discord(); 
			new Thread(bot,"bot").start();
		} catch (LoginException e) {
			e.printStackTrace();
		} 
	}

	
}
