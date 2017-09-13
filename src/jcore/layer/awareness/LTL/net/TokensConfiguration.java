package layer.awareness.LTL.net;

import java.util.ArrayList;
import java.util.HashMap;

public class TokensConfiguration {
	
	private HashMap<String, ArrayList<String>> conf;
	
	private HashMap<String, String> netsState;
	
	public TokensConfiguration( Nets nets ){
		conf = new HashMap<>();
		netsState = new HashMap<>();
		
		String start = nets.getStartingNet();
		conf.put(start, new ArrayList<>());
		conf.get(start).add(nets.getStartingPN().getStartPlace().getName());
		netsState.put(start, "W");
	}
	
	public TokensConfiguration( TokensConfiguration prevConf ) {
		conf = new HashMap<>(prevConf.getConf());
		netsState = new HashMap<>(prevConf.getNetsState());
	}
	
	public TokensConfiguration( HashMap<String, ArrayList<String>> conf, HashMap<String, String> netsState ) {
		this.conf = new HashMap<>(conf);
		this.netsState = new HashMap<>(netsState);
	}
	
	public void addToken( String net, String token ) {	
		conf.get(net).add(token);
	}
	
	public void removeToken(String net, String token ) {
		conf.get(net).remove(token);
	}
	
	public String getNetState( String net ) {
		return netsState.get(net);
	}
	
	public void setNetState( String net, String state ) {
		netsState.put(net, state);
	}
	
	public void removeNetState( String net ) {
		netsState.remove(net);
	}

	public HashMap<String, String> getNetsState() {
		return netsState;
	}
	
	public ArrayList<String> getTokens( String net ) {
		if(conf.containsKey(net))
			return conf.get(net);
		else 
			return null;
	}

	public HashMap<String, ArrayList<String>> getConf() {
		return conf;
	}
	
	public Iterable<String> getNets() {
		return conf.keySet();
	}
}
