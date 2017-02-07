package example;

import static org.junit.Assert.*;

import org.junit.Test;

import layer.awareness.AbstractCapability;
import layer.awareness.Goal;
import layer.semantic.AssumptionSet;
import layer.semantic.Condition;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.Predicate;
import net.sf.tweety.logics.commons.syntax.Variable;
import net.sf.tweety.logics.fol.syntax.Conjunction;
import net.sf.tweety.logics.fol.syntax.Disjunction;
import net.sf.tweety.logics.fol.syntax.FOLAtom;
import net.sf.tweety.lp.asp.parser.ParseException;
import net.sf.tweety.lp.asp.syntax.DLPAtom;

public class Prova {

	@Test
	public void test() {
		
		AssumptionSet domain = new AssumptionSet();	 
		try {
	
			domain.addAssumption_asString("role(X) :- user(X).");
			domain.addAssumption_asString("role(X) :- storehouse_manager(X).");
			domain.addAssumption_asString("document(X) :- order(X).");
			domain.addAssumption_asString("document(X) :- invoice(X).");
			domain.addAssumption_asString("document(X) :- user_data(X)");
			domain.addAssumption_asString("document(X) :- registration_form(X)");
			domain.addAssumption_asString("order(X) :- delivery_order(X).");
			domain.addAssumption_asString("processed(X) :- accepted(X), order(X), sent(Y,Z), delivery_order(Y), storehouse_manager(Z)");
			domain.addAssumption_asString("processed(X) :- refused(X), order(X), sent(failure_order,Y), user(Y)");
			domain.addAssumption_asString("notified(X,Y) :- uploaded_on_cloud(X), document(X), has_cloud_space(Y), user(Y)");
			domain.addAssumption_asString("notified(X,Y) :- mailed_perm_link(X,Y), document(X), user(Y)");
		
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (layer.semantic.exception.NotAllowedInAnAssumptionSet e) {
			e.printStackTrace();
		}
		
		/*************/

		Variable doc = new Variable("Doc");
		Variable usr = new Variable("Usr");
		Variable mng = new Variable("Mng");
		
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
		
		/*******************/
		
	}

}
