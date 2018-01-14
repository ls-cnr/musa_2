package org.icar.specification.linear_temporal_logic.net;

import java.util.ArrayList;
import java.util.HashMap;

import org.icar.musa.core.TokenConfInterface;

/**
 * The Class TokensConfiguration maintains the informations about tokens position for every net and nets state.
 */
public class TokenConf implements TokenConfInterface {
	
	/** The dictionary that associate a net (with its reference) to a list of that net's places that indicates tokens positions  */
	private HashMap<String, ArrayList<String>> conf;
	
	/** The nets state. */
	private HashMap<String, PNStateEnum> netsState;
	
	/**
	 * Instantiates a new tokens configuration, putting a token in the starting place of the starting net.
	 *
	 * @param nets
	 *            the nets
	 */
	public TokenConf( PNHierarchy nets ){
		conf = new HashMap<>();
		netsState = new HashMap<>();
		
		String start = nets.getStartingNet();
		conf.put(start, new ArrayList<>());
		conf.get(start).add(nets.getStartingPN().getStartPlace().getName());
		netsState.put(start, nets.getStartingPN().getPlaceState().get(nets.getStartingPN().getStartPlace()));
	}
	
	/**
	 * Instantiates a new tokens configuration copying another one.
	 *
	 * @param prevConf
	 *            the prev conf
	 */
	public TokenConf( TokenConfInterface prevConf ) {
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
	public TokenConf(HashMap<String, ArrayList<String>> conf, HashMap<String, PNStateEnum> netsState) {
		this.conf = conf;
		this.netsState = netsState;
	}

	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#addToken(java.lang.String, java.lang.String)
	 */
	@Override
	public void addToken( String net, String token ) {	
		if( !conf.containsKey(net) )
			conf.put(net, new ArrayList<>()); 
		conf.get(net).add(token);
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#removeToken(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeToken(String net, String token ) {
		conf.get(net).remove(token);
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#getNetState(java.lang.String)
	 */
	@Override
	public PNStateEnum getNetState( String net ) {
		return netsState.get(net);
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#setNetState(java.lang.String, org.icar.specification.linear_temporal_logic.net.PetriNetState)
	 */
	@Override
	public void setNetState( String net, PNStateEnum state ) {
		netsState.put(net, state);
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#removeNetState(java.lang.String)
	 */
	@Override
	public void removeNetState( String net ) {
		netsState.remove(net);
	}

	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#getNetsState()
	 */
	@Override
	public HashMap<String, PNStateEnum> getNetsState() {
		return netsState;
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#getTokens(java.lang.String)
	 */
	@Override
	public ArrayList<String> getTokens( String net ) {
		if(conf.containsKey(net))
			return conf.get(net);
		else 
			return null;
	}

	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#getConf()
	 */
	@Override
	public HashMap<String, ArrayList<String>> getConf() {
		return conf;
	}
	
	/* (non-Javadoc)
	 * @see org.icar.specification.linear_temporal_logic.net.TokenConfInterface#getNets()
	 */
	@Override
	public Iterable<String> getNets() {
		return conf.keySet();
	}
}
