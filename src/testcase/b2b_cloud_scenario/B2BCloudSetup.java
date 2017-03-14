package b2b_cloud_scenario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import translator.ExtDLPHead;

public class B2BCloudSetup {
	private StateOfWorld wStart;
	private StateOfWorld startNode;
	private StateOfWorld regAndCloud;
	private StateOfWorld regAndCloud2;
	private StateOfWorld regNoCloud;
	private StateOfWorld known;
	private StateOfWorld unknown;
	private StateOfWorld uncompleteRegForm;
	private StateOfWorld acceptedOrderNoCloud;
	private StateOfWorld acceptedOrderCloud;
	private StateOfWorld refusedOrderNoCloud;
	private StateOfWorld refusedOrderCloud;
	private StateOfWorld notifyFailureCloud;
	private StateOfWorld notifyFailureNoCloud;
	private StateOfWorld availableInvoiceCloud;
	private StateOfWorld availableInvoiceNoCloud;
	private StateOfWorld uploadedOnUserCloud;
	private StateOfWorld uploadedOnPrivateCloud;
	private StateOfWorld sharedLink;
	private StateOfWorld notifiedStoreHouseCloud;
	private StateOfWorld notifiedStoreHouseNoCloud;

	private WorldNode nodewStart;
	private WorldNode noderegAndCloud;
	private WorldNode noderegNoCloud;
	private WorldNode nodeknown;
	private WorldNode nodeunknown;
	private WorldNode nodeuncompleteRegForm;
	private WorldNode nodeacceptedOrderNoCloud;
	private WorldNode nodeacceptedOrderCloud;
	private WorldNode noderefusedOrderNoCloud;
	private WorldNode noderefusedOrderCloud;
	private WorldNode nodenotifyFailureCloud;
	private WorldNode nodenotifyFailureNoCloud;
	private WorldNode nodeavailableInvoiceCloud;
	private WorldNode nodeavailableInvoiceNoCloud;
	private WorldNode nodeuploadedOnUserCloud;
	private WorldNode nodeuploadedOnPrivateCloud;
	private WorldNode nodesharedLink;
	private WorldNode nodenotifiedStoreHouseCloud;
	private WorldNode nodenotifiedStoreHouseNoCloud;

	private ENode eStart;
	private ENode eregAndCloud;
	private ENode eregNoCloud;
	private ENode eknown;
	private ENode eunknown;
	private ENode euncompleteRegForm;
	private ENode eacceptedOrderNoCloud;
	private ENode eacceptedOrderCloud;
	private ENode erefusedOrderNoCloud;
	private ENode erefusedOrderCloud;
	private ENode enotifyFailureCloud;
	private ENode enotifyFailureNoCloud;
	private ENode eavailableInvoiceCloud;
	private ENode eavailableInvoiceNoCloud;
	private ENode euploadedOnUserCloud;
	private ENode euploadedOnPrivateCloud;
	private ENode esharedLink;
	private ENode enotifiedStoreHouseCloud;
	private ENode enotifiedStoreHouseNoCloud;

	private AbstractCapability NSM;
	private AbstractCapability SFL;
	private AbstractCapability UOPCS;
	private AbstractCapability UOUCS;
	private AbstractCapability GI;
	private AbstractCapability NSF;
	private AbstractCapability CS;
	private AbstractCapability WUD;
	private AbstractCapability SRF;
	private AbstractCapability CU;
	private AbstractCapability AU;

	private Goal TNF;
	private Goal TDO;
	private Goal TNI;
	private Goal TPAO;
	private Goal TPO;
	private Goal TWO;
	private Goal THO;

	private GoalTreeModel model;
	private ArrayList<Token> startTokens;

	private AssumptionSet domain;

	public ArrayList<AbstractCapability> getCapabilities() {
		ArrayList<AbstractCapability> al = new ArrayList<AbstractCapability>();
		al.add(NSM);
		al.add(SFL);
		al.add(UOPCS);
		al.add(UOUCS);
		al.add(GI);
		al.add(NSF);
		al.add(CS);
		al.add(WUD);
		al.add(SRF);
		al.add(CU);
		al.add(AU);
		return al;
	}

	public WorldNode getNodewStart() {
		return this.nodewStart;
	}

	public GoalTreeModel getGoalModel() {
		return this.model;

	}

	public AssumptionSet getDomain() {
		return this.domain;
	}

	public ArrayList<Token> getStartTokens() {
		return this.startTokens;
	}

	public B2BCloudSetup() {
		setup_domain_assumption();

		/*************/

		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Variable mng = new Variable("Mng");
		Variable fail = new Variable("Fail");

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

		/*************/
		
		setup_goals();
		
		/**************************************************/
		/* check_user */
		/* CAPABILITY check_user:
				Parameters: an_order, a_user, the_user_data	
				Assumptions: order(an_order), user(a_user), user_data(the_user_data)	
				Pre-Condition: available(X) & order(X)
    				Post-Condition: --
			    	EvolutionSet:
			     		RegisteredUserWithCloud: 		[add(registered(a_user)), add(has_cloud_space(a_user))],
					    RegisteredUserWithoutCloud:		[add(registered(a_user))],
			  		    KnownUser: 			    					[add(complete(the_user_data)), add(unregistered(a_user))],
					    UnknownUser:							[add(uncomplete(the_user_data)), add(unregistered(a_user))]
		 */
		FOLAtom CU_available = new FOLAtom(new Predicate("available", 1));
		CU_available.addArgument(doc);
		FOLAtom CU_order = new FOLAtom(new Predicate("order", 1));
		CU_order.addArgument(doc);
		FOLAtom CU_notLogged = new FOLAtom(new Predicate("logged", 1));
		CU_notLogged.addArgument(usr);
		Negation CU_neg = new Negation(CU_notLogged);
		FOLAtom CU_user = new FOLAtom(new Predicate("user", 1));
		CU_user.addArgument(usr);

		Set CU_Set = new HashSet<Variable>();
		CU_Set.add(doc);
		CU_Set.add(usr);
		Condition CU_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(CU_available, CU_order), new Conjunction(CU_neg, CU_user)), CU_Set));

		Set<EvolutionScenario> CU_evo = new HashSet<>();
		CapabilityEvolutionScenario CU_evo1 = new CapabilityEvolutionScenario("RegisteredUserWithCloud");
		CU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("registered", a_user))));
		CU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("has_cloud_space", a_user))));
		CU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		CU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("user_data", the_user_data))));
		CU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("logged", a_user))));
		CU_evo.add(CU_evo1);
		CapabilityEvolutionScenario CU_evo2 = new CapabilityEvolutionScenario("RegisteredUserWithoutCloud");
		CU_evo2.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("registered", a_user))));
		CU_evo2.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		CU_evo2.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("user_data", the_user_data))));
		CU_evo2.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("logged", a_user))));
		CU_evo.add(CU_evo2);
		CapabilityEvolutionScenario CU_evo3 = new CapabilityEvolutionScenario("KnownUser");
		CU_evo3.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("complete", the_user_data))));
		CU_evo3.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user_data", the_user_data))));
		CU_evo3.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("unregistered", a_user))));
		CU_evo3.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		CU_evo3.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("logged", a_user))));
		CU_evo.add(CU_evo3);
		CapabilityEvolutionScenario CU_evo4 = new CapabilityEvolutionScenario("UnknownUser");
		CU_evo4.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_user_data))));
		CU_evo4.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user_data", the_user_data))));
		CU_evo4.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("unregistered", a_user))));
		CU_evo4.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		CU_evo4.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("logged", a_user))));
		CU_evo.add(CU_evo4);

		this.CU = new AbstractCapability("check_user", CU_evo, CU_pre, null);

		/* add_user */
		FOLAtom AU_complete = new FOLAtom(new Predicate("complete", 1));
		AU_complete.addArgument(doc);
		FOLAtom AU_user_data = new FOLAtom(new Predicate("user_data", 1));
		AU_user_data.addArgument(doc);
		FOLAtom AU_unregistered = new FOLAtom(new Predicate("unregistered", 1));
		AU_unregistered.addArgument(usr);
		FOLAtom AU_user = new FOLAtom(new Predicate("user", 1));
		AU_user.addArgument(usr);
		Set AU_Set = new HashSet<Variable>();
		AU_Set.add(doc);
		AU_Set.add(usr);
		Condition AU_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(AU_complete, AU_user_data), new Conjunction(AU_unregistered, AU_user)),
				AU_Set));

		Set<EvolutionScenario> AU_evo = new HashSet<>();
		CapabilityEvolutionScenario AU_evo1 = new CapabilityEvolutionScenario("RegisteredUser");
		AU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("registered", a_user))));
		AU_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("unregistered", a_user))));
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("complete", the_user_data))));
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("user_data", the_user_data))));
		AU_evo.add(AU_evo1);

		this.AU = new AbstractCapability("add_user", AU_evo, AU_pre, null);

		/* send_registration_form */
		FOLAtom SRF_uncomplete = new FOLAtom(new Predicate("uncomplete", 1));
		SRF_uncomplete.addArgument(doc);
		FOLAtom SRF_user_data = new FOLAtom(new Predicate("user_data", 1));
		SRF_user_data.addArgument(doc);
		FOLAtom SRF_unregistered = new FOLAtom(new Predicate("unregistered", 1));
		SRF_unregistered.addArgument(usr);
		FOLAtom SRF_user = new FOLAtom(new Predicate("user", 1));
		SRF_user.addArgument(usr);
		Set SRF_Set = new HashSet<Variable>();
		SRF_Set.add(doc);
		SRF_Set.add(usr);
		Condition SRF_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction(
				new Conjunction(SRF_uncomplete, SRF_user_data), new Conjunction(SRF_unregistered, SRF_user)), SRF_Set));

		Set<EvolutionScenario> SRF_evo = new HashSet<>();
		CapabilityEvolutionScenario SRF_evo1 = new CapabilityEvolutionScenario("UncompleteRegistrationForm");
		SRF_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form))));
		SRF_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("registration_form", the_registration_form))));
		SRF_evo.add(SRF_evo1);

		this.SRF = new AbstractCapability("send_registration_form", SRF_evo, SRF_pre, null);

		/* wait_user_data */
		FOLAtom WUD_uncomplete = new FOLAtom(new Predicate("uncomplete", 1));
		WUD_uncomplete.addArgument(doc);
		FOLAtom WUD_registration_form = new FOLAtom(new Predicate("registration_form", 1));
		WUD_registration_form.addArgument(doc);
		Condition WUD_pre = new Condition(
				new ExistsQuantifiedFormula(new Conjunction(WUD_uncomplete, WUD_registration_form), doc));

		Set<EvolutionScenario> WUD_evo = new HashSet<>();
		CapabilityEvolutionScenario WUD_evo1 = new CapabilityEvolutionScenario("CompleteForm");
		WUD_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("complete", the_user_data))));
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_user_data))));
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form))));
		WUD_evo1.addOperator(
				new RemoveStatement(new ExtDLPHead(new DLPAtom("registration_form", the_registration_form))));
		WUD_evo.add(WUD_evo1);
		CapabilityEvolutionScenario WUD_evo2 = new CapabilityEvolutionScenario("UncompleteForm");
		WUD_evo2.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", the_registration_form))));
		WUD_evo2.addOperator(
				new RemoveStatement(new ExtDLPHead(new DLPAtom("registration_form", the_registration_form))));
		WUD_evo.add(WUD_evo2);

		this.WUD = new AbstractCapability("wait_user_data", WUD_evo, WUD_pre, null);

		/* check_storehouse */
		/* CAPABILITY check_storehouse:
		    Parameters: an_order, a_user
		    Assumptions: order(an_order), user(a_user)
			Pre-Condition: available(X) & order(X) & registered(a_user) & user(X)
		    Post-Condition: --
		    EvolutionSet: 
		        AcceptableOrder: [add(accepted(an_order))],
		        UnacceptableOrder: [add(refused(an_order))]
		*/
		FOLAtom CS_available = new FOLAtom(new Predicate("available", 1));
		CS_available.addArgument(doc);
		FOLAtom CS_order = new FOLAtom(new Predicate("order", 1));
		CS_order.addArgument(doc);
		FOLAtom CS_registered = new FOLAtom(new Predicate("registered", 1));
		CS_registered.addArgument(usr);
		FOLAtom CS_user = new FOLAtom(new Predicate("user", 1));
		CS_user.addArgument(usr);
		Set CS_Set = new HashSet<Variable>();
		CS_Set.add(doc);
		CS_Set.add(usr);
		Condition CS_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(CS_available, CS_order), new Conjunction(CS_registered, CS_user)),
				CS_Set));

		Set<EvolutionScenario> CS_evo = new HashSet<>();
		CapabilityEvolutionScenario CS_evo1 = new CapabilityEvolutionScenario("AcceptableOrder");
		CS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("accepted", an_order))));
		CS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("order", an_order))));
		CS_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("available", an_order))));
		CS_evo.add(CS_evo1);
		CapabilityEvolutionScenario CS_evo2 = new CapabilityEvolutionScenario("UnacceptableOrder");
		CS_evo2.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("refused", an_order))));
		CS_evo2.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("order", an_order))));
		CS_evo2.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("available", an_order))));
		CS_evo.add(CS_evo2);

		this.CS = new AbstractCapability("check_storehouse", CS_evo, CS_pre, null);

		/* notify_stock_failure */
		FOLAtom NSF_refused = new FOLAtom(new Predicate("refused", 1));
		NSF_refused.addArgument(doc);
		FOLAtom NSF_order = new FOLAtom(new Predicate("order", 1));
		NSF_order.addArgument(doc);
		FOLAtom NSF_registered = new FOLAtom(new Predicate("registered", 1));
		NSF_registered.addArgument(usr);
		FOLAtom NSF_user = new FOLAtom(new Predicate("user", 1));
		NSF_user.addArgument(usr);
		FOLAtom NSF_ord_fail = new FOLAtom(new Predicate("order_failure", 1));
		NSF_ord_fail.addArgument(doc);
		Set NSF_Set = new HashSet<Variable>();
		NSF_Set.add(doc);
		NSF_Set.add(usr);
		Condition NSF_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(new Conjunction(NSF_refused, NSF_order),
						new Conjunction(NSF_registered, NSF_user)), new Conjunction(NSF_ord_fail, NSF_order)),
				NSF_Set));

		Set<EvolutionScenario> NSF_evo = new HashSet<>();
		CapabilityEvolutionScenario NSF_evo1 = new CapabilityEvolutionScenario("Failure");
		NSF_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("sent", failure_order, a_user))));
		NSF_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		NSF_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("order_failure", an_order))));
		NSF_evo.add(NSF_evo1);

		this.NSF = new AbstractCapability("notify_stock_failure", NSF_evo, NSF_pre, null);

		/* generate_invoice */
		FOLAtom GI_accepted = new FOLAtom(new Predicate("accepted", 1));
		GI_accepted.addArgument(doc);
		FOLAtom GI_order = new FOLAtom(new Predicate("order", 1));
		GI_order.addArgument(doc);
		FOLAtom GI_registered = new FOLAtom(new Predicate("registered", 1));
		GI_registered.addArgument(usr);
		FOLAtom GI_user = new FOLAtom(new Predicate("user", 1));
		GI_user.addArgument(usr);
		FOLAtom GI_invoice = new FOLAtom(new Predicate("available", 1));
		GI_invoice.addArgument(doc);
		Negation GI_notAvailable = new Negation(GI_invoice);
		Set GT_Set = new HashSet<Variable>();
		GT_Set.add(doc);
		GT_Set.add(usr);
		Condition GI_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction(
				new Conjunction(new Conjunction(GI_accepted, GI_order), new Conjunction(GI_registered, GI_user)),
				GI_notAvailable), GT_Set));

		Set<EvolutionScenario> GI_evo = new HashSet<>();
		CapabilityEvolutionScenario GI_evo1 = new CapabilityEvolutionScenario("AvailableInvoice");
		GI_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("available", the_invoice))));
		GI_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("invoice", the_invoice))));
		GI_evo.add(GI_evo1);

		this.GI = new AbstractCapability("generate_invoice", GI_evo, GI_pre, null);

		/* upload_on_user_cloud_storage */
		FOLAtom UOUCS_available = new FOLAtom(new Predicate("available", 1));
		UOUCS_available.addArgument(doc);
		FOLAtom UOUCS_invoice = new FOLAtom(new Predicate("invoice", 1));
		UOUCS_invoice.addArgument(doc);
		FOLAtom UOUCS_has_cloud_space = new FOLAtom(new Predicate("has_cloud_space", 1));
		UOUCS_has_cloud_space.addArgument(usr);
		FOLAtom UOUCS_user = new FOLAtom(new Predicate("user", 1));
		UOUCS_user.addArgument(usr);
		FOLAtom UOUCS_uploaded = new FOLAtom(new Predicate("uploaded_on_cloud", 1));
		UOUCS_uploaded.addArgument(doc);
		Negation UOUCS_notUploaded = new Negation(UOUCS_uploaded);
		Set UOUCS_Set = new HashSet<Variable>();
		UOUCS_Set.add(doc);
		UOUCS_Set.add(usr);
		Condition UOUCS_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(new Conjunction(UOUCS_available, UOUCS_invoice),
						new Conjunction(UOUCS_has_cloud_space, UOUCS_user)), UOUCS_notUploaded),
				UOUCS_Set));

		Set<EvolutionScenario> UOUCS_evo = new HashSet<>();
		CapabilityEvolutionScenario UOUCS_evo1 = new CapabilityEvolutionScenario("UploadedOnCloud");
		UOUCS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("uploaded_on_cloud", the_invoice))));
		UOUCS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("invoice", the_invoice))));
		UOUCS_evo.add(UOUCS_evo1);

		this.UOUCS = new AbstractCapability("upload_on_user_cloud_storage", UOUCS_evo, UOUCS_pre, null);

		/* upload_on_private_cloud_storage */
		FOLAtom UOPCS_available = new FOLAtom(new Predicate("available", 1));
		UOPCS_available.addArgument(doc);
		FOLAtom UOPCS_invoice = new FOLAtom(new Predicate("invoice", 1));
		UOPCS_invoice.addArgument(doc);
		FOLAtom UOPCS_not_has_cloud_space = new FOLAtom(new Predicate("has_cloud_space", 1));
		UOPCS_not_has_cloud_space.addArgument(usr);
		Negation neg1 = new Negation(UOPCS_not_has_cloud_space);
		FOLAtom UOPCS_user = new FOLAtom(new Predicate("user", 1));
		UOPCS_user.addArgument(usr);
		FOLAtom UOPCS_uploaded = new FOLAtom(new Predicate("uploaded_on_cloud", 1));
		UOPCS_uploaded.addArgument(doc);
		Negation UOPCS_notUploaded = new Negation(UOUCS_uploaded);
		Set UOPCS_Set = new HashSet<Variable>();
		UOPCS_Set.add(doc);
		UOPCS_Set.add(usr);
		Condition UOPCS_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction(
				new Conjunction(new Conjunction(UOPCS_available, UOPCS_invoice), new Conjunction(neg1, UOPCS_user)),
				UOPCS_notUploaded), UOPCS_Set));

		Set<EvolutionScenario> UOPCS_evo = new HashSet<>();
		CapabilityEvolutionScenario UOPCS_evo1 = new CapabilityEvolutionScenario("UploadedOnCloud");
		UOPCS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("uploaded_on_cloud", the_invoice))));
		UOPCS_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("invoice", the_invoice))));
		UOPCS_evo.add(UOPCS_evo1);

		this.UOPCS = new AbstractCapability("upload_on_private_cloud_storage", UOPCS_evo, UOPCS_pre, null);

		/* share_file_link */
		FOLAtom SFL_uploaded_on_cloud = new FOLAtom(new Predicate("uploaded_on_cloud", 1));
		SFL_uploaded_on_cloud.addArgument(doc);
		FOLAtom SFL_invoice = new FOLAtom(new Predicate("invoice", 1));
		SFL_invoice.addArgument(doc);
		FOLAtom SFL_not_has_cloud_space = new FOLAtom(new Predicate("has_cloud_space", 1));
		SFL_not_has_cloud_space.addArgument(usr);
		Negation neg2 = new Negation(SFL_not_has_cloud_space);
		FOLAtom SFL_user = new FOLAtom(new Predicate("user", 1));
		SFL_user.addArgument(usr);
		FOLAtom SFL_mailed = new FOLAtom(new Predicate("mailed_perm_link", 2));
		SFL_mailed.addArgument(doc);
		SFL_mailed.addArgument(usr);
		Negation SFL_notMailed = new Negation(SFL_mailed);
		Set SFL_Set = new HashSet<Variable>();
		SFL_Set.add(doc);
		SFL_Set.add(usr);
		Condition SFL_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction(
				new Conjunction(new Conjunction(SFL_uploaded_on_cloud, SFL_invoice), new Conjunction(neg2, SFL_user)),
				SFL_notMailed), SFL_Set));

		Set<EvolutionScenario> SFL_evo = new HashSet<>();
		CapabilityEvolutionScenario SFL_evo1 = new CapabilityEvolutionScenario("MailedPermLink");
		SFL_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("mailed_perm_link", the_invoice, a_user))));
		SFL_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("invoice", the_invoice))));
		SFL_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("user", a_user))));
		SFL_evo.add(SFL_evo1);

		this.SFL = new AbstractCapability("share_file_link", SFL_evo, SFL_pre, null);

		/* notify_storehouse_manager */
		FOLAtom NSM_notified = new FOLAtom(new Predicate("notified", 2));
		NSM_notified.addArgument(doc);
		NSM_notified.addArgument(usr);
		FOLAtom NSM_invoice = new FOLAtom(new Predicate("invoice", 1));
		NSM_invoice.addArgument(doc);
		FOLAtom NSM_user = new FOLAtom(new Predicate("user", 1));
		NSM_user.addArgument(usr);
		Set NSM_Set = new HashSet<Variable>();
		NSM_Set.add(doc);
		NSM_Set.add(usr);
		FOLAtom NSM_sent = new FOLAtom(new Predicate("sent", 2));
		NSM_sent.addArgument(doc);
		NSM_sent.addArgument(usr);
		Negation NSM_notSent = new Negation(NSM_sent);
		Condition NSM_pre = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(NSM_notified, NSM_notSent), new Conjunction(NSM_invoice, NSM_user)),
				NSM_Set));

		Set<EvolutionScenario> NSM_evo = new HashSet<>();
		CapabilityEvolutionScenario NSM_evo1 = new CapabilityEvolutionScenario("SentDeliveryOrderToStorehouseManager");
		NSM_evo1.addOperator(
				new AddStatement(new ExtDLPHead(new DLPAtom("sent", the_delivery_order, a_storehouse_manager))));
		NSM_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("delivery_order", the_delivery_order))));
		NSM_evo1.addOperator(new AddStatement(new ExtDLPHead(new DLPAtom("storehouse_manager", a_storehouse_manager))));
		NSM_evo.add(NSM_evo1);

		this.NSM = new AbstractCapability("notify_storehouse_manager", NSM_evo, NSM_pre, null);

		/**************************************************/

		/* Model construction */
		model = new GoalTreeModel(THO);
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

		this.wStart = new StateOfWorld();

		try {
			this.wStart.addFact_asString("order(an_order).");
			this.wStart.addFact_asString("available(an_order).");
			this.wStart.addFact_asString("user(a_user).");
			this.wStart.addFact_asString("user_data(the_user_data).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.regAndCloud = new StateOfWorld();
		try {
			this.regAndCloud.addFact_asString("order(an_order).");
			this.regAndCloud.addFact_asString("available(an_order).");
			this.regAndCloud.addFact_asString("user(a_user).");
			this.regAndCloud.addFact_asString("registered(a_user).");
			this.regAndCloud.addFact_asString("has_cloud_space(a_user).");
			this.regAndCloud.addFact_asString("logged(a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.regNoCloud = new StateOfWorld();
		try {
			this.regNoCloud.addFact_asString("order(an_order).");
			this.regNoCloud.addFact_asString("available(an_order).");
			this.regNoCloud.addFact_asString("user(a_user).");
			this.regNoCloud.addFact_asString("registered(a_user).");
			this.regNoCloud.addFact_asString("logged(a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.unknown = new StateOfWorld();
		try {
			this.unknown.addFact_asString("order(an_order).");
			this.unknown.addFact_asString("available(an_order).");
			this.unknown.addFact_asString("user(a_user).");
			this.unknown.addFact_asString("logged(a_user).");
			this.unknown.addFact_asString("user_data(the_user_data).");
			this.unknown.addFact_asString("uncomplete(the_user_data).");
			this.unknown.addFact_asString("unregistered(a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.known = new StateOfWorld();
		try {
			this.known.addFact_asString("order(an_order).");
			this.known.addFact_asString("available(an_order).");
			this.known.addFact_asString("user(a_user).");
			this.known.addFact_asString("logged(a_user).");
			this.known.addFact_asString("user_data(the_user_data).");
			this.known.addFact_asString("complete(the_user_data).");
			this.known.addFact_asString("unregistered(a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.uncompleteRegForm = new StateOfWorld();
		try {
			this.uncompleteRegForm.addFact_asString("order(an_order).");
			this.uncompleteRegForm.addFact_asString("available(an_order).");
			this.uncompleteRegForm.addFact_asString("user(a_user).");
			this.uncompleteRegForm.addFact_asString("logged(a_user).");
			this.uncompleteRegForm.addFact_asString("user_data(the_user_data).");
			this.uncompleteRegForm.addFact_asString("uncomplete(the_user_data).");
			this.uncompleteRegForm.addFact_asString("unregistered(a_user).");
			this.uncompleteRegForm.addFact_asString("uncomplete(the_registration_form).");
			this.uncompleteRegForm.addFact_asString("registration_form(the_registration_form).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.acceptedOrderNoCloud = new StateOfWorld();
		try {
			this.acceptedOrderNoCloud.addFact_asString("order(an_order).");
			this.acceptedOrderNoCloud.addFact_asString("user(a_user).");
			this.acceptedOrderNoCloud.addFact_asString("logged(a_user).");
			this.acceptedOrderNoCloud.addFact_asString("registered(a_user).");
			this.acceptedOrderNoCloud.addFact_asString("accepted(an_order).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.acceptedOrderCloud = new StateOfWorld();
		try {
			this.acceptedOrderCloud.addFact_asString("order(an_order).");
			this.acceptedOrderCloud.addFact_asString("user(a_user).");
			this.acceptedOrderCloud.addFact_asString("logged(a_user).");
			this.acceptedOrderCloud.addFact_asString("registered(a_user).");
			this.acceptedOrderCloud.addFact_asString("has_cloud_space(a_user).");
			this.acceptedOrderCloud.addFact_asString("accepted(an_order).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.refusedOrderNoCloud = new StateOfWorld();
		try {
			this.refusedOrderNoCloud.addFact_asString("order(an_order).");
			this.refusedOrderNoCloud.addFact_asString("user(a_user).");
			this.refusedOrderNoCloud.addFact_asString("logged(a_user).");
			this.refusedOrderNoCloud.addFact_asString("registered(a_user).");
			this.refusedOrderNoCloud.addFact_asString("refused(an_order).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.refusedOrderCloud = new StateOfWorld();
		try {
			this.refusedOrderCloud.addFact_asString("order(an_order).");
			this.refusedOrderCloud.addFact_asString("user(a_user).");
			this.refusedOrderCloud.addFact_asString("logged(a_user).");
			this.refusedOrderCloud.addFact_asString("registered(a_user).");
			this.refusedOrderCloud.addFact_asString("has_cloud_space(a_user).");
			this.refusedOrderCloud.addFact_asString("refused(an_order).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.notifyFailureCloud = new StateOfWorld();
		try {
			this.notifyFailureCloud.addFact_asString("order(an_order).");
			this.notifyFailureCloud.addFact_asString("user(a_user).");
			this.notifyFailureCloud.addFact_asString("logged(a_user).");
			this.notifyFailureCloud.addFact_asString("registered(a_user).");
			this.notifyFailureCloud.addFact_asString("has_cloud_space(a_user).");
			this.notifyFailureCloud.addFact_asString("refused(an_order).");
			this.notifyFailureCloud.addFact_asString("sent(failure_order, a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.notifyFailureNoCloud = new StateOfWorld();
		try {
			this.notifyFailureNoCloud.addFact_asString("order(an_order).");
			this.notifyFailureNoCloud.addFact_asString("user(a_user).");
			this.notifyFailureNoCloud.addFact_asString("logged(a_user).");
			this.notifyFailureNoCloud.addFact_asString("registered(a_user).");
			this.notifyFailureNoCloud.addFact_asString("refused(an_order).");
			this.notifyFailureNoCloud.addFact_asString("sent(failure_order, a_user).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.availableInvoiceCloud = new StateOfWorld();
		try {
			this.availableInvoiceCloud.addFact_asString("order(an_order).");
			this.availableInvoiceCloud.addFact_asString("user(a_user).");
			this.availableInvoiceCloud.addFact_asString("logged(a_user).");
			this.availableInvoiceCloud.addFact_asString("registered(a_user).");
			this.availableInvoiceCloud.addFact_asString("has_cloud_space(a_user).");
			this.availableInvoiceCloud.addFact_asString("accepted(an_order).");
			this.availableInvoiceCloud.addFact_asString("available(the_invoice).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.availableInvoiceNoCloud = new StateOfWorld();
		try {
			this.availableInvoiceNoCloud.addFact_asString("order(an_order).");
			this.availableInvoiceNoCloud.addFact_asString("user(a_user).");
			this.availableInvoiceNoCloud.addFact_asString("logged(a_user).");
			this.availableInvoiceNoCloud.addFact_asString("registered(a_user).");
			this.availableInvoiceNoCloud.addFact_asString("accepted(an_order).");
			this.availableInvoiceNoCloud.addFact_asString("available(the_invoice).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.uploadedOnUserCloud = new StateOfWorld();
		try {
			this.uploadedOnUserCloud.addFact_asString("order(an_order).");
			this.uploadedOnUserCloud.addFact_asString("user(a_user).");
			this.uploadedOnUserCloud.addFact_asString("logged(a_user).");
			this.uploadedOnUserCloud.addFact_asString("registered(a_user).");
			this.uploadedOnUserCloud.addFact_asString("has_cloud_space(a_user).");
			this.uploadedOnUserCloud.addFact_asString("accepted(an_order).");
			this.uploadedOnUserCloud.addFact_asString("available(the_invoice).");
			this.uploadedOnUserCloud.addFact_asString("uploaded_on_cloud(the_invoice).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.uploadedOnPrivateCloud = new StateOfWorld();
		try {
			this.uploadedOnPrivateCloud.addFact_asString("order(an_order).");
			this.uploadedOnPrivateCloud.addFact_asString("user(a_user).");
			this.uploadedOnPrivateCloud.addFact_asString("logged(a_user).");
			this.uploadedOnPrivateCloud.addFact_asString("registered(a_user).");
			this.uploadedOnPrivateCloud.addFact_asString("accepted(an_order).");
			this.uploadedOnPrivateCloud.addFact_asString("invoice(the_invoice).");
			this.uploadedOnPrivateCloud.addFact_asString("available(the_invoice).");
			this.uploadedOnPrivateCloud.addFact_asString("uploaded_on_cloud(the_invoice).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.sharedLink = new StateOfWorld();
		try {
			this.sharedLink.addFact_asString("order(an_order).");
			this.sharedLink.addFact_asString("user(a_user).");
			this.sharedLink.addFact_asString("logged(a_user).");
			this.sharedLink.addFact_asString("registered(a_user).");
			this.sharedLink.addFact_asString("accepted(an_order).");
			this.sharedLink.addFact_asString("available(the_invoice).");
			this.sharedLink.addFact_asString("uploaded_on_cloud(the_invoice).");
			this.sharedLink.addFact_asString("mailed_perm_link(the_invoice, a_user).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.notifiedStoreHouseNoCloud = new StateOfWorld();
		try {
			this.notifiedStoreHouseNoCloud.addFact_asString("order(an_order).");
			this.notifiedStoreHouseNoCloud.addFact_asString("user(a_user).");
			this.notifiedStoreHouseNoCloud.addFact_asString("logged(a_user).");
			this.notifiedStoreHouseNoCloud.addFact_asString("registered(a_user).");
			this.notifiedStoreHouseNoCloud.addFact_asString("accepted(an_order).");
			this.notifiedStoreHouseNoCloud.addFact_asString("available(the_invoice).");
			this.notifiedStoreHouseNoCloud.addFact_asString("uploaded_on_cloud(the_invoice).");
			this.notifiedStoreHouseNoCloud.addFact_asString("mailed_perm_link(the_invoice, a_user).");
			this.notifiedStoreHouseNoCloud.addFact_asString("sent(the_delivery_order, a_storehouse_manager).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		this.notifiedStoreHouseCloud = new StateOfWorld();
		try {
			this.notifiedStoreHouseCloud.addFact_asString("order(an_order).");
			this.notifiedStoreHouseCloud.addFact_asString("user(a_user).");
			this.notifiedStoreHouseCloud.addFact_asString("registered(a_user).");
			this.notifiedStoreHouseCloud.addFact_asString("logged(a_user).");
			this.notifiedStoreHouseCloud.addFact_asString("has_cloud_space(a_user).");
			this.notifiedStoreHouseCloud.addFact_asString("accepted(the_order).");
			this.notifiedStoreHouseCloud.addFact_asString("available(the_invoice).");
			this.notifiedStoreHouseCloud.addFact_asString("uploaded_on_cloud(the_invoice).");
			this.notifiedStoreHouseCloud.addFact_asString("sent(the_delivery_order, a_storehouse_manager).");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
			e.printStackTrace();
		}

		/*
		 * this.wStart.addFact_asASP(new ExtDLPHead(new DLPAtom("order",
		 * an_order))); this.wStart.addFact_asASP(new ExtDLPHead(new
		 * DLPAtom())); this.wStart.addFact_asASP(new ExtDLPHead(new
		 * DLPAtom("available", an_order))); this.wStart.addFact_asASP(new
		 * ExtDLPHead(new DLPAtom("user", a_user)));
		 * this.wStart.addFact_asASP(new ExtDLPHead(new DLPAtom("user_data",
		 * the_user_data)));
		 */
		this.nodewStart = new WorldNode(this.wStart);

		/* Model construction */
		model = new GoalTreeModel(THO);
		firstLevel = new ArrayList<>();
		firstLevel.add(TWO);
		firstLevel.add(TPO);
		secondLevel = new ArrayList<>();
		secondLevel.add(TPAO);
		secondLevel.add(TNF);
		thirdLevel = new ArrayList<>();
		thirdLevel.add(TNI);
		thirdLevel.add(TDO);

		model.addAndArcs(THO, firstLevel);
		model.addOrArcs(TPO, secondLevel);
		model.addAndArcs(TPAO, thirdLevel);

		this.startTokens = new ArrayList<>();
		this.startTokens.add(new Token("p3"));
		this.startTokens.add(new Token("p4"));

		this.noderegAndCloud = new WorldNode(this.regAndCloud);
		this.noderegNoCloud = new WorldNode(this.regNoCloud);
		this.nodeknown = new WorldNode(this.known);
		this.nodeunknown = new WorldNode(this.unknown);
		this.nodeuncompleteRegForm = new WorldNode(this.uncompleteRegForm);
		this.nodeacceptedOrderNoCloud = new WorldNode(this.acceptedOrderNoCloud);
		this.nodeacceptedOrderCloud = new WorldNode(this.acceptedOrderCloud);
		this.noderefusedOrderNoCloud = new WorldNode(this.refusedOrderNoCloud);
		this.noderefusedOrderCloud = new WorldNode(this.refusedOrderCloud);
		this.nodeavailableInvoiceNoCloud = new WorldNode(this.availableInvoiceNoCloud);
		this.nodenotifyFailureNoCloud = new WorldNode(this.notifyFailureNoCloud);
		this.nodeuploadedOnPrivateCloud = new WorldNode(this.uploadedOnPrivateCloud);

		this.eregAndCloud = new ENode(this.regAndCloud);
		this.euncompleteRegForm = new ENode(this.uncompleteRegForm);
		this.eregNoCloud = new ENode(this.regNoCloud);
		this.eknown = new ENode(this.known);
		this.eunknown = new ENode(this.known);
		this.eacceptedOrderCloud = new ENode(this.acceptedOrderCloud);
		this.eacceptedOrderNoCloud = new ENode(this.acceptedOrderNoCloud);
		this.erefusedOrderCloud = new ENode(this.refusedOrderCloud);
		this.erefusedOrderNoCloud = new ENode(this.refusedOrderNoCloud);
		this.eavailableInvoiceNoCloud = new ENode(this.availableInvoiceNoCloud);
		this.enotifyFailureNoCloud = new ENode(this.notifyFailureNoCloud);

	}

	private void setup_goals() {
		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Variable mng = new Variable("Mng");
		Variable fail = new Variable("Fail");

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

		/* to_handle_order */
		FOLAtom THO_received = new FOLAtom(new Predicate("received", 2));
		THO_received.addArgument(doc);
		THO_received.addArgument(usr);
		FOLAtom THO_order = new FOLAtom(new Predicate("order", 1));
		THO_order.addArgument(doc);
		FOLAtom THO_user = new FOLAtom(new Predicate("user", 1));
		THO_user.addArgument(usr);
		Set<Variable> THO_var = new HashSet<Variable>();
		THO_var.add(doc);
		THO_var.add(usr);
		Condition THO_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(THO_received, new Conjunction(THO_order, THO_user)), THO_var));

		FOLAtom THO_processed = new FOLAtom(new Predicate("processed", 1));
		THO_processed.addArgument(doc);
		Condition THO_fs = new Condition(new ExistsQuantifiedFormula(new Conjunction(THO_processed, THO_order), doc));

		Goal THO = new Goal("to_handle_order", THO_tc, THO_fs);

		/* to_wait_order */
		FOLAtom TWO_received = new FOLAtom(new Predicate("received", 2));
		TWO_received.addArgument(doc);
		TWO_received.addArgument(usr);
		FOLAtom TWO_order = new FOLAtom(new Predicate("order", 1));
		TWO_order.addArgument(doc);
		FOLAtom TWO_user = new FOLAtom(new Predicate("user", 1));
		TWO_user.addArgument(usr);
		Set<Variable> TWO_var = new HashSet<Variable>();
		TWO_var.add(doc);
		TWO_var.add(usr);
		Condition TWO_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(TWO_received, new Conjunction(TWO_order, TWO_user)), TWO_var));

		FOLAtom TWO_available = new FOLAtom(new Predicate("available", 1));
		TWO_available.addArgument(doc);
		Condition TWO_fs = new Condition(new ExistsQuantifiedFormula(new Conjunction(TWO_available, TWO_order), doc));

		Goal TWO = new Goal("to_wait_order", TWO_tc, TWO_fs);

		/* to_process_order */
		FOLAtom TPO_available = new FOLAtom(new Predicate("available", 1));
		TPO_available.addArgument(doc);
		FOLAtom TPO_order = new FOLAtom(new Predicate("order", 1));
		TPO_order.addArgument(doc);
		FOLAtom TPO_registered = new FOLAtom(new Predicate("registered", 1));
		TPO_registered.addArgument(usr);
		FOLAtom TPO_user = new FOLAtom(new Predicate("user", 1));
		TPO_user.addArgument(usr);
		Set<Variable> TPO_var = new HashSet<Variable>();
		TPO_var.add(doc);
		TPO_var.add(usr);
		Condition TPO_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(TPO_available, TPO_order), new Conjunction(TPO_registered, TPO_user)),
				TPO_var));

		FOLAtom TPO_processed = new FOLAtom(new Predicate("processed", 1));
		TPO_processed.addArgument(doc);
		Condition TPO_fs = new Condition(new ExistsQuantifiedFormula(new Conjunction(TPO_processed, TPO_order), doc));

		Goal TPO = new Goal("to_process_order", TPO_tc, TPO_fs);

		/* to_process_accepted_order */
		FOLAtom TPAO_accepted = new FOLAtom(new Predicate("accepted", 1));
		TPAO_accepted.addArgument(doc);
		FOLAtom TPAO_order = new FOLAtom(new Predicate("order", 1));
		TPAO_order.addArgument(doc);
		Condition TPAO_tc = new Condition(new ExistsQuantifiedFormula(new Conjunction(TPAO_accepted, TPAO_order), doc));

		FOLAtom TPAO_send = new FOLAtom(new Predicate("sent", 2));
		TPAO_send.addArgument(doc);
		TPAO_send.addArgument(mng);
		FOLAtom TPAO_delivery = new FOLAtom(new Predicate("delivery_order", 1));
		TPAO_delivery.addArgument(doc);
		FOLAtom TPAO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TPAO_manager.addArgument(mng);
		Set<Variable> TPAO_var = new HashSet<Variable>();
		TPAO_var.add(doc);
		TPAO_var.add(mng);
		Condition TPAO_fs = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(TPAO_send, new Conjunction(TPAO_delivery, TPAO_manager)), TPAO_var));

		Goal TPAO = new Goal("to_process_accepted_order", TPAO_tc, TPAO_fs);

		/* to_notify_invoce */
		FOLAtom TNI_registered = new FOLAtom(new Predicate("registered", 1));
		TNI_registered.addArgument(usr);
		FOLAtom TNI_user = new FOLAtom(new Predicate("user", 1));
		TNI_user.addArgument(usr);
		FOLAtom TNI_available = new FOLAtom(new Predicate("available", 1));
		TNI_available.addArgument(doc);
		FOLAtom TNI_invoice = new FOLAtom(new Predicate("invoice", 1));
		TNI_invoice.addArgument(doc);
		Set<Variable> TNI_var1 = new HashSet<Variable>();
		TNI_var1.add(usr);
		TNI_var1.add(doc);
		Condition TNI_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(TNI_registered, TNI_user), new Conjunction(TNI_available, TNI_invoice)),
				TNI_var1));

		FOLAtom TNI_send = new FOLAtom(new Predicate("sent", 2));
		TNI_send.addArgument(doc);
		TNI_send.addArgument(usr);
		Set<Variable> TNI_var2 = new HashSet<Variable>();
		TNI_var2.add(doc);
		TNI_var2.add(usr);
		Condition TNI_fs = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(TNI_send, new Conjunction(TNI_invoice, TNI_user)), TNI_var2));

		Goal TNI = new Goal("to_notify_invoice", TNI_tc, TNI_fs);

		/* to_deliver_order */
		FOLAtom TDO_send = new FOLAtom(new Predicate("sent", 2));
		TDO_send.addArgument(doc);
		TDO_send.addArgument(usr);
		FOLAtom TDO_invoice = new FOLAtom(new Predicate("invoice", 1));
		TDO_invoice.addArgument(doc);
		FOLAtom TDO_user = new FOLAtom(new Predicate("user", 1));
		TDO_user.addArgument(usr);
		Set<Variable> TDO_var1 = new HashSet<Variable>();
		TDO_var1.add(doc);
		TDO_var1.add(usr);
		Condition TDO_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(TDO_send, new Conjunction(TDO_invoice, TDO_user)), TDO_var1));

		FOLAtom TDO_delivery = new FOLAtom(new Predicate("delivery_order", 1));
		TDO_delivery.addArgument(doc);
		FOLAtom TDO_manager = new FOLAtom(new Predicate("storehouse_manager", 1));
		TDO_manager.addArgument(mng);
		Set<Variable> TDO_var2 = new HashSet<Variable>();
		TDO_var2.add(doc);
		TDO_var2.add(mng);
		Condition TDO_fs = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(TDO_send, new Conjunction(TDO_delivery, TDO_manager)), TDO_var2));

		Goal TDO = new Goal("to_delivery_order", TDO_tc, TDO_fs);

		/* to_notify_failure */
		FOLAtom TNF_refused = new FOLAtom(new Predicate("refused", 1));
		TNF_refused.addArgument(doc);
		FOLAtom TNF_order = new FOLAtom(new Predicate("order", 1));
		TNF_order.addArgument(doc);
		FOLAtom TNF_registered = new FOLAtom(new Predicate("registered", 1));
		TNF_registered.addArgument(usr);
		FOLAtom TNF_user = new FOLAtom(new Predicate("user", 1));
		TNF_user.addArgument(usr);
		Set<Variable> TNF_var1 = new HashSet<Variable>();
		TNF_var1.add(doc);
		TNF_var1.add(usr);
		Condition TNF_tc = new Condition(new ExistsQuantifiedFormula(
				new Conjunction(new Conjunction(TNF_refused, TNF_order), new Conjunction(TNF_registered, TNF_user)),
				TNF_var1));

		FOLAtom TNF_send = new FOLAtom(new Predicate("sent", 2));
		TNF_send.addArgument(new Constant("failure_order"));
		TNF_send.addArgument(usr);
		Condition TNF_fs = new Condition(new ExistsQuantifiedFormula(new Conjunction(TNF_send, TNF_user), usr));

		Goal TNF = new Goal("to_notify_failure", TNF_tc, TNF_fs);
	}

	/**
	 * 
	 */
	private void setup_domain_assumption() {
		this.domain = new AssumptionSet();
		try {

			domain.addAssumption_asString("role(X) :- user(X).");
			domain.addAssumption_asString("role(X) :- storehouse_manager(X).");
			domain.addAssumption_asString("document(X) :- order(X).");
			domain.addAssumption_asString("document(X) :- invoice(X).");
			domain.addAssumption_asString("document(X) :- user_data(X).");
			domain.addAssumption_asString("document(X) :- registration_form(X).");
			domain.addAssumption_asString("order(X) :- delivery_order(X).");
			domain.addAssumption_asString(
					"processed(X) :- accepted(X), order(X), sent(Y,Z), delivery_order(Y), storehouse_manager(Z).");
			domain.addAssumption_asString("processed(X) :- refused(X), order(X), sent(failure_order,Y), user(Y).");
			domain.addAssumption_asString(
					"notified(X,Y) :- uploaded_on_cloud(X), document(X), has_cloud_space(Y), user(Y).");
			domain.addAssumption_asString("notified(X,Y) :- mailed_perm_link(X,Y), document(X), user(Y).");

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
	}

}
