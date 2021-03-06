import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.json.simple.JSONObject;

import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

public class Commands {
	public Commands() {		

		//A test command structure
		Command test = new Command("test");
		test.withDescription("A command for testing");
		test.onExecuted(context ->	context.getMessage().getChannel().sendMessage("Test"));
		Main.addCmd(test);

		Command invite = new Command("invite");
		invite.withDescription("Sends a group invite to another Player");
		invite.withUsage("<@username>");
		Main.addCmd(invite);

		//Command for Changing Hotkeys
		Command prefix = new Command("prefix");
		prefix.withDescription("Change the hotkeys of commands");
		prefix.onExecuted(context -> {		
			if(context.getArgs().length >= 1) {				
				CommandRegistry.getForClient(Main.cli).setPrefix(context.getArgs()[0]);				
				context.getMessage().getChannel().sendMessage("Prefix changed to '"+context.getArgs()[0]+"'");
				BotUtils.BOT_PREFIX = context.getArgs()[0];				
				BotUtils.obj.put("bot_prefix", BotUtils.BOT_PREFIX);
				try (FileWriter file = new FileWriter("test.json")) {
					file.write(BotUtils.obj.toJSONString());
					file.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		prefix.withUsage("<new prefix>");		
		Main.addCmd(prefix);

		Command help = new Command("help");
		help.withDescription("Help page for commands");		
		help.withUsage("[command]");
		Main.addCmd(help);

		Command example = new Command("example");		
		example.withDescription("An example for syntax highlighting");
		example.onExecuted(context -> {				
			RPGBox b2 = new RPGBox("Test");						
			b2.addLine("Test25");
			b2.addGreenLine("Once upon a time, there was a boy and a girl, who were obama");			
			b2.addRedLine("Hello");
			b2.setHeader("Adventures");
			String oP2 = b2.getBox();
			context.getMessage().getChannel().sendMessage(oP2);
		});
		
		Command group = new Command("group");
		group.withDescription("Used to manage your group, use 'remove' to remove members and 'promote' to promote to new group leader (use the request command to invite people)");
		group.withUsage("remove @username\n+        "+BotUtils.BOT_PREFIX+"group promote @username");
		group.onExecuted(context -> {				
			String[] args = context.getArgs();
			if(args[0].equalsIgnoreCase("list")) {
				
			}
		});
		Main.addCmd(group);
		
		Command pardon = new Command("pardon");
		pardon.withDescription("Pardon?");
		group.onExecuted(context -> {
			context.getMessage().getChannel().sendMessage("Pardon?");
			context.getMessage().getChannel().sendMessage("!pardon");
		});
		Main.addCmd(pardon);
	}
}
