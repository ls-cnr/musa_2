// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartago.*;
import jason.asSyntax.Term;
import layer.awareness.AbstractCapability;
import layer.awareness.Goal;
import layer.awareness.goalmodel.GoalTreeModel;
import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.evolution.AddStatement;
import layer.semantic.evolution.CapabilityEvolutionScenario;
import layer.semantic.evolution.EvolutionScenario;
import layer.semantic.evolution.RemoveStatement;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import pmr.probexp.ExpansionNode;
import pmr.probexp.ProblemExploration;
import translator.ExtDLPHead;
import translator.JasonENode;
import translator.JasonExpansionNode;
import translator.TranslateError;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;


//@ARTIFACT_INFO(
//		  outports = {
//		    @OUTPORT(name = "local_cap_repo")
//		  }
//	) 
public class ProblemExplorationArtifact extends Artifact {
	
	private ProblemExploration pe;
	
	void init() {
		ArrayList<AbstractCapability> capabilities = get_capabilities_for_test();

		// a regime vanno presi dal artefatto Specification
		GoalTreeModel model = get_goal_model_for_test();
		AssumptionSet assumptions=get_domain_assumption_for_test();
		
		pe = new ProblemExploration( model, capabilities, assumptions);

	}
	
	@OPERATION
	public void addToVisit( String term_string ) {
		ENode node;
		try{
			node = JasonENode.term_string_to_object(term_string);
		}catch(TranslateError t){return;}
		if (!node.isExitNode()) {
			pe.addToVisit(new WorldNode(node.getWorldState()), node.getTokens(), node.getScore() );
		}
	}
	
	@OPERATION
	void expand_local_graph() {
		pe.expandNode();
	}
	
	@OPERATION
	void getMostPromisingExpansion(OpFeedbackParam<Term> expansion) {
		ExpansionNode exp = pe.getHighestExpansion();
		expansion.set( JasonExpansionNode.object_to_term(exp) );
	}
	
	private GoalTreeModel get_goal_model_for_test() {
		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Variable mng = new Variable("Mng");
		Variable fail = new Variable("Fail");

		/*to_handle_order*/
		FOLAtom THO_received = new FOLAtom( new Predicate("received",2));
		THO_received.addArgument(doc);
		THO_received.addArgument(usr);
		FOLAtom THO_order = new FOLAtom( new Predicate("order",1));
		THO_order.addArgument(doc);
		FOLAtom THO_user = new FOLAtom( new Predicate("user",1));
		THO_user.addArgument(usr);
		Set<Variable> THO_var = new HashSet<Variable>();
		THO_var.add(doc);
		THO_var.add(usr);
		Condition THO_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(THO_received, new Conjunction(THO_order,THO_user)), THO_var ) );
		
		FOLAtom THO_processed = new FOLAtom( new Predicate("processed", 1) );
		THO_processed.addArgument(doc);
		Condition THO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(THO_processed, THO_order), doc));
		
		Goal THO = new Goal("to_handle_order", THO_tc, THO_fs);
		
		/*to_wait_order*/
		FOLAtom TWO_received = new FOLAtom( new Predicate("received",2));
		TWO_received.addArgument(doc);
		TWO_received.addArgument(usr);
		FOLAtom TWO_order = new FOLAtom( new Predicate("order",1));
		TWO_order.addArgument(doc);
		FOLAtom TWO_user = new FOLAtom( new Predicate("user",1));
		TWO_user.addArgument(usr);
		Set<Variable> TWO_var = new HashSet<Variable>();
		TWO_var.add(doc);
		TWO_var.add(usr);
		Condition TWO_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(TWO_received, new Conjunction(TWO_order,TWO_user)), TWO_var ) );
		
		FOLAtom TWO_available = new FOLAtom( new Predicate("available",1));
		TWO_available.addArgument(doc);
		Condition TWO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TWO_available, TWO_order), doc ) );
		
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
		Set<Variable> TPO_var = new HashSet<Variable>();
		TPO_var.add(doc);
		TPO_var.add(usr);
		Condition TPO_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(new Conjunction(TPO_available,TPO_order), new Conjunction(TPO_registered,TPO_user)), TPO_var ) );
		
		FOLAtom TPO_processed = new FOLAtom( new Predicate("processed", 1) );
		TPO_processed.addArgument(doc);
		Condition TPO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TPO_processed, TPO_order), doc ) );
		
		Goal TPO = new Goal("to_process_order", TPO_tc, TPO_fs);
		
		/*to_process_accepted_order*/
		FOLAtom TPAO_accepted = new FOLAtom( new Predicate("accepted",1));
		TPAO_accepted.addArgument(doc);
		FOLAtom TPAO_order = new FOLAtom( new Predicate("order",1));
		TPAO_order.addArgument(doc);
		Condition TPAO_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(TPAO_accepted, TPAO_order), doc) );
		
		FOLAtom TPAO_send = new FOLAtom( new Predicate("send",2));
		TPAO_send.addArgument(doc);
		TPAO_send.addArgument(mng);
		FOLAtom TPAO_delivery = new FOLAtom( new Predicate("delivery_order", 1));
		TPAO_delivery.addArgument(doc);
		FOLAtom TPAO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TPAO_manager.addArgument(mng);
		Set<Variable> TPAO_var = new HashSet<Variable>();
		TPAO_var.add(doc);
		TPAO_var.add(mng);
		Condition TPAO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TPAO_send, new Conjunction(TPAO_delivery, TPAO_manager)), TPAO_var) );
		
		Goal TPAO = new Goal("to_process_accepted_order", TPAO_tc, TPAO_fs);
		
		/*to_notify_invoce*/
		FOLAtom TNI_registered = new FOLAtom( new Predicate("registered",1));
		TNI_registered.addArgument(usr);
		FOLAtom TNI_user = new FOLAtom( new Predicate("user",1));
		TNI_user.addArgument(usr);
		FOLAtom TNI_available = new FOLAtom( new Predicate("available",1));
		TNI_available.addArgument(doc);
		FOLAtom TNI_invoice = new FOLAtom( new Predicate("invoice",1));
		TNI_invoice.addArgument(doc);
		Set<Variable> TNI_var1 = new HashSet<Variable>();
		TNI_var1.add(usr);
		TNI_var1.add(doc);
		Condition TNI_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(new Conjunction(TNI_registered, TNI_user), new Conjunction(TNI_available, TNI_invoice)), TNI_var1) );
		
		FOLAtom TNI_send = new FOLAtom( new Predicate("send",2));
		TNI_send.addArgument(doc);
		TNI_send.addArgument(usr);
		Set<Variable> TNI_var2 = new HashSet<Variable>();
		TNI_var2.add(doc);
		TNI_var2.add(usr);		
		Condition TNI_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TNI_send, new Conjunction(TNI_invoice, TNI_user)), TNI_var2) );
		
		Goal TNI = new Goal("to_notify_invoice", TNI_tc, TNI_fs);
		
		/*to_deliver_order*/
		FOLAtom TDO_send = new FOLAtom( new Predicate("send",2));
		TDO_send.addArgument(doc);
		TDO_send.addArgument(usr);
		FOLAtom TDO_invoice = new FOLAtom( new Predicate("invoice",1));
		TDO_invoice.addArgument(doc);		
		FOLAtom TDO_user = new FOLAtom( new Predicate("user",1));
		TDO_user.addArgument(usr);
		Set<Variable> TDO_var1 = new HashSet<Variable>();
		TDO_var1.add(doc);
		TDO_var1.add(usr);
		Condition TDO_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(TDO_send, new Conjunction(TDO_invoice, TDO_user)), TDO_var1) );
		
		FOLAtom TDO_delivery = new FOLAtom( new Predicate("delivery_order", 1));
		TDO_delivery.addArgument(doc);
		FOLAtom TDO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TDO_manager.addArgument(mng);
		Set<Variable> TDO_var2 = new HashSet<Variable>();
		TDO_var2.add(doc);
		TDO_var2.add(mng);
		Condition TDO_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TDO_send, new Conjunction(TDO_delivery, TDO_manager)), TDO_var2) );
		
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
		Set<Variable> TNF_var1 = new HashSet<Variable>();
		TNF_var1.add(doc);
		TNF_var1.add(usr);
		Condition TNF_tc = new Condition( new ExistsQuantifiedFormula(new Conjunction(new Conjunction(TNF_refused, TNF_order), new Conjunction(TNF_registered, TNF_user)), TNF_var1) );
		
		FOLAtom TNF_send = new FOLAtom( new Predicate("send",2));
		TNF_send.addArgument(new Constant("failure_order"));
		TNF_send.addArgument(usr);	
		Condition TNF_fs = new Condition( new ExistsQuantifiedFormula(new Conjunction(TNF_send, TNF_user), usr) );
		
		Goal TNF = new Goal("to_notify_failure", TNF_tc, TNF_fs);

		/*Model construction*/
		GoalTreeModel model = new GoalTreeModel(THO);
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

	    return model;
	}
	private ArrayList<AbstractCapability> get_capabilities_for_test() {
		ArrayList<AbstractCapability>list = new ArrayList<AbstractCapability>();
		
		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Constant a_user = new Constant("a_user");
		Constant the_user_data = new Constant("the_user_data");

		/*check_user*/
		FOLAtom CU_available = new FOLAtom( new Predicate("available",1));
		CU_available.addArgument(doc);
		FOLAtom CU_order = new FOLAtom( new Predicate("order",1));
		CU_order.addArgument(doc);
		FOLAtom CU_notLogged = new FOLAtom( new Predicate("logged",1));
		CU_notLogged.addArgument(usr);
		Negation CU_neg = new Negation(CU_notLogged);
		FOLAtom CU_user = new FOLAtom( new Predicate("user",1));
		CU_user.addArgument(usr);
		
		Set CU_Set = new HashSet<Variable>();
		CU_Set.add(doc);
		CU_Set.add(usr);
		Condition CU_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(CU_available, CU_order), new Conjunction(CU_neg, CU_user) ), CU_Set ));

		Set<EvolutionScenario> CU_evo = new HashSet<>();
		CapabilityEvolutionScenario CU_evo1 = new CapabilityEvolutionScenario("RegisteredUserWithCloud");
		CU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registered", a_user)) ) );
		CU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("has_cloud_space", a_user)) ) );
		CU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo1.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("logged", a_user)) ) );
		CU_evo.add(CU_evo1);
		CapabilityEvolutionScenario CU_evo2 = new CapabilityEvolutionScenario("RegisteredUserWithoutCloud");
		CU_evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registered", a_user)) ) );
		CU_evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("logged", a_user)) ) );
		CU_evo.add(CU_evo2);
		CapabilityEvolutionScenario CU_evo3 = new CapabilityEvolutionScenario("KnownUser");
		CU_evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("complete", the_user_data)) ) );
		CU_evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("unregistered", a_user)) ) );
		CU_evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo3.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("logged", a_user)) ) );
		CU_evo.add(CU_evo3);
		CapabilityEvolutionScenario CU_evo4 = new CapabilityEvolutionScenario("UnknownUser");
		CU_evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uncomplete", the_user_data)) ) );
		CU_evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user_data", the_user_data)) ) );
		CU_evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("unregistered", a_user)) ) );
		CU_evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		CU_evo4.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("logged", a_user)) ) );
		CU_evo.add(CU_evo4);
		
		AbstractCapability CU = new AbstractCapability("check_user", CU_evo, CU_pre, null);
		list.add(CU);
		return list;
	}
	
	private AssumptionSet get_domain_assumption_for_test() {
		AssumptionSet domain = new AssumptionSet();	 
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
		return domain;
	}
	
}

