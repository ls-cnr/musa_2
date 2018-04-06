package org.icar.specification.LTLgoal;

import org.icar.specification.LTLgoal.goalParser.APredicateContext;
import org.icar.specification.LTLgoal.goalParser.AlwaysFalseContext;
import org.icar.specification.LTLgoal.goalParser.AlwaysTrueContext;
import org.icar.specification.LTLgoal.goalParser.AndBinaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.BracketedContext;
import org.icar.specification.LTLgoal.goalParser.FinallyUnaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.GloballyUnaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.Goal_modelContext;
import org.icar.specification.LTLgoal.goalParser.GoalpriorityContext;
import org.icar.specification.LTLgoal.goalParser.IfBinaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.IffBinaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.Ltl_extended_listContext;
import org.icar.specification.LTLgoal.goalParser.NextUnaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.NotUnaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.OrBinaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.ReleaseBinaryOperatorContext;
import org.icar.specification.LTLgoal.goalParser.Single_goalContext;
import org.icar.specification.LTLgoal.goalParser.UntilBinaryOperatorContext;
import org.icar.specification.LTLgoal.specmodel.BinaryFormula;
import org.icar.specification.LTLgoal.specmodel.GoalModel;
import org.icar.specification.LTLgoal.specmodel.LTLFormula;
import org.icar.specification.LTLgoal.specmodel.LTLGoal;
import org.icar.specification.LTLgoal.specmodel.LTLPredicate;
import org.icar.specification.LTLgoal.specmodel.UnaryFormula;

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
		
		LTLGoal goal = new LTLGoal(ctx.atom().getText(), formula, priority);
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
	public GoalModelEntity visitNextUnaryOperator(NextUnaryOperatorContext ctx) {
		LTLFormula subformula  = (LTLFormula) visit(ctx.formula());
		
		return new UnaryFormula(UnaryFormula.NEXT, subformula);
	}

	@Override
	public GoalModelEntity visitGloballyUnaryOperator(GloballyUnaryOperatorContext ctx) {
		LTLFormula subformula  = (LTLFormula) visit(ctx.formula());
		
		return new UnaryFormula(UnaryFormula.GLOBALLY, subformula);
	}

	@Override
	public GoalModelEntity visitFinallyUnaryOperator(FinallyUnaryOperatorContext ctx) {
		LTLFormula subformula  = (LTLFormula) visit(ctx.formula());
		
		return new UnaryFormula(UnaryFormula.FINALLY, subformula);
	}

	@Override
	public GoalModelEntity visitNotUnaryOperator(NotUnaryOperatorContext ctx) {
		LTLFormula subformula  = (LTLFormula) visit(ctx.formula());
		
		return new UnaryFormula(UnaryFormula.NOT, subformula);
	}


	
	@Override
	public GoalModelEntity visitAndBinaryOperator(AndBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.AND,  left,right);
	}

	@Override
	public GoalModelEntity visitOrBinaryOperator(OrBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.OR,  left,right);
	}

	@Override
	public GoalModelEntity visitIfBinaryOperator(IfBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.IF,  left,right);
	}

	@Override
	public GoalModelEntity visitIffBinaryOperator(IffBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.IFF,  left,right);
	}

	@Override
	public GoalModelEntity visitUntilBinaryOperator(UntilBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.UNTIL,  left,right);
	}

	@Override
	public GoalModelEntity visitReleaseBinaryOperator(ReleaseBinaryOperatorContext ctx) {
		LTLFormula left  = (LTLFormula) visit(ctx.formula(0));
		LTLFormula right  = (LTLFormula) visit(ctx.formula(1));
			
		return new BinaryFormula(BinaryFormula.RELEASE,  left,right);
	}
		
}
