package layer.awareness.LTL.net;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Class TokensConfiguration maintains the informations about tokens position for every net and nets state.
 */
public class TokensConfiguration {
	
	/** The dictionary that associate a net (with its reference) to a list of that net's places that indicates tokens positions  */
	private HashMap<String, ArrayList<String>> conf;
	
	/** The nets state. */
	private HashMap<String, String> netsState;
	
	/**
	 * Instantiates a new tokens configuration, putting a token in the starting place of the starting net.
	 *
	 * @param nets
	 *            the nets
	 */
	public TokensConfiguration( Nets nets ){
		conf = new HashMap<>();
		netsState = new HashMap<>();
		
		String start = nets.getStartingNet();
		conf.put(start, new ArrayList<>());
		conf.get(start).add(nets.getStartingPN().getStartPlace().getName());
		netsState.put(start, "W");
	}
	
	/**
	 * Instantiates a new tokens configuration copying another one.
	 *
	 * @param prevConf
	 *            the prev conf
	 */
	public TokensConfiguration( TokensConfiguration prevConf ) {
		conf = new HashMap<>();
		netsState = new HashMap<>();
		for( String map : prevConf.getConf().keySet() )
			conf.put(map, new ArrayList<>(prevConf.getConf().get(map)));
		for( String map : prevConf.getNetsState().keySet() )
			netsState.put(map, prevConf.getNetsState().get(map));
	}
	
	/**
	 * Constructor used for JasonTokensConfiguration.
	 *
	 * @param conf
	 *            the conf
	 * @param netsState
	 *            the nets state
	 */
	public TokensConfiguration(HashMap<String, ArrayList<String>> conf, HashMap<String, String> netsState) {
		this.conf = conf;
		this.netsState = netsState;
	}

	/**
	 * Adds a token in a net.
	 *
	 * @param net
	 *            the net
	 * @param token
	 *            the token
	 */
	public void addToken( String net, String token ) {	
		if( !conf.containsKey(net) )
			conf.put(net, new ArrayList<>()); 
		conf.get(net).add(token);
	}
	
	/**
	 * Removes a token from a net.
	 *
	 * @param net
	 *            the net
	 * @param token
	 *            the token
	 */
	public void removeToken(String net, String token ) {
		conf.get(net).remove(token);
	}
	
	/**
	 * Gets the net state.
	 *
	 * @param net
	 *            the net
	 * @return the net state
	 */
	public String getNetState( String net ) {
		return netsState.get(net);
	}
	
	/**
	 * Sets the net state.
	 *
	 * @param net
	 *            the net
	 * @param state
	 *            the state
	 */
	public void setNetState( String net, String state ) {
		netsState.put(net, state);
	}
	
	/**
	 * Removes the net state from the dictionary.
	 *
	 * @param net
	 *            the net
	 */
	public void removeNetState( String net ) {
		netsState.remove(net);
	}

	/**
	 * Gets the nets state.
	 *
	 * @return the nets state
	 */
	public HashMap<String, String> getNetsState() {
		return netsState;
	}
	
	/**
	 * Gets the tokens.
	 *
	 * @param net
	 *            the net
	 * @return the tokens
	 */
	public ArrayList<String> getTokens( String net ) {
		if(conf.containsKey(net))
			return conf.get(net);
		else 
			return null;
	}

	/**
	 * Gets the conf.
	 *
	 * @return the conf
	 */
	public HashMap<String, ArrayList<String>> getConf() {
		return conf;
	}
	
	/**
	 * Gets the nets.
	 *
	 * @return the nets
	 */
	public Iterable<String> getNets() {
		return conf.keySet();
	}
}
