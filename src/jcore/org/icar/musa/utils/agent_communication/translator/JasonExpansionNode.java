package org.icar.musa.utils.agent_communication.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.icar.musa.pmr.problem_exploration.CapabilityEdge;
import org.icar.musa.pmr.problem_exploration.ScenarioEdge;
import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;
import org.icar.musa.utils.exception.TranslateError;

import jason.asSyntax.ListTermImpl;
import jason.asSyntax.NumberTermImpl;
import jason.asSyntax.StringTermImpl;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;

public class JasonExpansionNode {
	public static Term object_to_term(WTSExpansion exp) {
		
		if(exp == null)	 {
			return new Structure("null_exp",0);
		}
		
		Structure term = null;
		if (exp.isMulti_expansion()==false) {
			term = new Structure("single_exp",6);

			Term source = JasonExtNode.object_to_term(exp.getRoot());
			term.addTerm(source);
			
			Term dest = JasonExtNode.object_to_term(exp.getEvolutionNodes().get(0));
			term.addTerm(dest);
			
		} else {
			term = new Structure("multi_exp",6);
			
			Term source = JasonExtNode.object_to_term(exp.getRoot());
			term.addTerm(source);

			XorNode xornode = exp.getXorNode();
			ArrayList<WTSNode> dests = exp.getEvolutionNodes();
			ListTermImpl dest_list = new ListTermImpl();
			for (WTSNode dest_node : dests) {
				StateNode node = (StateNode) dest_node;
				Structure evoterm = new Structure("evo",2);
				
				ScenarioEdge scen = (ScenarioEdge) exp.getEdge(xornode, dest_node);
				evoterm.addTerm(new StringTermImpl(scen.getScenario()));
				
				Term term_node = JasonExtNode.object_to_term(node);
				evoterm.addTerm(term_node);
				
				dest_list.add(evoterm);
			}
			term.addTerm(dest_list);
		}
		
		
		String capability = exp.getCapability();
		StringTermImpl capvalue = new StringTermImpl(capability);
		term.addTerm(capvalue);
		
		double score = exp.getScore();
		NumberTermImpl score_term = new NumberTermImpl(score);
		term.addTerm(score_term);
				
		boolean contain_exit = exp.isContain_exit();
		StringTermImpl contain_exitvalue;
		if (contain_exit) 
			contain_exitvalue = new StringTermImpl("exit");
		else
			contain_exitvalue = new StringTermImpl("normal");
		term.addTerm(contain_exitvalue);
				
		boolean contain_forbidden= exp.isContain_forbidden();
		StringTermImpl contain_forbiddenvalue;
		if (contain_forbidden) 
			contain_forbiddenvalue = new StringTermImpl("forbidden");
		else
			contain_forbiddenvalue = new StringTermImpl("allowed");
		term.addTerm(contain_forbiddenvalue);
		
		return term;
	}

	
	public static WTSExpansion term_to_object(Term exp) throws TranslateError {
		
		if(exp == null)	return null;
		
		
		if (!exp.isStructure()) {
			throw new TranslateError();
		} 
			
		Structure s = (Structure) exp;
		WTSNode source = JasonExtNode.term_to_object(s.getTerm(0));
		
		StringTermImpl tempCap = (StringTermImpl) s.getTerm(2);
		String capability = tempCap.getString();
		CapabilityEdge main_edge = new CapabilityEdge();
		main_edge.setCapabilityName(capability);
		main_edge.setAgentProvider("myAgent");

		WTSExpansion expansion = new WTSExpansion(capability, (StateNode) source);
		
		if (s.getFunctor().equals("single_exp")) {
			Term t = s.getTerm(1);
			WTSNode dest = JasonExtNode.term_to_object(t);
			
			expansion.addVertex(dest);
			expansion.addEdge(source, dest,main_edge);
			
			expansion.setMulti_expansion(false);
		} else {
			XorNode xor = new XorNode();
			expansion.addVertex(xor);
			expansion.addEdge(source, xor,main_edge);
			
			ListTermImpl destIterator = (ListTermImpl) s.getTerm(1);
			
			List<Term> iterator = destIterator.getAsList();
			for (Term t : iterator) {
				Structure evo = (Structure) t;

				WTSNode dest = JasonExtNode.term_to_object(evo.getTerm(1));
				expansion.addVertex(dest);
				
				StringTermImpl tempScen = (StringTermImpl) evo.getTerm(0);
				String scen_name = tempScen.getString();
				ScenarioEdge secondary_edge = new ScenarioEdge(scen_name);
				expansion.addEdge(xor, dest,secondary_edge);

				expansion.setMulti_expansion(true);
			}
		}
			
		NumberTermImpl number =(NumberTermImpl) s.getTerm(3);
		double score = number.solve();
		expansion.setScore(score);

		boolean contain_exit=false;
		StringTermImpl contain_exit_term = (StringTermImpl) s.getTerm(4);
		String contain_exit_string = contain_exit_term.getString();
		if (contain_exit_string.equals("exit"))
			contain_exit=true;
		expansion.setContain_exit(contain_exit);

		boolean contain_forbidden=false;
		StringTermImpl contain_forbidden_term = (StringTermImpl) s.getTerm(5);
		String contain_forbidden_string = contain_forbidden_term.getString();
		if (contain_forbidden_string.equals("forbidden"))
			contain_forbidden=true;
		expansion.setContain_forbidden(contain_forbidden);
		
		return expansion;
	}

	public static WTSExpansion term_string_to_object(String exp_string) throws TranslateError {
		Structure t = Structure.parse(exp_string);
		return term_to_object(t);
	}

}

