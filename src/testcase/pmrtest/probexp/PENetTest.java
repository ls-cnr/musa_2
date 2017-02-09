package pmrtest.probexp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.awareness.Goal;
import layer.awareness.goalmodel.GoalModel;
import layer.awareness.net.Token;
import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.StateOfWorld;
import layer.semantic.evolution.AddStatement;
import layer.semantic.evolution.CapabilityEvolutionScenario;
import layer.semantic.evolution.EvolutionScenario;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.FolFormula;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPHead;
import pmr.graph.WorldNode;
import pmr.probexp.ProblemExploration;

public class PENetTest {

	private AssumptionSet domain;
	private GoalModel model;
	private ProblemExploration problem; 
	

	Variable doc = new Variable("Doc");
	Variable usr = new Variable("Usr");
	Variable mng = new Variable("Mng");
	
	Constant a_user = new Constant("a_user");
	Constant an_order = new Constant("an_order");
	Constant the_user_data = new Constant("the_user_data");
	Constant the_registration_form = new Constant("the_registration_form");
	Constant failure_order = new Constant("failure_order");
	Constant the_invoice = new Constant("the_invoice");
	Constant the_delivery_order = new Constant("the_delivery_order");
	Constant a_storehouse_manager = new Constant("a_storehouse_manager");
	
	@Before
	public void init() {
		domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("role(X) :- user(X).");
			domain.addAssumption_asString("role(X) :- storehouse_manager(X).");
			domain.addAssumption_asString("document(X) :- order(X).");
			domain.addAssumption_asString("document(X) :- invoice(X).");
			domain.addAssumption_asString("document(X) :- user_data(X).");
			domain.addAssumption_asString("document(X) :- registration_form(X).");
			domain.addAssumption_asString("order(X) :- delivery_order(X).");
			domain.addAssumption_asString("processed(X) :- accepted(X), order(X), sent(Y,Z), delivery_order(Y), storehouse_manager(Z).");
			domain.addAssumption_asString("processed(X) :- refused(X), order(X), sent(failure_order,Y), user(Y).");
			domain.addAssumption_asString("notified(X,Y) :- uploaded_on_cloud(X), document(X), has_cloud_space(Y), user(Y).");
			domain.addAssumption_asString("notified(X,Y) :- mailed_perm_link(X,Y), document(X), user(Y).");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		/*************/
		
		/*to_handle_order*/
		FOLAtom THO_received = new FOLAtom( new Predicate("received",2));
		THO_received.addArgument(doc);
		THO_received.addArgument(usr);
		FOLAtom THO_order = new FOLAtom( new Predicate("order",1));
		THO_order.addArgument(doc);
		FOLAtom THO_user = new FOLAtom( new Predicate("user",1));
		THO_user.addArgument(usr);
		Condition THO_tc = new Condition( new Conjunction(THO_received, new Conjunction(THO_order,THO_user)) );
		
		FOLAtom THO_processed = new FOLAtom( new Predicate("processed", 1) );
		THO_processed.addArgument(doc);
		Condition THO_fs = new Condition( new Conjunction(THO_processed, THO_order));
		
		Goal THO = new Goal("to_handle_order", THO_tc, THO_fs);
		
		/*to_wait_order*/
		FOLAtom TWO_received = new FOLAtom( new Predicate("received",2));
		TWO_received.addArgument(doc);
		TWO_received.addArgument(usr);
		FOLAtom TWO_order = new FOLAtom( new Predicate("order",1));
		TWO_order.addArgument(doc);
		FOLAtom TWO_user = new FOLAtom( new Predicate("user",1));
		TWO_user.addArgument(usr);
		Condition TWO_tc = new Condition( new Conjunction(TWO_received, new Conjunction(TWO_order,TWO_user)) );
		
		FOLAtom TWO_available = new FOLAtom( new Predicate("available",1));
		TWO_available.addArgument(doc);
		Condition TWO_fs = new Condition( new Conjunction(TWO_available, TWO_order) );
		
		Goal TWO = new Goal("to_wait_order", TWO_tc, TWO_fs);
		
		/*to_process_order*/
		FOLAtom TPO_available = new FOLAtom( new Predicate("available",1));
		TPO_available.addArgument(doc);
		FOLAtom TPO_order = new FOLAtom( new Predicate("order",1));
		TPO_order.addArgument(doc);
		FOLAtom TPO_registered = new FOLAtom( new Predicate("registered",1));
		TPO_registered.addArgument(usr);
		FOLAtom TPO_user = new FOLAtom( new Predicate("user",1));
		TPO_user.addArgument(usr);
		Condition TPO_tc = new Condition( new Conjunction(new Conjunction(TPO_available,TPO_order), new Conjunction(TPO_registered,TPO_user)) );
		
		FOLAtom TPO_processed = new FOLAtom( new Predicate("processed", 1) );
		TPO_processed.addArgument(doc);
		Condition TPO_fs = new Condition( new Conjunction(TPO_processed, TPO_order));
		
		Goal TPO = new Goal("to_handle_order", TPO_tc, TPO_fs);
		
		/*to_process_accepted_order*/
		FOLAtom TPAO_accepted = new FOLAtom( new Predicate("accepted",1));
		TPAO_accepted.addArgument(doc);
		FOLAtom TPAO_order = new FOLAtom( new Predicate("order",1));
		TPAO_order.addArgument(doc);
		Condition TPAO_tc = new Condition( new Conjunction(TPAO_accepted, TPAO_order));
		
		FOLAtom TPAO_send = new FOLAtom( new Predicate("send",2));
		TPAO_send.addArgument(doc);
		TPAO_send.addArgument(mng);
		FOLAtom TPAO_delivery = new FOLAtom( new Predicate("delivery_order", 1));
		TPAO_delivery.addArgument(doc);
		FOLAtom TPAO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TPAO_manager.addArgument(mng);
		Condition TPAO_fs = new Condition( new Conjunction(TPAO_send, new Conjunction(TPAO_delivery, TPAO_manager)));
		
		Goal TPAO = new Goal("to_handle_order", TPAO_tc, TPAO_fs);
		
		/*to_notify_invoce*/
		FOLAtom TNI_registered = new FOLAtom( new Predicate("registered",1));
		TNI_registered.addArgument(usr);
		FOLAtom TNI_user = new FOLAtom( new Predicate("user",1));
		TNI_user.addArgument(usr);
		FOLAtom TNI_available = new FOLAtom( new Predicate("available",1));
		TNI_available.addArgument(doc);
		FOLAtom TNI_invoice = new FOLAtom( new Predicate("invoice",1));
		TNI_invoice.addArgument(doc);
		Condition TNI_tc = new Condition( new Conjunction(new Conjunction(TNI_registered, TNI_user), new Conjunction(TNI_available, TNI_invoice)) );
		
		FOLAtom TNI_send = new FOLAtom( new Predicate("send",2));
		TNI_send.addArgument(doc);
		TNI_send.addArgument(usr);		
		Condition TNI_fs = new Condition( new Conjunction(TNI_send, new Conjunction(TNI_invoice, TNI_user)) );
		
		Goal TNI = new Goal("to_notify_invoice", TNI_tc, TNI_fs);
		
		/*to_deliver_order*/
		FOLAtom TDO_send = new FOLAtom( new Predicate("send",2));
		TDO_send.addArgument(doc);
		TDO_send.addArgument(usr);
		FOLAtom TDO_invoice = new FOLAtom( new Predicate("invoice",1));
		TDO_invoice.addArgument(doc);		
		FOLAtom TDO_user = new FOLAtom( new Predicate("user",1));
		TDO_user.addArgument(usr);
		Condition TDO_tc = new Condition( new Conjunction(TDO_send, new Conjunction(TDO_invoice, TDO_user)) );
		
		FOLAtom TDO_delivery = new FOLAtom( new Predicate("delivery_order", 1));
		TDO_delivery.addArgument(doc);
		FOLAtom TDO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TDO_manager.addArgument(mng);
		Condition TDO_fs = new Condition( new Conjunction(TDO_send, new Conjunction(TDO_delivery, TDO_manager)) );
		
		Goal TDO = new Goal("to_delivery_order", TDO_tc, TDO_fs);
		
		/*to_notify_failure*/
		FOLAtom TNF_refused = new FOLAtom( new Predicate("refused",1));
		TNF_refused.addArgument(doc);
		FOLAtom TNF_order = new FOLAtom( new Predicate("order",1));
		TNF_order.addArgument(doc);
		FOLAtom TNF_registered = new FOLAtom( new Predicate("registered",1));
		TNF_registered.addArgument(usr);
		FOLAtom TNF_user = new FOLAtom( new Predicate("user",1));
		TNF_user.addArgument(usr);
		Condition TNF_tc = new Condition( new Conjunction(new Conjunction(TNF_refused, TNF_order), new Conjunction(TNF_registered, TNF_user)) );
		
		FOLAtom TNF_send = new FOLAtom( new Predicate("send",2));
		TNF_send.addArgument(new Constant("failure_order"));
		TNF_send.addArgument(usr);	
		Condition TNF_fs = new Condition( new Conjunction(TNF_send, TNF_user));
		
		Goal TNF = new Goal("to_notify_failure", TNF_tc, TNF_fs);
		
		
		/*Model construction*/
		model = new GoalModel(THO);
		ArrayList<Goal> firstLevel = new ArrayList<>();
		firstLevel.add(TWO);
		firstLevel.add(TPO);
		ArrayList<Goal> secondLevel = new ArrayList<>();
		secondLevel.add(TPAO);
		secondLevel.add(TNF);
		ArrayList<Goal> thirdLevel = new ArrayList<>();
		thirdLevel.add(TNI);
		thirdLevel.add(TDO);
		
		model.addAndArcs(THO, firstLevel);
		model.addOrArcs(TPO, secondLevel);
		model.addAndArcs(TPAO, thirdLevel);
		
		problem = new ProblemExploration(model, new ArrayList<AbstractCapability>(), domain);
		
		StateOfWorld wStart = new StateOfWorld(); 
		try {
		  wStart.addFact_asString("order(an_order).");
		  wStart.addFact_asString("available(an_order).");
		  wStart.addFact_asString("user(a_user).");
		  wStart.addFact_asString("user_data(the_user_data).");
		} catch (ParseException e) {
		  e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
		  e.printStackTrace();
		}
		
		ArrayList<Token> tokens = new ArrayList<>();
		tokens.add(new Token("p3"));
		tokens.add(new Token("p4"));
		
		problem.addToVisit(new WorldNode(wStart), tokens, 9);
	}
	
	@Test
	public void test1() {
		/**************************************************/
		/*check_user*/
		FOLAtom CU_available = new FOLAtom( new Predicate("available",1));
		CU_available.addArgument(doc);
		FOLAtom CU_order = new FOLAtom( new Predicate("order",1));
		CU_order.addArgument(doc);
		Condition CU_pre = new Condition( new ExistsQuantifiedFormula(new Conjunction(CU_available, CU_order), doc) );

		Set<EvolutionScenario> CU_evo = new HashSet<>();
		CapabilityEvolutionScenario CU_evo1 = new CapabilityEvolutionScenario("RegisteredUserWithCloud");
		CU_evo1.addOperator( new AddStatement( new DLPHead(new DLPAtom("registered", a_user)) ) );
		CU_evo1.addOperator( new AddStatement( new DLPHead(new DLPAtom("has_cloud_space", a_user)) ) );
		CU_evo1.addOperator( new AddStatement( new DLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo.add(CU_evo1);
		CapabilityEvolutionScenario CU_evo2 = new CapabilityEvolutionScenario("RegisteredUserWithoutCloud");
		CU_evo2.addOperator( new AddStatement( new DLPHead(new DLPAtom("registered", a_user)) ) );
		CU_evo2.addOperator( new AddStatement( new DLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo.add(CU_evo2);
		CapabilityEvolutionScenario CU_evo3 = new CapabilityEvolutionScenario("KnownUser");
		CU_evo3.addOperator( new AddStatement( new DLPHead(new DLPAtom("complete", the_user_data)) ) );
		CU_evo3.addOperator( new AddStatement( new DLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo3.addOperator( new AddStatement( new DLPHead(new DLPAtom("unregistered", a_user)) ) );
		CU_evo3.addOperator( new AddStatement( new DLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo.add(CU_evo3);
		CapabilityEvolutionScenario CU_evo4 = new CapabilityEvolutionScenario("UnknownUser");
		CU_evo4.addOperator( new AddStatement( new DLPHead(new DLPAtom("uncomplete", the_user_data)) ) );
		CU_evo4.addOperator( new AddStatement( new DLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo4.addOperator( new AddStatement( new DLPHead(new DLPAtom("unregistered", a_user)) ) );
		CU_evo4.addOperator( new AddStatement( new DLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo.add(CU_evo4);
		
		AbstractCapability CU = new AbstractCapability("check_user", CU_evo, CU_pre, null);
		/**************************************************/
		
		problem.addCapability(CU);
		
		problem.expandNode();
		
	}

}
