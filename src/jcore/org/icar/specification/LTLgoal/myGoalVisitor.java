package org.icar.specification.LTLgoal;

import org.icar.specification.LTLgoal.goalParser.APredicateContext;
import org.icar.specification.LTLgoal.goalParser.AlwaysFalseContext;
import org.icar.specification.LTLgoal.goalParser.AlwaysTrueContext;
import org.icar.specification.LTLgoal.goalParser.Binary_operatorContext;
import org.icar.specification.LTLgoal.goalParser.BracketedContext;
import org.icar.specification.LTLgoal.goalParser.Goal_modelContext;
import org.icar.specification.LTLgoal.goalParser.GoalpriorityContext;
import org.icar.specification.LTLgoal.goalParser.Ltl_extended_listContext;
import org.icar.specification.LTLgoal.goalParser.Single_goalContext;
import org.icar.specification.LTLgoal.goalParser.Unary_operatorContext;
import org.icar.specification.LTLgoal.model.BinaryFormula;
import org.icar.specification.LTLgoal.model.GoalModel;
import org.icar.specification.LTLgoal.model.LTLFormula;
import org.icar.specification.LTLgoal.model.LTLGoal;
import org.icar.specification.LTLgoal.model.LTLPredicate;
import org.icar.specification.LTLgoal.model.UnaryFormula;

public class myGoalVisitor extends goalBaseVisitor<GoalModelEntity>  {

	@Override
	public GoalModelEntity visitGoal_model(Goal_modelContext ctx) {
		return visitLtl_extended_list (ctx.ltl_extended_list());
	}
	
	@Override
	public GoalModelEntity visitLtl_extended_list(Ltl_extended_listContext ctx) {
		GoalModel model = new GoalModel();
		LTLGoal goal = (LTLGoal) visit(ctx.single_goal());
		model.getGoals().add(goal);
		
		if (ctx.ltl_extended_list() != null) {
			GoalModel nextmodel = (GoalModel) visit(ctx.ltl_extended_list());
			model.getGoals().addAll(nextmodel.getGoals());
		}
		
		return model;
	}

	@Override
	public GoalModelEntity visitSingle_goal(Single_goalContext ctx) {
		LTLFormula formula = (LTLFormula) visit(ctx.ltl_extended().formula());
		
		LTLPriority priority = null;
		if (ctx.goalpriority() != null)
			priority = (LTLPriority) visit(ctx.goalpriority());
		
		LTLGoal goal = new LTLGoal(ctx.smallatom().getText(), formula, priority);
		return goal;
	}
	
	@Override
	public GoalModelEntity visitGoalpriority(GoalpriorityContext ctx) {
		LTLPriority priority = null;
		
		Integer num = new Integer(ctx.numeral().getText());
		priority = new LTLPriority(num.intValue());
		
		return priority;
	}

	@Override
	public GoalModelEntity visitAlwaysTrue(AlwaysTrueContext ctx) {
		return new LTLPredicate("true");
	}

	@Override
	public GoalModelEntity visitAlwaysFalse(AlwaysFalseContext ctx) {
		return new LTLPredicate("false");
	}

	@Override
	public GoalModelEntity visitAPredicate(APredicateContext ctx) {
		return new LTLPredicate(ctx.getText());
	}

	@Override
	public GoalModelEntity visitBracketed(BracketedContext ctx) {
		if (ctx.formula()!=null) {
			return visit(ctx.formula());
		}
		return visit(ctx.binary_operator());
	}

	@Override
	public GoalModelEntity visitUnary_operator(Unary_operatorContext ctx) {
		UnaryFormula formula = null;
		
		LTLFormula subformula  = (LTLFormula) visit(ctx.formula());
		
		if (ctx.GLOBALLY() != null) {
			formula = new UnaryFormula(UnaryFormula.GLOBALLY, subformula);
		} else if (ctx.FINALLY() != null) {
			formula = new UnaryFormula(UnaryFormula.FINALLY, subformula);
		} else if (ctx.NEXT() != null) {
			formula = new UnaryFormula(UnaryFormula.NEXT, subformula);
		} else if (ctx.NOT() != null) {
			formula = new UnaryFormula(UnaryFormula.NOT, subformula);
		}

		return formula;
	}

	@Override
	public GoalModelEntity visitBinary_operator(Binary_operatorContext ctx) {
		BinaryFormula formula = null;
		
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
		
		if (ctx.UNTIL() != null) {
			formula = new BinaryFormula(BinaryFormula.UNTIL, left,right);
		} else if (ctx.RELEASE() != null) {
			formula = new BinaryFormula(BinaryFormula.RELEASE,  left,right);
		} else if (ctx.AND() != null) {
			formula = new BinaryFormula(BinaryFormula.AND,  left,right);
		} else if (ctx.OR() != null) {
			formula = new BinaryFormula(BinaryFormula.OR,  left,right);
		} else if (ctx.IF() != null) {
			formula = new BinaryFormula(BinaryFormula.IF,  left,right);
		} else if (ctx.IFF() != null) {
			formula = new BinaryFormula(BinaryFormula.IFF,  left,right);
		}

		return formula;
	}
	
	

}
