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
		
		if(exp == null)	 {
			return new Structure("null_expansion",0);
		}
		
		MultipleExpansionNode temp;
		Structure map = new Structure("map", 2);
		Structure term = new Structure("expansionNode",5);
		
		Term source = JasonENode.object_to_term(exp.getSource());
		
		ListTermImpl dest_list = new ListTermImpl();
		ListTermImpl scenarioValue = new ListTermImpl();
		
		
		if (exp.getDestination() != null) {
			
			if(exp instanceof MultipleExpansionNode){
				MultipleExpansionNode multiexp = (MultipleExpansionNode)exp;
				for (ENode t : multiexp.getDestination()) {
					Term enodeTerm = JasonENode.object_to_term(t);
					dest_list.add(enodeTerm);
						StringTermImpl value = new StringTermImpl(multiexp.getScenario(t));
						scenarioValue.add(value);
					}
				}			
		else{
			for (ENode t : exp.getDestination()) {
				Term enodeTerm = JasonENode.object_to_term(t);
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

	public static ExpansionNode term_to_object(Term exp) throws TranslateError {
		
		if(exp == null)	return null;
		
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
					ENode putnode = JasonENode.term_to_object(temp);
					dest.add(putnode);
					StringTermImpl tempValue = (StringTermImpl)sceniterator.get(i);
					scenarioMap.put(putnode,tempValue.getString());
				}
				return new MultipleExpansionNode(source, dest, capability, score, agent, scenarioMap);
			}
			else{
				Term temp = iterator.get(0);
				ENode putnode = JasonENode.term_to_object(temp);
				dest.add(putnode);
				return new NormalExpansionNode(source, dest, capability, score, agent);			
			}

		}
	}

	public static ExpansionNode term_string_to_object(String exp_string) throws TranslateError {
		Structure t = Structure.parse(exp_string);
		return term_to_object(t);
	}

}

