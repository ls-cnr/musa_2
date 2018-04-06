package org.icar.linear_temporal_logic.ltl_supervisor;

import java.util.HashSet;
import java.util.Set;


public class TokenConf {
	private String myNetHiearchy; 
	private Set<Token> tokens;
	
	public TokenConf(String myNetHiearchy, Set<Token> tokens) {
		super();
		this.myNetHiearchy = myNetHiearchy;
		this.tokens = tokens;
	}

	public TokenConf(String myNetHiearchy) {
		super();
		this.myNetHiearchy = myNetHiearchy;
		this.tokens = new HashSet<Token>();
	}

	public String getMyNetHiearchy() {
		return myNetHiearchy;
	}

	public void setMyNetHiearchy(String myNetHiearchy) {
		this.myNetHiearchy = myNetHiearchy;
	}

	public Set<Token> getTokens() {
		return tokens;
	}

	public void setTokens(Set<Token> tokens) {
		this.tokens = tokens;
	}

	public Set<Token> filter_tokens_by_net(String name) {
		Set<Token> filtered = new HashSet<Token>();
		for (Token t : tokens) {
			if (t.getMyNet().equals(name)) {
				filtered.add(t);
			}
		}
		return filtered;
	}

	public boolean contain_a_token(String pn_name, String place_name) {
		boolean result = false;
		for (Token t : tokens) {
			if (t.getMyNet().equals(pn_name) && t.getMyPlace().equals(place_name)) {
				result=true;
			}
		}
		return result;
	}

	
	@Override
	public String toString() {
		return "TokenConf [myNetHiearchy=" + myNetHiearchy + ", tokens=" + tokens + "]";
	}



	
	
}
