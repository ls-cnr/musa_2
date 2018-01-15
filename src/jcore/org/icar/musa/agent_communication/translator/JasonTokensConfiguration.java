package org.icar.musa.agent_communication.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.icar.musa.core.TokenConfInterface;
import org.icar.specification.linear_temporal_logic.net.PNStateEnum;
import org.icar.specification.linear_temporal_logic.net.TokenConf;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

public class JasonTokensConfiguration {


	public static Term object_to_term(TokenConfInterface tokens) {
		Structure terms = new Structure("tokensconfig",2);
		
		ListTermImpl confList = new ListTermImpl();
		for( String key : tokens.getConf().keySet() ){
			Structure confS = new Structure("c", 2);
			
			StringTermImpl keyConf = new StringTermImpl(key);
			ListTermImpl valueList = new ListTermImpl();
			for( String value : tokens.getTokens(key) )
				valueList.add(new StringTermImpl(value));
			
			confS.addTerm(keyConf);
			confS.addTerm(valueList);
			confList.add(confS);			
		}
		
		ListTermImpl stateList = new ListTermImpl();
		for( String key : tokens.getNetsState().keySet() ){
			Structure stateS = new Structure("s", 2);
			
			StringTermImpl keyState = new StringTermImpl(key);
			StringTermImpl valueState = new StringTermImpl(enum_to_string(tokens.getNetState(key)));
			
			stateS.addTerm(keyState);
			stateS.addTerm(valueState);
			stateList.add(stateS);			
		}
		
		terms.addTerm(confList);
		terms.addTerm(stateList);
		
		return terms;
	}
	
	
	public static TokenConf term_to_object(Term term) throws TranslateError {
		TokenConf res = null;
		HashMap<String, ArrayList<String>> conf = new HashMap<>();
		HashMap<String, PNStateEnum> netsState = new HashMap<>();
		
		if (!term.isStructure()) 
			throw new TranslateError(); 
		else{
			Structure s = (Structure) term;
			
			List<Term> confList = ((ListTermImpl) s.getTerm(0)).getAsList();
			for(Term temp : confList){
				Structure confS = (Structure) temp;
				
				String key = ((StringTermImpl) confS.getTerm(0)).getString();
				List<Term> valueList = ((ListTermImpl) confS.getTerm(1)).getAsList();
				ArrayList<String> values = new ArrayList<>();
				for(Term value : valueList)
					values.add(((StringTermImpl) value).getString());
				
				conf.put(key, values);
			}
			
			List<Term> stateList = ((ListTermImpl) s.getTerm(1)).getAsList();
			for(Term temp : stateList){
				Structure stateS = (Structure) temp;
				
				String keyState = ((StringTermImpl) stateS.getTerm(0)).getString();
				String valueState = ((StringTermImpl) stateS.getTerm(1)).getString();
								
				netsState.put(keyState, string_to_enum(valueState));
			}
			
		}
		
		res = new TokenConf(conf, netsState);
		return res;
	}
	
	public static TokenConfInterface term_string_to_object(String term_string) throws TranslateError{
		Structure t = Structure.parse(term_string);
		return term_to_object(t);
	}
	
	
	
	private static String enum_to_string(PNStateEnum s) {
		if (s==PNStateEnum.ACCEPTED) return "A";
		if (s==PNStateEnum.ERROR) return "E";
		if (s==PNStateEnum.WAIT_BUT_ACCEPTED) return "WA";
		if (s==PNStateEnum.WAIT_BUT_ERROR) return "WE";
		return "W";
	}
	
	private static PNStateEnum string_to_enum(String s) {
		if (s.equals("A")) return PNStateEnum.ACCEPTED;
		if (s.equals("E")) return PNStateEnum.ERROR;
		if (s.equals("WA")) return PNStateEnum.WAIT_BUT_ACCEPTED;
		if (s.equals("WE")) return PNStateEnum.WAIT_BUT_ERROR;
		return PNStateEnum.WAIT;
	}
	
}
