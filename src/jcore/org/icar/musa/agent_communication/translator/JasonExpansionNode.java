package org.icar.musa.agent_communication.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.MultipleExpansion;
import org.icar.musa.proactive_means_end_reasoning.NormalExpansion;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

public class JasonExpansionNode {
	public static Term object_to_term(GraphExpansion exp) {
		
		if(exp == null)	 {
			return new Structure("null_expansion",0);
		}
		
		MultipleExpansion temp;
		Structure map = new Structure("map", 2);
		Structure term = new Structure("expansionNode",5);
		
		Term source = JasonExtNode.object_to_term(exp.getSource());
		
		ListTermImpl dest_list = new ListTermImpl();
		ListTermImpl scenarioValue = new ListTermImpl();
		
		
		if (exp.getDestination() != null) {
			
			if(exp instanceof MultipleExpansion){
				MultipleExpansion multiexp = (MultipleExpansion)exp;
				for (ExtendedNode t : multiexp.getDestination()) {
					Term enodeTerm = JasonExtNode.object_to_term(t);
					dest_list.add(enodeTerm);
						StringTermImpl value = new StringTermImpl(multiexp.getScenario(t));
						scenarioValue.add(value);
					}
				}			
		else{
			for (ExtendedNode t : exp.getDestination()) {
				Term enodeTerm = JasonExtNode.object_to_term(t);
				dest_list.add(enodeTerm);
				StringTermImpl value = new StringTermImpl("Simple");
				scenarioValue.add(value);
				}
			}
		}
		map.addTerm(dest_list);
		map.addTerm(scenarioValue);

		NumberTermImpl score = new NumberTermImpl(exp.getScore());
		StringTermImpl capability = new StringTermImpl(exp.getCapability());
		

		StringTermImpl agent = new StringTermImpl(exp.getAgent());
		
		term.addTerm(source);
		term.addTerm(capability);
		term.addTerm(score);
		term.addTerm(map);
		term.addTerm(agent);
		
		return term;
	}

	public static GraphExpansion term_to_object(Term exp) throws TranslateError {
		
		if(exp == null)	return null;
		
		ExtendedNode source = null;
		ArrayList<ExtendedNode> dest = new ArrayList<>();
		int score;
		HashMap<ExtendedNode, String> scenarioMap = new HashMap<>();
		
		if (!exp.isStructure()) {
			throw new TranslateError();
		} else {
			
			Structure s = (Structure) exp;
			
			try{
				source = JasonExtNode.term_to_object(s.getTerm(0));
			}catch(TranslateError e1){throw new TranslateError();}
			
			
			StringTermImpl tempCap = (StringTermImpl) s.getTerm(1);
			String capability = tempCap.getString();
			
			
			NumberTermImpl number =(NumberTermImpl) s.getTerm(2);
			score = (int)number.solve();
			
			
			StringTermImpl tempAgent = (StringTermImpl) s.getTerm(4);
			String agent = tempAgent.getString();
			
			
			Structure destScenario =(Structure) s.getTerm(3);
			ListTermImpl destIterator = (ListTermImpl) destScenario.getTerm(0);
			ListTermImpl scenarioiterator = (ListTermImpl) destScenario.getTerm(1);
			List<Term> iterator = destIterator.getAsList();
			List<Term> sceniterator = scenarioiterator.getAsList();
			
			if(scenarioiterator.size() != 1){
				for(int i = 0 ; i<iterator.size(); i++){
					Term temp = iterator.get(i);
					ExtendedNode putnode = JasonExtNode.term_to_object(temp);
					dest.add(putnode);
					StringTermImpl tempValue = (StringTermImpl)sceniterator.get(i);
					scenarioMap.put(putnode,tempValue.getString());
				}
				return new MultipleExpansion(source, dest, capability, score, agent, scenarioMap);
			}
			else{
				Term temp = iterator.get(0);
				ExtendedNode putnode = JasonExtNode.term_to_object(temp);
				dest.add(putnode);
				return new NormalExpansion(source, dest, capability, score, agent);			
			}

		}
	}

	public static GraphExpansion term_string_to_object(String exp_string) throws TranslateError {
		Structure t = Structure.parse(exp_string);
		return term_to_object(t);
	}

}

