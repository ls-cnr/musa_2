// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;
import jason.asSyntax.Term;
import layer.awareness.AbstractCapability;
import layer.awareness.Goal;
import layer.awareness.goalmodel.GoalTreeModel;
import layer.awareness.net.Token;
import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import layer.semantic.StateOfWorld;
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
import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.ProblemExploration;
import translator.ExtDLPHead;
import translator.JasonENode;
import translator.JasonExpansionNode;
import translator.TranslateError;


//@ARTIFACT_INFO(
//		  outports = {
//		    @OUTPORT(name = "local_cap_repo")
//		  }
//	)
public class ProblemExplorationArtifact2 extends Artifact {

	private ProblemExploration pe;

	void init() {
		ArrayList<AbstractCapability> capabilities = get_capabilities_for_test();

		// a regime vanno presi dal artefatto Specification
		GoalTreeModel model = get_goal_model_for_test();
		AssumptionSet assumptions=get_domain_assumption_for_test();

		pe = new ProblemExploration( model, capabilities, assumptions);
		//this.debugSetInitialNode();

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

	@OPERATION
	void removeWinnerNode(String node){
		ExpansionNode exp = null;
		try{
			exp = JasonExpansionNode.term_string_to_object(node);
		}catch(TranslateError t){}
		this.pe.removeExpandedNode(exp);
	}

	private GoalTreeModel get_goal_model_for_test() {
		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Variable mng = new Variable("Mng");
		@SuppressWarnings("unused")
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
	@SuppressWarnings("unused")
	private ArrayList<AbstractCapability> get_capabilities_for_test() {
		ArrayList<AbstractCapability>list = new ArrayList<AbstractCapability>();

		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Constant a_user = new Constant("a_user");
		Constant an_order = new Constant("an_order");
		Constant the_user_data = new Constant("the_user_data");
		Constant the_registration_form = new Constant("the_registration_form");
		Constant failure_order = new Constant("failure_order");
		Constant the_invoice = new Constant("the_invoice");
		Constant the_delivery_order = new Constant("the_delivery_order");
		Constant a_storehouse_manager = new Constant("a_storehouse_manager");
		Constant the_user_space = new Constant("the_user_space");
		Constant the_system_space = new Constant("the_system_space");

		/*send_registration_form*/
		FOLAtom SRF_uncomplete = new FOLAtom( new Predicate("uncomplete",1));
		SRF_uncomplete.addArgument(doc);
		FOLAtom SRF_user_data = new FOLAtom( new Predicate("user_data",1));
		SRF_user_data.addArgument(doc);
		FOLAtom SRF_unregistered = new FOLAtom( new Predicate("unregistered",1));
		SRF_unregistered.addArgument(usr);
		FOLAtom SRF_user = new FOLAtom( new Predicate("user",1));
		SRF_user.addArgument(usr);
		Set<Variable> SRF_Set = new HashSet<Variable>();
		SRF_Set.add(doc);
		SRF_Set.add(usr);
		Condition SRF_pre = new Condition(new ExistsQuantifiedFormula( new Conjunction(new Conjunction(SRF_uncomplete, SRF_user_data), new Conjunction(SRF_unregistered, SRF_user)), SRF_Set ));

		Set<EvolutionScenario> SRF_evo = new HashSet<>();
		CapabilityEvolutionScenario SRF_evo1 = new CapabilityEvolutionScenario("UncompleteRegistrationForm");
		SRF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form)) ) );
		SRF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registration_form", the_registration_form)) ) );
		SRF_evo.add(SRF_evo1);

		AbstractCapability SRF = new AbstractCapability("send_registration_form", SRF_evo, SRF_pre, null);

		/*wait_user_data*/
		FOLAtom WUD_uncomplete = new FOLAtom( new Predicate("uncomplete",1));
		WUD_uncomplete.addArgument(doc);
		FOLAtom WUD_registration_form = new FOLAtom( new Predicate("registration_form",1));
		WUD_registration_form.addArgument(doc);
		Condition WUD_pre = new Condition( new ExistsQuantifiedFormula( new Conjunction(WUD_uncomplete, WUD_registration_form), doc));

		Set<EvolutionScenario> WUD_evo = new HashSet<>();
		CapabilityEvolutionScenario WUD_evo1 = new CapabilityEvolutionScenario("CompleteForm");
		WUD_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("complete", the_user_data)) ) );
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_user_data))));
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form))));
		WUD_evo1.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("registration_form", the_registration_form)) ) );
		WUD_evo.add(WUD_evo1);
		CapabilityEvolutionScenario WUD_evo2 = new CapabilityEvolutionScenario("UncompleteForm");
		WUD_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form)) ) );
		WUD_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("registration_form", the_registration_form)) ) );
		WUD_evo.add(WUD_evo2);

		AbstractCapability WUD = new AbstractCapability("wait_user_data", WUD_evo, WUD_pre, null);


		/*notify_stock_failure*/
		FOLAtom NSF_refused = new FOLAtom( new Predicate("refused",1));
		NSF_refused.addArgument(doc);
		FOLAtom NSF_order = new FOLAtom( new Predicate("order",1));
		NSF_order.addArgument(doc);
		FOLAtom NSF_registered = new FOLAtom( new Predicate("registered",1));
		NSF_registered.addArgument(usr);
		FOLAtom NSF_user = new FOLAtom( new Predicate("user",1));
		NSF_user.addArgument(usr);
		FOLAtom NSF_sent = new FOLAtom( new Predicate("sent",2));
		NSF_sent.addArgument(doc);
		NSF_sent.addArgument(usr);
		Negation NSF_notSent = new Negation(NSF_sent);
		Set<Variable> NSF_Set = new HashSet<Variable>();
		NSF_Set.add(doc);
		NSF_Set.add(usr);
		Condition NSF_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(NSF_refused, NSF_order), new Conjunction(NSF_registered, NSF_user)), NSF_notSent), NSF_Set ));

		Set<EvolutionScenario> NSF_evo = new HashSet<>();
		CapabilityEvolutionScenario NSF_evo1 = new CapabilityEvolutionScenario("Failure");
		NSF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("sent", failure_order, a_user)) ) );
		NSF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		NSF_evo.add(NSF_evo1);

		AbstractCapability NSF = new AbstractCapability("notify_stock_failure", NSF_evo, NSF_pre, null);


		/*share_file_link*/
		FOLAtom SFL_uploaded_on_cloud = new FOLAtom( new Predicate("uploaded_on_cloud",1));
		SFL_uploaded_on_cloud.addArgument(doc);
		FOLAtom SFL_invoice = new FOLAtom( new Predicate("invoice",1));
		SFL_invoice.addArgument(doc);
		FOLAtom SFL_not_has_cloud_space = new FOLAtom( new Predicate("has_cloud_space",1));
		SFL_not_has_cloud_space.addArgument(usr);
		Negation neg2 = new Negation(SFL_not_has_cloud_space);
		FOLAtom SFL_user = new FOLAtom( new Predicate("user",1));
		SFL_user.addArgument(usr);
		FOLAtom SFL_mailed = new FOLAtom( new Predicate("mailed_perm_link",2));
		SFL_mailed.addArgument(doc);
		SFL_mailed.addArgument(usr);
		Negation SFL_notMailed = new Negation(SFL_mailed);
		Set<Variable> SFL_Set = new HashSet<Variable>();
		SFL_Set.add(doc);
		SFL_Set.add(usr);
		Condition SFL_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(SFL_uploaded_on_cloud, SFL_invoice), new Conjunction(neg2, SFL_user)), SFL_notMailed), SFL_Set ));


		Set<EvolutionScenario> SFL_evo = new HashSet<>();
		CapabilityEvolutionScenario SFL_evo1 = new CapabilityEvolutionScenario("MailedPermLink");
		SFL_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("mailed_perm_link", the_invoice, a_user)) ) );
		SFL_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", a_user)) ) );
		SFL_evo.add(SFL_evo1);

		AbstractCapability SFL = new AbstractCapability("share_file_link", SFL_evo, SFL_pre, null);


		list.add(SRF);
		list.add(WUD);
		//list.add(NSF);
		//list.add(SFL);
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

	@SuppressWarnings("unused")
	private void debugSetInitialNode(){
		StateOfWorld regNoCloud = new StateOfWorld();
		try {
			regNoCloud.addFact_asString("order(an_order).");
			regNoCloud.addFact_asString("available(an_order).");
			regNoCloud.addFact_asString("user(a_user).");
			regNoCloud.addFact_asString("user_data(the_user_data).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		ArrayList<Token> tokens = new ArrayList<>();
		tokens.add(new Token("p3"));
		tokens.add(new Token("p4"));
		this.pe.addToVisit(new WorldNode(regNoCloud), tokens, 9);
	}
}

