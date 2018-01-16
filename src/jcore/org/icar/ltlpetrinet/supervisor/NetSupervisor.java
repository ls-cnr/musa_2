package org.icar.ltlpetrinet.supervisor;

import java.util.HashSet;
import java.util.Set;

import org.icar.ltlpetrinet.annotated_pn.PNStateEnum;
import org.icar.ltlpetrinet.hierarchical_model.HierarchyNode;
import org.icar.ltlpetrinet.hierarchical_model.NetHierarchy;
import org.icar.ltlpetrinet.hierarchical_model.PNNode;

import org.icar.musa.core.domain.StateOfWorld;
import org.icar.musa.core.runtime_entity.AssumptionSet;
import petrinet.logic.Place;

public class NetSupervisor {
	private NetHierarchy netmodel;
	private TokenConf token_conf;
	
	public NetSupervisor(NetHierarchy netmodel, TokenConf tokens) {
		super();
		this.netmodel = netmodel;
		this.token_conf = tokens;
	}
	
	public void prepareTokens() {
		set_all_tokens(netmodel.getRoot());
	}

	public void cleanTokens() {
		token_conf = new TokenConf(netmodel.getRoot().getName(),update_tokens_and_clear_all(netmodel.getRoot()));
	}

	public void update(StateOfWorld w,AssumptionSet assumptions) {	
		netmodel.getRoot().updateNet(w,assumptions);		
	}
	
	public PNStateEnum getState() {
		return netmodel.getRoot().getNetState();
	}
	
	public boolean isExit() {
		PNStateEnum state = netmodel.getRoot().getNetState();
		return (state == PNStateEnum.ACCEPTED);
	}

	private void set_all_tokens(HierarchyNode root) {
		for (HierarchyNode node : root.getDependencies().values()) {
			set_all_tokens(node);
		}
		
		if (root instanceof PNNode) {
			PNNode petrinet_node = (PNNode) root;
			for (Place p : petrinet_node.getPN().getPlaces()) {
				boolean markable = token_conf.contain_a_token(root.getName(),p.getName());
				if (markable) {
					p.setTokens(1);
				}
			}
		}
		
	}

	private Set<Token> update_tokens_and_clear_all(HierarchyNode root) {
		Set<Token> token_set = new HashSet<Token>();
		
		for (HierarchyNode node : root.getDependencies().values()) {
			Set<Token> sub_token_set = update_tokens_and_clear_all(node);
			token_set.addAll(sub_token_set);
		}

		if (root instanceof PNNode) {
			PNNode petrinet_node = (PNNode) root;
			for (Place p : petrinet_node.getPN().getPlaces()) {
				if (p.getTokens()>0) {
					token_set.add(new Token(p.getName(),petrinet_node.getName()));
					p.setTokens(0);
				}
			}
		}
			
		return token_set;
	}

	public TokenConf getTokenConfiguration() {
		return token_conf;
	}

	/* this method uses the distance (hop number) between petri nets places
	 * for calculating the distance to the total satisfaction
	 */
	public int calculate_partial_satisfaction() {	
		return netmodel.getRoot().calculate_partial_satisfaction();
	}







}
