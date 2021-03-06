import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

class BotUtils {
	public static JSONObject obj = new JSONObject();
    // Default value for prefix, can be changed with commands
    public static String BOT_PREFIX;    
    // Handles the creation and getting of a IDiscordClient object for a token
    static IDiscordClient getBuiltDiscordClient(String token) {       	
        return new ClientBuilder()
                .withToken(token)
                .build();

    }
    
    // Helper functions to make certain aspects of the bot easier to use.
    static IMessage sendMessage(IChannel channel, String message) {    	
    	// This might look weird but it'll be explained in another page.
        RequestBuffer.request(() -> {
        	IMessage r = null;
            try{
            	r = channel.sendMessage(message);
            } catch (DiscordException e){
                System.err.println("Message could not be sent with error: ");
                e.printStackTrace();
            }
            return r;
        });

		return null;    	
    }
        
}