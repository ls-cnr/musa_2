package org.icar.linear_temporal_logic.hierarchical_ltl_model;

import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.AndOperator;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.FinallyPN;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.GloballyPN;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.OrOperator;
import org.icar.linear_temporal_logic.hierarchical_ltl_model.template.UntilPN;
import org.icar.musa.context.fol_reasoner.FOLCondition;
import org.icar.specification.LTLgoal.specmodel.BinaryFormula;
import org.icar.specification.LTLgoal.specmodel.LTLFormula;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;
import org.icar.specification.LTLgoal.specmodel.LTLPredicate;
import org.icar.specification.LTLgoal.specmodel.UnaryFormula;


public class NetHierarchyBuilder {
	private int suff = 0;
	
	public NetHierarchy build(LTLGoal goal) {
		HierarchyNode root = build_from_formula(goal.getFormula());
		root.init();
		NetHierarchy h = new NetHierarchy(root);
		return h;
	}

	private HierarchyNode build_from_formula(LTLFormula formula) {
		if (formula instanceof LTLPredicate) {
			return build_from_predicate_node((LTLPredicate) formula);
		} else if (formula instanceof UnaryFormula) {
			return build_from_unary_operator_node((UnaryFormula) formula);
		} else if (formula instanceof BinaryFormula) {
			return build_from_binary_operator_node((BinaryFormula) formula);
		}	

		return null;
	}

	private HierarchyNode build_from_binary_operator_node(BinaryFormula formula) {
		if (formula.isAnd()) {
			HierarchyNode left = build_from_formula(formula.getLeft());
			HierarchyNode right = build_from_formula(formula.getRight());
			return new AndOperator("a"+suff(), left, right);
		} else if (formula.isOr()) {
			HierarchyNode left = build_from_formula(formula.getLeft());
			HierarchyNode right = build_from_formula(formula.getRight());
			return new OrOperator("o"+suff(), left, right);
		} else if (formula.isUntil()) {
			HierarchyNode left = build_from_formula(formula.getLeft());
			HierarchyNode right = build_from_formula(formula.getRight());
			return new UntilPN("u"+suff(), left, right);
		}  
		
		return null;
	}

	private HierarchyNode build_from_unary_operator_node(UnaryFormula formula) {
		if (formula.isGlobally()) {
			HierarchyNode sub = build_from_formula(formula.getSubformula());
			return new GloballyPN("g"+suff(), sub);
		} else if (formula.isFinally()) {
			HierarchyNode sub = build_from_formula(formula.getSubformula());
			return new FinallyPN("f"+suff(), sub);
		} 
		return null;
	}

	private HierarchyNode build_from_predicate_node(LTLPredicate formula) {
		String pred = formula.getPredicate();
		return new PredicateNode("pn"+suff(), new FOLCondition( pred ));
	}
	
	private String suff() {
		return ""+suff++;
	}


}
