import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

public class Help {
	public Help() {
		Main.cmd[3].onExecuted(context -> {	
			if(context.getArgs().length == 0) {
			String[] names = new String[Main.cmd.length];
			String[] description = new String[Main.cmd.length];
			for(int i = 0; i < Main.cmd.length; i++) {
				names[i] = Main.cmd[i].getName();
				description[i] = Main.cmd[i].getDescription();
			}
			
			for (int n = 0; n < Main.cmd.length; n++) {
		        for (int m = 0; m < 4 - n; m++) {
		            if ((names[m].compareTo(names[m + 1])) > 0) {
		                String swapString = names[m];
		                names[m] = names[m + 1];
		                names[m + 1] = swapString;
		                String swapString2 = description[m];
		                description[m] = description[m + 1];
		                description[m + 1] = swapString2;
		            }
		        }
		    }
			String oP = "";
			for(int i = 0; i < Main.cmd.length; i++) {
				oP += "```diff\n";
				oP += "- "+names[i]+"\n+ "+description[i]+"\n\n";
				oP += "```";
			}
			oP += "```Current prefix is '"+BotUtils.BOT_PREFIX+"'```";
			context.getMessage().getChannel().sendMessage(oP);
			}else {
				String oP = "";
				boolean success = false;
				for(int i = 0; i < Main.cmd.length; i++) {
					if(context.getArgs()[0].equals(Main.cmd[i].getName())) {
						System.out.println("test");
						oP += "```diff\n";
						oP +=  "+ Description: "+Main.cmd[i].getDescription()+"\n\n";
						oP += "Usage: "+BotUtils.BOT_PREFIX+Main.cmd[i].getUsage();
						oP += "```";
						success = true;
						break;
					}			
				}
				if(!success) {
					oP += "Help for '"+context.getArgs()[0]+"' not found.";
				}
				context.getMessage().getChannel().sendMessage(oP);
			}
		});
		CommandRegistry.getForClient(Main.cli).register(Main.cmd[3]);
	}
}
