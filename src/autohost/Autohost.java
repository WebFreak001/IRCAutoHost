package autohost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import autohost.RateLimiter;
import autohost.utils.RateLimiterThread;
import java.util.regex.*;


public class Autohost  {
	
	Config config;
	Socket socket;
	BufferedWriter writer;
	BufferedReader reader;
	List<Lobby> Lobbies = new ArrayList<>();
	public List<RateLimiter> limiters = new ArrayList<>();
	//private RateLimiterThread rate;
	
    public static void main(String[] args) throws Exception {
    	Config config = new Config("config.properties");
    	IRCClient irc = new IRCClient(config);
    	
    }
}
	/*
	public Autohost (){
		setName(Config.authName);	
		
	}
	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message){	
		//System.out.println(">Channel>"+channel+" - "+sender+" : "+message);
			Pattern pattern = Pattern.compile("#mp_\\d"); // Is this a multi lobby channel?
			Matcher matcher = pattern.matcher(channel);
				if (matcher.matches()){
					for (Lobby lobby : Lobbies) {
						if (lobby.channel.equalsIgnoreCase(channel)){ // Is it an autohosted (by us) channel?
							ParseChannelMessage(lobby, message);
						}
						else
						{
							System.out.println("Warning: Channel not loaded? C:"+channel);
						}
					}
				}
	}
	*/
	/*
	public void onPrivateMessage(String sender, String login, String hostname, String message){	
		System.out.println(sender+" : "+message);
		message = message.trim();
		if (sender.equalsIgnoreCase("AutoHost")) {
			//this.sendRawLine("PRIVMSG BanchoBot !help");.
			//this.sendRawLine("PRIVMSG BanchoBot !mp make test");
		}
		else if (sender.equalsIgnoreCase("BanchoBot")) {
			//message.
		}
		if (message.startsWith("!")){
			message = message.substring(1);
			String[] args = message.split(" ");
			if (args.length == 1){
				if (args[0].equals("help")){
					this.sendRawLine("PRIVMSG "+sender+" This is a help message.");
				}
			}
			else if (args.length == 2){
				
			}
				
			
		}
		else
		{
			//this.sendRawLine("PRIVMSG "+sender+" This account is a bot. Command prefix is !. Send me !help for more info.");
			sendRawMessage(sender, "This account is a bot. Command prefix is !. Send me !help for more info.");
			
		}
	}
	public void NewLobbyMessage(String channel,String sender, String message)
	{
		
	}
	
	public void ParseChannelMessage(Lobby lobby, String message)
	{
		if (message.contains("Closed the match")){
			Lobbies.remove(lobby);
			System.out.println("Closed Match "+lobby.channel);
		}
	}
	
	public void sendRawMessage(String target, String message) {
		if (rate== null){
			rate = new RateLimiterThread(this);
			rate.start();
			this.sendRawLine("PRIVMSG AutoHost return");
			System.out.println("New RateLimiter");
			
		}
		System.out.println(this.rate.getState());
		Boolean exists = false;
		for (RateLimiter limiter : this.limiters){
			if (limiter.target.equals(target)){
				limiter.addMessage(message);
				exists = true;
			}
		}
		if (!exists){
			System.out.println("New target. Add.");
			RateLimiter rlimiter = new RateLimiter(target, 200);
			rlimiter.addMessage(message);
			limiters.add(rlimiter);		
		}
	}
	
	public void IgnoreSend(String line){
		this.sendRawLine(line);
	}

	/*public void setRate(RateLimiterThread rate2) {
		rate2.start();
		this.rate = rate2;	
	}
	*/
	

