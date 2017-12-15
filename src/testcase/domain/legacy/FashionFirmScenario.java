package domain.legacy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import communication.translator.ExtDLPHead;
import datalayer.awareness.AbstractCapability;
import datalayer.awareness.AssumptionSet;
import datalayer.awareness.Requirements;
import datalayer.awareness.LTL.formulamodel.FormulaBTConstruction;
import datalayer.awareness.LTL.formulamodel.LTLGoal;
import datalayer.world.Condition;
import datalayer.world.StateOfWorld;
import datalayer.world.evolution.AddStatement;
import datalayer.world.evolution.CapabilityEvolutionScenario;
import datalayer.world.evolution.EvolutionScenario;
import datalayer.world.evolution.RemoveStatement;
import domain.Scenario;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.ExistsQuantifiedFormula;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.logics.fol.syntax.Negation;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class FashionFirmScenario implements Scenario {

	@Override
	public String getName() {
		return "b2b fashion firm scenario";
	}

	@Override
	public AssumptionSet getDomainAssumptions() {
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
		} catch (exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		return domain;
	}

	@Override
	public Requirements getRequirements() {
		String g1 = "received(doc,usr) && (order(doc)&&user(usr)) -> F(processed(doc)&&order(doc))";
		String g2 = "received(doc,usr)&&(order(doc)&&user(usr)) -> F(available(doc)&&order(doc))";
		String g3 = "available(doc)&&order(doc)) -> F(processed(doc))";
		String g4 = "accepted(doc)&&order(doc)) -> F(sent(order,man)&&(delivery_order(order)&&storehouse_manager(man))";
		
		
		LTLGoal g = FormulaBTConstruction.construct("");
		
		return null;
	}

	@Override
	public ArrayList<AbstractCapability> getCapabilitySet() {
		ArrayList<AbstractCapability> capabilities = new ArrayList<AbstractCapability>();
		capabilities.add(check_user());
		capabilities.add(add_user());
		capabilities.add(send_registration_form());
		capabilities.add(wait_user_data());
		capabilities.add(check_storehouse());
		capabilities.add(notify_stock_failure());
		capabilities.add(generate_invoice());
		capabilities.add(upload_on_user_cloud_storage());
		capabilities.add(upload_on_private_cloud_storage());
		capabilities.add(share_file_link());
		capabilities.add(notify_storehouse_manager());
		return capabilities;
	}

	private AbstractCapability check_user() {
		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Constant a_user = new Constant("a_user");
		Constant the_user_data = new Constant("the_user_data");
		
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
		
		return new AbstractCapability("check_user", CU_evo, CU_pre, null);	}

	private AbstractCapability add_user() {
		FOLAtom AU_complete = new FOLAtom( new Predicate("complete",1));
		AU_complete.addArgument(new Variable("Doc"));
		FOLAtom AU_user_data = new FOLAtom( new Predicate("user_data",1));
		AU_user_data.addArgument(new Variable("Doc"));
		FOLAtom AU_unregistered = new FOLAtom( new Predicate("unregistered",1));
		AU_unregistered.addArgument(new Variable("Usr"));
		FOLAtom AU_user = new FOLAtom( new Predicate("user",1));
		AU_user.addArgument(new Variable("Usr"));
		Set AU_Set = new HashSet<Variable>();
		AU_Set.add(new Variable("Doc"));
		AU_Set.add(new Variable("Usr"));
		Condition AU_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction(new Conjunction(AU_complete, AU_user_data), new Conjunction(AU_unregistered, AU_user)), AU_Set));

		Set<EvolutionScenario> AU_evo = new HashSet<>();
		CapabilityEvolutionScenario AU_evo1 = new CapabilityEvolutionScenario("RegisteredUser");
		AU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registered", new Constant("a_user"))) ) );
		AU_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", new Constant("a_user"))) ) );
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("unregistered", new Constant("a_user")))));
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("complete", new Constant("the_user_data")))));
		AU_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("user_data", new Constant("the_user_data")))));
		AU_evo.add(AU_evo1);
		
		return new AbstractCapability("add_user", AU_evo, AU_pre, null);
	}
	
	private AbstractCapability send_registration_form() {
		FOLAtom SRF_uncomplete = new FOLAtom( new Predicate("uncomplete",1));
		SRF_uncomplete.addArgument(new Variable("Doc"));
		FOLAtom SRF_user_data = new FOLAtom( new Predicate("user_data",1));
		SRF_user_data.addArgument(new Variable("Doc"));
		FOLAtom SRF_unregistered = new FOLAtom( new Predicate("unregistered",1));
		SRF_unregistered.addArgument(new Variable("Usr"));
		FOLAtom SRF_user = new FOLAtom( new Predicate("user",1));
		SRF_user.addArgument(new Variable("Usr"));
		Set SRF_Set = new HashSet<Variable>();
		SRF_Set.add(new Variable("Doc"));
		SRF_Set.add(new Variable("Usr"));
		Condition SRF_pre = new Condition(new ExistsQuantifiedFormula( new Conjunction(new Conjunction(SRF_uncomplete, SRF_user_data), new Conjunction(SRF_unregistered, SRF_user)), SRF_Set ));

		Set<EvolutionScenario> SRF_evo = new HashSet<>();
		CapabilityEvolutionScenario SRF_evo1 = new CapabilityEvolutionScenario("UncompleteRegistrationForm");
		SRF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uncomplete", new Constant("the_registration_form"))) ) );
		SRF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("registration_form", new Constant("the_registration_form"))) ) );
		SRF_evo.add(SRF_evo1);
		
		return new AbstractCapability("send_registration_form", SRF_evo, SRF_pre, null);
	}
	
	private AbstractCapability wait_user_data() {
		FOLAtom WUD_uncomplete = new FOLAtom( new Predicate("uncomplete",1));
		WUD_uncomplete.addArgument(new Variable("Doc"));
		FOLAtom WUD_registration_form = new FOLAtom( new Predicate("registration_form",1));
		WUD_registration_form.addArgument(new Variable("Doc"));
		Condition WUD_pre = new Condition( new ExistsQuantifiedFormula( new Conjunction(WUD_uncomplete, WUD_registration_form), new Variable("Doc")));

		Set<EvolutionScenario> WUD_evo = new HashSet<>();
		CapabilityEvolutionScenario WUD_evo1 = new CapabilityEvolutionScenario("CompleteForm");
		WUD_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("complete", new Constant("the_user_data"))) ) );
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", new Constant("the_user_data")))));
		WUD_evo1.addOperator(new RemoveStatement(new ExtDLPHead(new DLPAtom("uncomplete", new Constant("the_registration_form")))));
		WUD_evo1.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("registration_form", new Constant("the_registration_form"))) ) );
		WUD_evo.add(WUD_evo1);
		CapabilityEvolutionScenario WUD_evo2 = new CapabilityEvolutionScenario("UncompleteForm");
		WUD_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("uncomplete", new Constant("the_registration_form"))) ) );
		WUD_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("registration_form", new Constant("the_registration_form"))) ) );
		WUD_evo.add(WUD_evo2);
		
		return new AbstractCapability("wait_user_data", WUD_evo, WUD_pre, null);
	}
	
	private AbstractCapability check_storehouse() {
		FOLAtom CS_available = new FOLAtom( new Predicate("available",1));
		CS_available.addArgument(new Variable("Doc"));
		FOLAtom CS_order = new FOLAtom( new Predicate("order",1));
		CS_order.addArgument(new Variable("Doc"));
		FOLAtom CS_registered = new FOLAtom( new Predicate("registered",1));
		CS_registered.addArgument(new Variable("Usr"));
		FOLAtom CS_user = new FOLAtom( new Predicate("user",1));
		CS_user.addArgument(new Variable("Usr"));
		Set CS_Set = new HashSet<Variable>();
		CS_Set.add(new Variable("Doc"));
		CS_Set.add(new Variable("Usr"));
		Condition CS_pre = new Condition(new ExistsQuantifiedFormula( new Conjunction(new Conjunction(CS_available, CS_order), new Conjunction(CS_registered, CS_user)), CS_Set ));

		Set<EvolutionScenario> CS_evo = new HashSet<>();
		CapabilityEvolutionScenario CS_evo1 = new CapabilityEvolutionScenario("AcceptableOrder");
		CS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("accepted", new Constant("an_order"))) ) );
		CS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("order", new Constant("an_order"))) ) );
		CS_evo1.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("available", new Constant("an_order"))) ) );
		CS_evo.add(CS_evo1);
		CapabilityEvolutionScenario CS_evo2 = new CapabilityEvolutionScenario("UnacceptableOrder");
		CS_evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("refused", new Constant("an_order"))) ) );
		CS_evo2.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("order", new Constant("an_order"))) ) );
		CS_evo2.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("available", new Constant("an_order"))) ) );
		CS_evo.add(CS_evo2);
		
		return new AbstractCapability("check_storehouse", CS_evo, CS_pre, null);
	}
	
	private AbstractCapability notify_stock_failure() {
		FOLAtom NSF_refused = new FOLAtom( new Predicate("refused",1));
		NSF_refused.addArgument(new Variable("Doc"));
		FOLAtom NSF_order = new FOLAtom( new Predicate("order",1));
		NSF_order.addArgument(new Variable("Doc"));
		FOLAtom NSF_registered = new FOLAtom( new Predicate("registered",1));
		NSF_registered.addArgument(new Variable("Usr"));
		FOLAtom NSF_user = new FOLAtom( new Predicate("user",1));
		NSF_user.addArgument(new Variable("Usr"));
		FOLAtom NSF_ord_fail = new FOLAtom( new Predicate("order_failure",1));
		NSF_ord_fail.addArgument(new Variable("Doc"));
		Set NSF_Set = new HashSet<Variable>();
		NSF_Set.add(new Variable("Doc"));
		NSF_Set.add(new Variable("Usr"));
		Condition NSF_pre = new Condition(new ExistsQuantifiedFormula( new Conjunction( new Conjunction(new Conjunction(NSF_refused, NSF_order), new Conjunction(NSF_registered, NSF_user)), new Conjunction(NSF_ord_fail, NSF_order)), NSF_Set ));

		Set<EvolutionScenario> NSF_evo = new HashSet<>();
		CapabilityEvolutionScenario NSF_evo1 = new CapabilityEvolutionScenario("Failure");
		NSF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("sent", new Constant("failure_order"), new Constant("a_user"))) ) );
		NSF_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", new Constant("a_user"))) ) );
		NSF_evo1.addOperator( new RemoveStatement( new ExtDLPHead(new DLPAtom("order_failure", new Constant("an_order"))) ) );
		NSF_evo.add(NSF_evo1);
		
		return new AbstractCapability("notify_stock_failure", NSF_evo, NSF_pre, null);
	}
	
	private AbstractCapability generate_invoice() {
		FOLAtom GI_accepted = new FOLAtom( new Predicate("accepted",1));
		GI_accepted.addArgument(new Variable("Doc"));
		FOLAtom GI_order = new FOLAtom( new Predicate("order",1));
		GI_order.addArgument(new Variable("Doc"));
		FOLAtom GI_registered = new FOLAtom( new Predicate("registered",1));
		GI_registered.addArgument(new Variable("Usr"));
		FOLAtom GI_user = new FOLAtom( new Predicate("user",1));
		GI_user.addArgument(new Variable("Usr"));
		FOLAtom GI_invoice = new FOLAtom( new Predicate("available", 1));
		GI_invoice.addArgument(new Variable("Doc"));
		Negation GI_notAvailable = new Negation(GI_invoice);
		Set GT_Set = new HashSet<Variable>();
		GT_Set.add(new Variable("Doc"));
		GT_Set.add(new Variable("Usr"));
		Condition GI_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(GI_accepted, GI_order), new Conjunction(GI_registered, GI_user)), GI_notAvailable), GT_Set) );

		Set<EvolutionScenario> GI_evo = new HashSet<>();
		CapabilityEvolutionScenario GI_evo1 = new CapabilityEvolutionScenario("AvailableInvoice");
		GI_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("available", new Constant("the_invoice"))) ) );
		GI_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("invoice", new Constant("the_invoice"))) ) );
		GI_evo.add(GI_evo1);
		
		return new AbstractCapability("generate_invoice", GI_evo, GI_pre, null);	
	}
	
	private AbstractCapability upload_on_user_cloud_storage() {
		FOLAtom UOUCS_available = new FOLAtom( new Predicate("available",1));
		UOUCS_available.addArgument(new Variable("Doc"));
		FOLAtom UOUCS_invoice = new FOLAtom( new Predicate("invoice",1));
		UOUCS_invoice.addArgument(new Variable("Doc"));
		FOLAtom UOUCS_has_cloud_space = new FOLAtom( new Predicate("has_cloud_space",1));
		UOUCS_has_cloud_space.addArgument(new Variable("Usr"));
		FOLAtom UOUCS_user = new FOLAtom( new Predicate("user",1));
		UOUCS_user.addArgument(new Variable("Usr"));
		FOLAtom UOUCS_uploaded = new FOLAtom(new Predicate("uploaded_on_cloud",1));
		UOUCS_uploaded.addArgument(new Variable("Doc"));
		Negation UOUCS_notUploaded = new Negation(UOUCS_uploaded);
		Set UOUCS_Set = new HashSet<Variable>();
		UOUCS_Set.add(new Variable("Doc"));
		UOUCS_Set.add(new Variable("Usr"));
		Condition UOUCS_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(UOUCS_available, UOUCS_invoice), new Conjunction(UOUCS_has_cloud_space, UOUCS_user)), UOUCS_notUploaded), UOUCS_Set ));

		Set<EvolutionScenario> UOUCS_evo = new HashSet<>();
		CapabilityEvolutionScenario UOUCS_evo1 = new CapabilityEvolutionScenario("UploadedOnCloud");
		UOUCS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uploaded_on_cloud", new Constant("the_invoice"))) ) );
		UOUCS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("invoice", new Constant("the_invoice"))) ) );
		UOUCS_evo.add(UOUCS_evo1);
		
		return new AbstractCapability("upload_on_user_cloud_storage", UOUCS_evo, UOUCS_pre, null);
	}
	
	private AbstractCapability upload_on_private_cloud_storage() {
		FOLAtom UOPCS_available = new FOLAtom( new Predicate("available",1));
		UOPCS_available.addArgument(new Variable("Doc"));
		FOLAtom UOPCS_invoice = new FOLAtom( new Predicate("invoice",1));
		UOPCS_invoice.addArgument(new Variable("Doc"));
		FOLAtom UOPCS_not_has_cloud_space = new FOLAtom( new Predicate("has_cloud_space",1));
		UOPCS_not_has_cloud_space.addArgument(new Variable("Usr"));
		Negation neg1 = new Negation(UOPCS_not_has_cloud_space);
		FOLAtom UOPCS_user = new FOLAtom( new Predicate("user",1));
		UOPCS_user.addArgument(new Variable("Usr"));
		FOLAtom UOPCS_uploaded = new FOLAtom(new Predicate("uploaded_on_cloud",1));
		UOPCS_uploaded.addArgument(new Variable("Doc"));
		Negation UOPCS_notUploaded = new Negation(UOPCS_uploaded);
		Set UOPCS_Set = new HashSet<Variable>();
		UOPCS_Set.add(new Variable("Doc"));
		UOPCS_Set.add(new Variable("Usr"));
		Condition UOPCS_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(UOPCS_available, UOPCS_invoice), new Conjunction(neg1, UOPCS_user)), UOPCS_notUploaded), UOPCS_Set ));

		Set<EvolutionScenario> UOPCS_evo = new HashSet<>();
		CapabilityEvolutionScenario UOPCS_evo1 = new CapabilityEvolutionScenario("UploadedOnCloud");
		UOPCS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("uploaded_on_cloud", new Constant("the_invoice"))) ) );
		UOPCS_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("invoice", new Constant("the_invoice"))) ) );
		UOPCS_evo.add(UOPCS_evo1);
		
		return new AbstractCapability("upload_on_private_cloud_storage", UOPCS_evo, UOPCS_pre, null);
	}
	
	private AbstractCapability share_file_link() {
		FOLAtom SFL_uploaded_on_cloud = new FOLAtom( new Predicate("uploaded_on_cloud",1));
		SFL_uploaded_on_cloud.addArgument(new Variable("Doc"));
		FOLAtom SFL_invoice = new FOLAtom( new Predicate("invoice",1));
		SFL_invoice.addArgument(new Variable("Doc"));
		FOLAtom SFL_not_has_cloud_space = new FOLAtom( new Predicate("has_cloud_space",1));
		SFL_not_has_cloud_space.addArgument(new Variable("Usr"));
		Negation neg2 = new Negation(SFL_not_has_cloud_space);
		FOLAtom SFL_user = new FOLAtom( new Predicate("user",1));
		SFL_user.addArgument(new Variable("Usr"));
		FOLAtom SFL_mailed = new FOLAtom( new Predicate("mailed_perm_link",2));
		SFL_mailed.addArgument(new Variable("Doc"));
		SFL_mailed.addArgument(new Variable("Usr"));
		Negation SFL_notMailed = new Negation(SFL_mailed);
		Set SFL_Set = new HashSet<Variable>();
		SFL_Set.add(new Variable("Doc"));
		SFL_Set.add(new Variable("Usr"));
		Condition SFL_pre = new Condition(new ExistsQuantifiedFormula(new Conjunction( new Conjunction(new Conjunction(SFL_uploaded_on_cloud, SFL_invoice), new Conjunction(neg2, SFL_user)), SFL_notMailed), SFL_Set ));

		
		Set<EvolutionScenario> SFL_evo = new HashSet<>();
		CapabilityEvolutionScenario SFL_evo1 = new CapabilityEvolutionScenario("MailedPermLink");
		SFL_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("mailed_perm_link", new Constant("the_invoice"), new Constant("a_user"))) ) );
		SFL_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("invoice", new Constant("the_invoice"))) ) );
		SFL_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("user", new Constant("a_user"))) ) );
		SFL_evo.add(SFL_evo1);
		
		return new AbstractCapability("share_file_link", SFL_evo, SFL_pre, null);
	}
	
	private AbstractCapability notify_storehouse_manager() {
		FOLAtom NSM_notified = new FOLAtom( new Predicate("notified",2));
		NSM_notified.addArgument(new Variable("Doc"));
		NSM_notified.addArgument(new Variable("Usr"));
		FOLAtom NSM_invoice = new FOLAtom( new Predicate("invoice",1));
		NSM_invoice.addArgument(new Variable("Doc"));
		FOLAtom NSM_user = new FOLAtom( new Predicate("user",1));
		NSM_user.addArgument(new Variable("Usr"));
		Set NSM_Set = new HashSet<Variable>();
		NSM_Set.add(new Variable("Doc"));
		NSM_Set.add(new Variable("Usr"));
		FOLAtom NSM_sent = new FOLAtom ( new Predicate("sent",2));
		NSM_sent.addArgument(new Variable("Doc"));
		NSM_sent.addArgument(new Variable("Usr"));
		Negation NSM_notSent = new Negation(NSM_sent);
		Condition NSM_pre = new Condition(new ExistsQuantifiedFormula( new Conjunction(new Conjunction(NSM_notified, NSM_notSent), new Conjunction(NSM_invoice, NSM_user)), NSM_Set ));

		Set<EvolutionScenario> NSM_evo = new HashSet<>();
		CapabilityEvolutionScenario NSM_evo1 = new CapabilityEvolutionScenario("SentDeliveryOrderToStorehouseManager");
		NSM_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("sent",  new Constant("the_delivery_order") , new Constant("a_storehouse_manager"))) ) );
		NSM_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("delivery_order",  new Constant("the_delivery_order"))) ) );
		NSM_evo1.addOperator( new AddStatement( new ExtDLPHead(new DLPAtom("storehouse_manager", new Constant("a_storehouse_manager"))) ) );
		NSM_evo.add(NSM_evo1);
		
		return new AbstractCapability("notify_storehouse_manager", NSM_evo, NSM_pre, null);
	}

	@Override
	public StateOfWorld getInitialState() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
