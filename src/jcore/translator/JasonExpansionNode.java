package translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import ch.qos.logback.core.net.SyslogOutputStream;
import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import layer.awareness.net.Token;
import layer.semantic.StateOfWorld;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.MultipleExpansionNode;
import pmr.probexp.NormalExpansionNode;

public class JasonExpansionNode {
	public static Term object_to_term(ExpansionNode exp) {
		
		MultipleExpansionNode temp;
		Structure map = new Structure("map", 2);
		Structure term = new Structure("expansionNode",6);
		
		Term source = JasonENode.object_to_term(exp.getSource());
		
		ListTermImpl dest_list = new ListTermImpl();
		if (exp.getDestination() != null) {
			for (ENode t : exp.getDestination()) {
				Term token_term = JasonENode.object_to_term(t);
				dest_list.add(token_term);
			}			
		}

		NumberTermImpl score = new NumberTermImpl(exp.getScore());
		StringTermImpl capability = new StringTermImpl(exp.getCapability());
		
		if(exp instanceof MultipleExpansionNode){
			temp = (MultipleExpansionNode)exp;
			Iterator i = temp.getScenarioMap().entrySet().iterator();
			ListTermImpl scenarioKey = new ListTermImpl();
			ListTermImpl scenarioValue = new ListTermImpl();
			while(i.hasNext()){
				Entry<ENode, String> entry =(Entry<ENode, String>) i.next();
				Term key = JasonENode.object_to_term(entry.getKey());
				scenarioKey.add(key);
				Structure value = new Structure(entry.getValue());
				scenarioValue.add(value);
			}
			map.addTerm(scenarioKey);
			map.addTerm(scenarioValue);
		}
		else
			map = null;
		

		StringTermImpl agent = new StringTermImpl(exp.getAgent());
		
		term.addTerm(source);
		term.addTerm(dest_list);
		term.addTerm(capability);
		term.addTerm(score);
		term.addTerm(map);
		term.addTerm(agent);
		
		return term;
	}

	public static ExpansionNode term_to_object(Term exp) throws TranslateError {
		ENode source = null;
		ArrayList<ENode> dest = new ArrayList<>();
		int score;
		HashMap<ENode, String> scenarioMap = new HashMap<>();
		
		if (!exp.isStructure()) {
			throw new TranslateError();
		} else {
			
			Structure s = (Structure) exp;
			
			try{
			source = JasonENode.term_to_object(s.getTerm(0));
			}catch(TranslateError e1){throw new TranslateError();}
			
			ListTermImpl destIterator = (ListTermImpl) s.getTerm(1);
			List<Term> iterator = destIterator.getAsList();
			for(Term temp : iterator){
				dest.add(JasonENode.term_to_object(temp));
			}
			
			NumberTermImpl number =(NumberTermImpl) s.getTerm(3);
			score = (int)number.solve();
			
			StringTermImpl tempCap = (StringTermImpl) s.getTerm(2);
			String capability = tempCap.getString();
			
			StringTermImpl tempAgent = (StringTermImpl) s.getTerm(5);
			String agent = tempAgent.getString();
			
			if(s.getTerm(4) == null)
				return new NormalExpansionNode(source, dest, capability, score, agent);
			else{
				Structure map =(Structure) s.getTerm(4);
				ListTermImpl keyList = (ListTermImpl) map.getTerm(0);
				ListTermImpl valueList = (ListTermImpl) map.getTerm(1);
				List<Term> values = valueList.getAsList();
				List<Term> keys = keyList.getAsList();
				
				for(int i = 0; i<keys.size(); i++){
					Term tempKey = keyList.get(i);
					StringTermImpl tempValue = (StringTermImpl)valueList.get(i);
					scenarioMap.put(JasonENode.term_to_object(tempKey),tempValue.getString() );
				}
				return new MultipleExpansionNode(source, dest, capability, score, scenarioMap, agent);
			}
		}
	}

	public static ExpansionNode term_string_to_object(String exp_string) throws TranslateError {
		Structure t = Structure.parse(exp_string);
		return term_to_object(t);
	}

}

