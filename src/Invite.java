import com.darichey.discord.api.Command;
import com.darichey.discord.api.CommandRegistry;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.reaction.ReactionAddEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class Invite {
	private IGuild iG;
	private String param;
	private long paramL;
	private long paramL2;
	private boolean spaceName = false;
	private IMessage m;
	@SuppressWarnings("unchecked")
	public Invite() {				
		Main.getCommand("invite").onExecuted(context -> {
			if(context.getArgs().length >= 1) {
				iG = context.getMessage().getGuild();
				
				//Getting the long ID of a User			
				if(context.getMessage().getContent().substring(context.getMessage().getContent().indexOf("<")).charAt(2) == '!') {
					param = "<" + context.getMessage().getContent().substring(context.getMessage().getContent().indexOf("@!"));
					spaceName = true;
				}else {
					param = "<" + context.getMessage().getContent().substring(context.getMessage().getContent().indexOf("@"));
				}			
				try {
					if(spaceName) {
						paramL = Long.parseLong(param.substring(3, param.length()-1));
					}else {
						paramL = Long.parseLong(param.substring(2, param.length()-1));
					}
				}catch(Exception e) {
					
				}
				BotUtils.sendMessage(context.getMessage().getGuild().getGeneralChannel(), "invite pending!");
				System.out.println(paramL);
				boolean success = false;
				m = iG.getUserByID(paramL).getOrCreatePMChannel().sendMessage(context.getMessage().getAuthor()+" invited you to his group, accept?");
				paramL2 = context.getMessage().getAuthor().getLongID();		
				
				//Adds the Reactions to the original Message
				do {
					try {
						m.addReaction("👍");
						m.addReaction("👎");
						success = true;
						break;
					}catch(Exception e) {

					}
				}while(success == false);					

				Main.cli.getDispatcher().registerTemporaryListener(new IListener<ReactionAddEvent>() {			
					public void handle(ReactionAddEvent event) {				
						if(event.getMessage().equals(m)) {					
							if(!(event.getUser().getLongID() == event.getClient().getOurUser().getLongID())) {							
								if(event.getReaction().getUnicodeEmoji().getUnicode().equals("👍")) {												
									iG.getUserByID(paramL2).getOrCreatePMChannel().sendMessage(context.getArgs()[0]+" accepted your invite");									
								}else if(event.getReaction().getUnicodeEmoji().getUnicode().equals("👎")) {																	
									iG.getUserByID(paramL2).getOrCreatePMChannel().sendMessage(context.getArgs()[0]+" denied your invite");										
								}
							}
						}
					}
				});				
			}
		});				
	}
}
