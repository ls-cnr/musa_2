package org.icar.specification.ACLanguage;

import java.util.Collection;

import org.icar.musa.agent_communication.translator.ExtDLPHead;
import org.icar.musa.core.context.evolution.AddStatement;
import org.icar.musa.core.context.evolution.RemoveAnyStatement;
import org.icar.musa.core.context.evolution.RemoveStatement;
import org.icar.specification.ACLanguage.capabilityParser.AtomContext;
import org.icar.specification.ACLanguage.capabilityParser.EvolutionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyAddActionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyAtomConstantTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyAtomContext;
import org.icar.specification.ACLanguage.capabilityParser.MyAtomFunctionalTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyAtomPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyBracketedPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyCapBodyContext;
import org.icar.specification.ACLanguage.capabilityParser.MyCapabilityContext;
import org.icar.specification.ACLanguage.capabilityParser.MyClearActionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyCompPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyConjunctionPredicateFormulaContext;
import org.icar.specification.ACLanguage.capabilityParser.MyConstantTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyDisjunctionPredicateFormulaContext;
import org.icar.specification.ACLanguage.capabilityParser.MyEmptyTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyEvoScenarioContext;
import org.icar.specification.ACLanguage.capabilityParser.MyExistsPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyFalseConstantContext;
import org.icar.specification.ACLanguage.capabilityParser.MyForAllPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyFunctionTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyMultiActionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyMultiTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyMultiVariableContext;
import org.icar.specification.ACLanguage.capabilityParser.MyNegPredicateContext;
import org.icar.specification.ACLanguage.capabilityParser.MyNotPred1Context;
import org.icar.specification.ACLanguage.capabilityParser.MyNotPred2Context;
import org.icar.specification.ACLanguage.capabilityParser.MyNumericTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyPostconditionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyPreconditionContext;
import org.icar.specification.ACLanguage.capabilityParser.MyPredicateFormulaContext;
import org.icar.specification.ACLanguage.capabilityParser.MyQuantPredicateFormulaContext;
import org.icar.specification.ACLanguage.capabilityParser.MyRemoveActionContext;
import org.icar.specification.ACLanguage.capabilityParser.MySimpleTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MySingleActionContext;
import org.icar.specification.ACLanguage.capabilityParser.MySingleVariableContext;
import org.icar.specification.ACLanguage.capabilityParser.MyStringTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyStructureContext;
import org.icar.specification.ACLanguage.capabilityParser.MyStructureFunctionalTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyTrueConstantContext;
import org.icar.specification.ACLanguage.capabilityParser.MyTrueFalseTermContext;
import org.icar.specification.ACLanguage.capabilityParser.MyVariableTermContext;
import org.icar.specification.ACLanguage.capabilityParser.Numeral_termContext;
import org.icar.specification.ACLanguage.capabilityParser.String_termContext;
import org.icar.specification.ACLanguage.capabilityParser.Variable_termContext;
import org.icar.specification.ACLanguage.model.CapBody;
import org.icar.specification.ACLanguage.model.CapConditionTerms;
import org.icar.specification.ACLanguage.model.CapEvoAction;
import org.icar.specification.ACLanguage.model.CapEvoActionList;
import org.icar.specification.ACLanguage.model.CapEvoScenario;
import org.icar.specification.ACLanguage.model.CapRelationFormula;
import org.icar.specification.ACLanguage.model.CapTerm;
import org.icar.specification.ACLanguage.model.CapVarList;
import org.icar.specification.ACLanguage.model.Capability;
import org.icar.specification.ACLanguage.model.CapabilityEvolution;

import net.sf.tweety.commons.util.Pair;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.FunctionalTerm;
import net.sf.tweety.logics.commons.syntax.Functor;
import net.sf.tweety.logics.commons.syntax.NumberTerm;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.Disjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.ForallQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.logics.translators.aspfol.AspFolTranslator;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class myCapabilityVisitor extends capabilityBaseVisitor<CapabilityEntity> {

	@Override
	public CapabilityEntity visitMyCapability(MyCapabilityContext ctx) {
		String name = ctx.atom().getText();
		CapBody body = (CapBody) visit(ctx.cap_body());
		Capability cap = new Capability(name);
		cap.setPre(body.getPre());
		cap.setPost(body.getPost());
		cap.setEvo(body.getEvo());
		return cap;
	}

	@Override
	public CapabilityEntity visitMyCapBody(MyCapBodyContext ctx) {
		CapRelationFormula pre = (CapRelationFormula) visit(ctx.precondition());
		CapRelationFormula post = (CapRelationFormula) visit(ctx.postcondition());
		CapabilityEvolution evo = new CapabilityEvolution();
		for (EvolutionContext evo_ctx : ctx.evolution()) {
			CapEvoScenario scenario = (CapEvoScenario) visit(evo_ctx);
			evo.getScenarios().add(scenario);
		}
		CapBody body = new CapBody();
		body.setPre(pre);
		body.setPost(post);
		body.setEvo(evo);
		return body;
	}

	@Override
	public CapabilityEntity visitMyPrecondition(MyPreconditionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyPrecondition(ctx);
	}

	@Override
	public CapabilityEntity visitMyPostcondition(MyPostconditionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyPostcondition(ctx);
	}

	@Override
	public CapabilityEntity visitMyEvoScenario(MyEvoScenarioContext ctx) {
		String name = ctx.atom().getText();
		CapEvoActionList actions = (CapEvoActionList) visit(ctx.evo_action_list());
		CapEvoScenario s = new CapEvoScenario(name);
		s.setActions( actions );
		return s;
	}

	@Override
	public CapabilityEntity visitMySingleAction(MySingleActionContext ctx) {
		CapEvoActionList l = new CapEvoActionList();
		CapEvoAction a = (CapEvoAction) visit(ctx.action());
		l.getActions().add(a);
		return l;
	}

	@Override
	public CapabilityEntity visitMyMultiAction(MyMultiActionContext ctx) {
		CapEvoActionList l = new CapEvoActionList();
		CapEvoAction a = (CapEvoAction) visit(ctx.action());
		l.getActions().add(a);
		
		CapEvoActionList others = (CapEvoActionList) visit(ctx.evo_action_list());
		l.getActions().addAll(others.getActions());
		
		return l;
	}

	@Override
	public CapabilityEntity visitMyAddAction(MyAddActionContext ctx) {
		CapRelationFormula f = (CapRelationFormula) visit(ctx.predicate());
		
		AspFolTranslator tx = new AspFolTranslator();
		DLPAtom a = tx.toASP((FOLAtom) f.getFormula());
		CapEvoAction act = new AddStatement(new ExtDLPHead(a));
		
		return act;
	}

	@Override
	public CapabilityEntity visitMyRemoveAction(MyRemoveActionContext ctx) {
		CapRelationFormula f = (CapRelationFormula) visit(ctx.predicate());
		
		AspFolTranslator tx = new AspFolTranslator();
		DLPAtom a = tx.toASP((FOLAtom) f.getFormula());
		CapEvoAction act = new RemoveStatement(new ExtDLPHead(a));
		
		return act;
	}

	@Override
	public CapabilityEntity visitMyClearAction(MyClearActionContext ctx) {
		String f = ctx.atom().getText();
		CapEvoAction act = new RemoveAnyStatement(f);
		
		return act;
	}

	@Override
	public CapabilityEntity visitMyPredicateFormula(MyPredicateFormulaContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyPredicateFormula(ctx);
	}

	@Override
	public CapabilityEntity visitMyQuantPredicateFormula(MyQuantPredicateFormulaContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyQuantPredicateFormula(ctx);
	}

	@Override
	public CapabilityEntity visitMyForAllPredicate(MyForAllPredicateContext ctx) {
		CapRelationFormula c = (CapRelationFormula) visit(ctx.predicate_formula());
		CapVarList v = (CapVarList) visit(ctx.var_list());
		ForallQuantifiedFormula t = new ForallQuantifiedFormula(c.getFormula(),v.getVar_list());
		return new CapRelationFormula(t);
	}

	@Override
	public CapabilityEntity visitMyExistsPredicate(MyExistsPredicateContext ctx) {
		CapRelationFormula c = (CapRelationFormula) visit(ctx.predicate_formula());
		CapVarList v = (CapVarList) visit(ctx.var_list());
		ExistsQuantifiedFormula t = new ExistsQuantifiedFormula(c.getFormula(),v.getVar_list());
		return new CapRelationFormula(t);
	}

	@Override
	public CapabilityEntity visitMyNegPredicate(MyNegPredicateContext ctx) {
		CapRelationFormula c = (CapRelationFormula) visit(ctx.neg_predicate());
		Negation n = new Negation(c.getFormula());
		return new CapRelationFormula(n);
	}

	@Override
	public CapabilityEntity visitMyNotPred1(MyNotPred1Context ctx) {
		// TODO Auto-generated method stub
		return super.visitMyNotPred1(ctx);
	}

	@Override
	public CapabilityEntity visitMyNotPred2(MyNotPred2Context ctx) {
		// TODO Auto-generated method stub
		return super.visitMyNotPred2(ctx);
	}

	@Override
	public CapabilityEntity visitMyCompPredicate(MyCompPredicateContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyCompPredicate(ctx);
	}

	@Override
	public CapabilityEntity visitMyAtomPredicate(MyAtomPredicateContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyAtomPredicate(ctx);
	}

	@Override
	public CapabilityEntity visitMyDisjunctionPredicateFormula(MyDisjunctionPredicateFormulaContext ctx) {
		CapRelationFormula c1 = (CapRelationFormula) visit(ctx.predicate_formula().get(0));
		CapRelationFormula c2 = (CapRelationFormula) visit(ctx.predicate_formula().get(1));
		Disjunction c = new Disjunction(c1.getFormula(),c2.getFormula());
		return new CapRelationFormula(c);
	}

	@Override
	public CapabilityEntity visitMyConjunctionPredicateFormula(MyConjunctionPredicateFormulaContext ctx) {
		CapRelationFormula c1 = (CapRelationFormula) visit(ctx.predicate_formula().get(0));
		CapRelationFormula c2 = (CapRelationFormula) visit(ctx.predicate_formula().get(1));
		Conjunction c = new Conjunction(c1.getFormula(),c2.getFormula());
		return new CapRelationFormula(c);
	}


	@Override
	public CapabilityEntity visitMyBracketedPredicate(MyBracketedPredicateContext ctx) {
		// TODO Auto-generated method stub
		return visit(ctx.predicate_formula());
	}

	@Override
	public CapabilityEntity visitMyAtom(MyAtomContext ctx) {
		String functor = ctx.atom().getText();
		Predicate p = new Predicate(functor,0);
		return new CapRelationFormula(new FOLAtom(p) );
	}

	@Override
	public CapabilityEntity visitMyStructure(MyStructureContext ctx) {
		String functor = ctx.atom().getText();		
		CapConditionTerms terms = (CapConditionTerms) visit(ctx.termlist());
		Predicate p = new Predicate(functor,terms.getTerms().size());
		return new CapRelationFormula(new FOLAtom(p,terms.getTerms()) );
	}

	@Override
	public CapabilityEntity visitMyEmptyTerm(MyEmptyTermContext ctx) {
		CapConditionTerms t = new CapConditionTerms();
		return t;
	}

	@Override
	public CapabilityEntity visitMyMultiTerm(MyMultiTermContext ctx) {
		CapConditionTerms terms = new CapConditionTerms();		
		
		CapTerm t = (CapTerm) visit(ctx.term());
		terms.getTerms().add(t.getTerm());

		if (ctx.termlist() != null) {
			CapConditionTerms others = (CapConditionTerms) visit(ctx.termlist());
			terms.getTerms().addAll(others.getTerms());
		}
		return terms;
	}

	@Override
	public CapabilityEntity visitMySimpleTerm(MySimpleTermContext ctx) {
		CapConditionTerms terms = new CapConditionTerms();			
		CapTerm t = (CapTerm) visit(ctx.term());
		terms.getTerms().add(t.getTerm());
		return terms;
	}

	@Override
	public CapabilityEntity visitMyTrueFalseTerm(MyTrueFalseTermContext ctx) {
		Term<String> t = new Constant(ctx.getText());
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyConstantTerm(MyConstantTermContext ctx) {
		Term<String> t = new Constant(ctx.getText());
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyNumericTerm(MyNumericTermContext ctx) {
		String num_string = ctx.getText();
		NumberTerm t = new NumberTerm(num_string);
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyVariableTerm(MyVariableTermContext ctx) {
		String name = ctx.getText();
		Term<String> t =new Variable(name);
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyFunctionTerm(MyFunctionTermContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyFunctionTerm(ctx);
	}

	@Override
	public CapabilityEntity visitMyStringTerm(MyStringTermContext ctx) {
		String string = ctx.getText();
		Term<String> t = new Constant(string);
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyTrueConstant(MyTrueConstantContext ctx) {
		Term<String> t = new Constant("true");
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyFalseConstant(MyFalseConstantContext ctx) {
		Term<String> t = new Constant("false");
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyAtomFunctionalTerm(MyAtomFunctionalTermContext ctx) {
		Term<Pair<Functor,java.util.List<Term<?>>>> t;
		String functor = ctx.atom().getText();
		
		Functor f = new Functor(functor,0);
		t = new FunctionalTerm(f);
			
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitMyStructureFunctionalTerm(MyStructureFunctionalTermContext ctx) {
		Term<Pair<Functor,java.util.List<Term<?>>>> t;
		String functor = ctx.atom().getText();
		
		CapConditionTerms terms = (CapConditionTerms) visit(ctx.termlist());
		
		Functor f = new Functor(functor,terms.getTerms().size());
		t = new FunctionalTerm(f,terms.getTerms());
			
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitVariable_term(Variable_termContext ctx) {
		String name = ctx.getText();
		Term<String> t =new Variable(name);
		return new CapTerm(t);
	}

	@Override
	public CapabilityEntity visitNumeral_term(Numeral_termContext ctx) {
		// TODO Auto-generated method stub
		return super.visitNumeral_term(ctx);
	}

	@Override
	public CapabilityEntity visitString_term(String_termContext ctx) {
		// TODO Auto-generated method stub
		return super.visitString_term(ctx);
	}

	@Override
	public CapabilityEntity visitAtom(AtomContext ctx) {
		// TODO Auto-generated method stub
		return super.visitAtom(ctx);
	}

	@Override
	public CapabilityEntity visitMyAtomConstantTerm(MyAtomConstantTermContext ctx) {
		// TODO Auto-generated method stub
		return super.visitMyAtomConstantTerm(ctx);
	}

	@Override
	public CapabilityEntity visitMySingleVariable(MySingleVariableContext ctx) {
		CapVarList v = new CapVarList();
		
		CapTerm t = (CapTerm) visit(ctx.variable_term());
		v.getVar_list().add((Variable) t.getTerm());
		
		return v;
	}

	@Override
	public CapabilityEntity visitMyMultiVariable(MyMultiVariableContext ctx) {
		CapVarList v = new CapVarList();
		
		CapVarList others = (CapVarList) visit(ctx.var_list());
		v.getVar_list().addAll(others.getVar_list());
				
		CapTerm t = (CapTerm) visit(ctx.variable_term());
		v.getVar_list().add((Variable) t.getTerm());
		
		return v;
	}
	
	
	
	
}
