package org.icar.musa.utils.agent_communication.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSExpansion;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;

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
		
		Structure term = new Structure("exp",8);
		
		Term source = JasonExtNode.object_to_term(exp.getRoot());
		term.addTerm(source);
		
		XorNode xornode = exp.getXorNode();
		Term xor_term = JasonExtNode.object_to_term(xornode);
		term.addTerm(source);
		
		ArrayList<WTSNode> dests = exp.getEvolutionNodes();
		ListTermImpl dest_list = new ListTermImpl();
		for (WTSNode dest_node : dests) {
			StateNode node = (StateNode) dest_node;
			Term term_node = JasonExtNode.object_to_term(node);
			dest_list.add(term_node);
		}

		String capability = exp.getCapability();
		StringTermImpl capvalue = new StringTermImpl(capability);
		term.addTerm(capvalue);
		
		double score = exp.getScore();
		NumberTermImpl score_term = new NumberTermImpl(score);
		term.addTerm(score_term);
		
		boolean is_multi_exp = exp.isMulti_expansion();
		StringTermImpl is_multi_expvalue;
		if (is_multi_exp) 
			is_multi_expvalue = new StringTermImpl("is_multi");
		else
			is_multi_expvalue = new StringTermImpl("is_single");
		term.addTerm(is_multi_expvalue);
		
		boolean contain_forbidden= exp.isContain_forbidden();
		StringTermImpl contain_forbiddenvalue;
		if (contain_forbidden) 
			contain_forbiddenvalue = new StringTermImpl("forbidden");
		else
			contain_forbiddenvalue = new StringTermImpl("allowed");
		term.addTerm(contain_forbiddenvalue);
		
		boolean contain_exit = exp.isContain_exit();
		StringTermImpl contain_exitvalue;
		if (contain_exit) 
			contain_exitvalue = new StringTermImpl("exit");
		else
			contain_exitvalue = new StringTermImpl("normal");
		term.addTerm(contain_exitvalue);
				
		return term;
	}

	
	public static WTSExpansion term_to_object(Term exp) throws TranslateError {
		
		if(exp == null)	return null;
		
		
		if (!exp.isStructure()) {
			throw new TranslateError();
		} 
			
		Structure s = (Structure) exp;
		if (s.getFunctor().equals("exp")) {
			WTSNode source = JasonExtNode.term_to_object(s.getTerm(0));

			XorNode xor_node = (XorNode) JasonExtNode.term_to_object(s.getTerm(1));
			
			ArrayList<WTSNode> dests = new ArrayList<WTSNode>();
			ListTermImpl destIterator = (ListTermImpl) s.getTerm(2);
			List<Term> iterator = destIterator.getAsList();
			for (Term t : iterator) {
				WTSNode dest = JasonExtNode.term_to_object(t);
				dests.add(dest);
			}
			
			StringTermImpl tempCap = (StringTermImpl) s.getTerm(3);
			String capability = tempCap.getString();
			
			NumberTermImpl number =(NumberTermImpl) s.getTerm(4);
			double score = number.solve();

			boolean is_multi_exp=false;
			StringTermImpl is_multi_exp_term = (StringTermImpl) s.getTerm(5);
			String is_multi_exp_string = is_multi_exp_term.getString();
			if (is_multi_exp_string.equals("is_multi"))
				is_multi_exp=true;

			boolean contain_forbidden=false;
			StringTermImpl contain_forbidden_term = (StringTermImpl) s.getTerm(6);
			String contain_forbidden_string = contain_forbidden_term.getString();
			if (contain_forbidden_string.equals("forbidden"))
				contain_forbidden=true;

			boolean contain_exit=false;
			StringTermImpl contain_exit_term = (StringTermImpl) s.getTerm(7);
			String contain_exit_string = contain_exit_term.getString();
			if (contain_exit_string.equals("forbidden"))
				contain_exit=true;

		}
		
		return null;
	}

	public static WTSExpansion term_string_to_object(String exp_string) throws TranslateError {
		Structure t = Structure.parse(exp_string);
		return term_to_object(t);
	}

}

