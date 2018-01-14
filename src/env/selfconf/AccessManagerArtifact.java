// CArtAgO artifact code for project musa_2_0

package selfconf;

import java.util.LinkedList;
import java.util.List;

import org.icar.musa.agent_communication.auction.Bid;
import org.icar.musa.agent_communication.translator.JasonExpansionNode;
import org.icar.musa.agent_communication.translator.JasonExtNode;
import org.icar.musa.agent_communication.translator.JasonStateOfWorld;
import org.icar.musa.agent_communication.translator.TranslateError;
import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.proactive_means_end_reasoning.ExtendedNode;
import org.icar.musa.proactive_means_end_reasoning.GraphExpansion;
import org.icar.musa.proactive_means_end_reasoning.wts.WorldNode;
import org.icar.specification.linear_temporal_logic.formulamodel.FormulaBTConstruction;
import org.icar.specification.linear_temporal_logic.formulamodel.LTLGoal;
import org.icar.specification.linear_temporal_logic.net.PNHierarchy;
import org.icar.specification.linear_temporal_logic.net.TokenConf;

import cartago.*;
import jason.asSyntax.Term;

@ARTIFACT_INFO(
		  outports = {
		    @OUTPORT(name = "mygraph"),
		    @OUTPORT(name = "mysolution")
		  }
	) 
public class AccessManagerArtifact extends Artifact {
	
	private String spec_id_string;
	private int auction_id;
	private boolean bid_phase;
	
	private List<Bid> bids; 

	void init(String spec_id_string) {
		this.spec_id_string = spec_id_string;
				
		auction_id=0;
		
		bids = new LinkedList<Bid>();
		bid_phase=false;
	}
	
	/* interface: CONFIGURE */
	@OPERATION
	void set_initial_node(String node_string) {
		//System.out.println("set_initial_node "+node_string);

		try {
			execLinkedOp("mygraph","set_initial_state",node_string);
			StateOfWorld w = JasonStateOfWorld.term_string_to_object(node_string);
			
			// TODO recuperare i goal dal DB per settare gli initial tokens
			LTLGoal treeModel = FormulaBTConstruction.construct("G on(l1)");
			TokenConf startingTokens = new TokenConf(new PNHierarchy(treeModel));
			
			ExtendedNode enode = new ExtendedNode(w, startingTokens, 0, false, false);
			enode.setExit(false);
			Term term=JasonExtNode.object_to_term(enode);
			signal("announcement_new_node",spec_id_string,term);
			
		} catch (OperationException | TranslateError e) {
			e.printStackTrace();
		}
	}
	
	@OPERATION
	void get_number_of_nodes(OpFeedbackParam<Integer> num) {
		try {
			execLinkedOp("mygraph","get_node_number",num);
		} catch (OperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* interface: MANAGE AUCTION */
	@OPERATION
	void startAuction() {
		auction_id++;
		bid_phase = true;
		signal("announcement_new_auction",spec_id_string,auction_id);
	}

	@OPERATION
	void closeAuction() {
		bid_phase = false;

		Bid winner = null;
		double max_utilty = -1;
		
		for (Bid b : bids) {
			if (b.getValue() > max_utilty) {
				winner = b;
				max_utilty = b.getValue();
			}
		}
		
		if (winner != null) {
			signal("announcement_winner_auction",spec_id_string,auction_id,winner.getAgent());
		}
		bids.clear();
	}

	@OPERATION
	void bid(int auction_id,String participant, double utility) {
		if (auction_id == this.auction_id && bid_phase==true) {
			bids.add(new Bid(participant,utility));
		}
	}

	
	/* interface: GRANT ACCESS */
	@OPERATION
	void apply_changes(String expansion) {
		GraphExpansion exp;
		try {
			
			//System.out.println("EXP:"+expansion);
			execLinkedOp("mysolution","notifyENode",expansion);
			
			execLinkedOp("mygraph","expand",expansion, spec_id_string);
			exp = JasonExpansionNode.term_string_to_object(expansion);
			for(ExtendedNode temp : exp.getDestination()){
				//System.out.println("Auction: added "+exp.getSource().getWorldState().toSortedString()+"->"+exp.getCapability()+"->"+temp.getWorldState().toSortedString());
				Term term=JasonExtNode.object_to_term(temp);
				signal("announcement_new_node",spec_id_string,term);
			}
		} catch (OperationException e) {
			e.printStackTrace();
		} catch(TranslateError t){return;}
	}
	
	
	@OPERATION
	void print_graph() {
		try {
			execLinkedOp("mygraph","printGraph");
		} catch (OperationException e) {
			e.printStackTrace();
		}
	}
	
	
//	private WorldNode get_initial_node_for_test() {
//		StateOfWorld wStart = new StateOfWorld();
//		
//		try {
//			wStart.addFact_asString("order(an_order).");
//			wStart.addFact_asString("available(an_order).");
//			wStart.addFact_asString("user(a_user).");
//			wStart.addFact_asString("user_data(the_user_data).");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (layer.semantic.exception.NotAllowedInAStateOfWorld e) {
//			e.printStackTrace();
//		}
//		
//		return new WorldNode(wStart);
//	}
}

