package org.icar.musa.agent_communication.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.icar.ltlpetrinet.supervisor.Token;
import org.icar.ltlpetrinet.supervisor.TokenConf;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

public class JasonTokensConfiguration {


	public static Term object_to_term(TokenConf tokens) {
		Structure terms = new Structure("tokensconfig",2);
		
		StringTermImpl myNet = new StringTermImpl(tokens.getMyNetHiearchy());
		
		ListTermImpl confList = new ListTermImpl();
		for( Token token : tokens.getTokens() ){
			
			Structure confS = new Structure("c", 2);
			
			StringTermImpl mynet_term = new StringTermImpl(token.getMyNet());
			StringTermImpl myplace_term = new StringTermImpl(token.getMyPlace());
//			ListTermImpl valueList = new ListTermImpl();
//			for( String value : tokens.getTokens(key) )
//				valueList.add(new StringTermImpl(value));
			
			confS.addTerm(mynet_term);
			confS.addTerm(myplace_term);
			confList.add(confS);			
		}
		
//		ListTermImpl stateList = new ListTermImpl();
//		for( String key : tokens.getNetsState().keySet() ){
//			Structure stateS = new Structure("s", 2);
//			
//			StringTermImpl keyState = new StringTermImpl(key);
//			StringTermImpl valueState = new StringTermImpl(enum_to_string(tokens.getNetState(key)));
//			
//			stateS.addTerm(keyState);
//			stateS.addTerm(valueState);
//			stateList.add(stateS);			
//		}
		
		terms.addTerm(myNet);
		terms.addTerm(confList);
		//terms.addTerm(stateList);
		
		return terms;
	}
	
	
	public static TokenConf term_to_object(Term term) throws TranslateError {
		TokenConf res = null;
		//HashMap<String, ArrayList<String>> conf = new HashMap<>();
		//HashMap<String, PNStateEnum> netsState = new HashMap<>();
		
		if (!term.isStructure()) 
			throw new TranslateError(); 
		else{
			Structure s = (Structure) term;
			
			StringTermImpl myNetTerm = (StringTermImpl) s.getTerm(0);
			List<Term> confList = ((ListTermImpl) s.getTerm(1)).getAsList();
			
			res = new TokenConf(myNetTerm.getString());
			
			for(Term token_term : confList){
				Structure confS = (Structure) token_term;
				
				String mynet = ((StringTermImpl) confS.getTerm(0)).getString();
				String myplace = ((StringTermImpl) confS.getTerm(1)).getString();
				
				res.getTokens().add( new Token(myplace,mynet));
			}
			
		}
		return res;
	}
	
	public static TokenConf term_string_to_object(String term_string) throws TranslateError{
		Structure t = Structure.parse(term_string);
		return term_to_object(t);
	}
	
	
	
//	private static String enum_to_string(PNStateEnum s) {
//		if (s==PNStateEnum.ACCEPTED) return "A";
//		if (s==PNStateEnum.ERROR) return "E";
//		if (s==PNStateEnum.WAIT_BUT_ACCEPTED) return "WA";
//		if (s==PNStateEnum.WAIT_BUT_ERROR) return "WE";
//		return "W";
//	}
//	
//	private static PNStateEnum string_to_enum(String s) {
//		if (s.equals("A")) return PNStateEnum.ACCEPTED;
//		if (s.equals("E")) return PNStateEnum.ERROR;
//		if (s.equals("WA")) return PNStateEnum.WAIT_BUT_ACCEPTED;
//		if (s.equals("WE")) return PNStateEnum.WAIT_BUT_ERROR;
//		return PNStateEnum.WAIT;
//	}
	
}
