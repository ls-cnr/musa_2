package org.icar.musa.pmr.problem_exploration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.jgrapht.graph.DirectedMultigraph;

public class WTS extends DirectedMultigraph<WTSNode, WTSEdge> {
	private static final long serialVersionUID = 1L;

	//private NetHierarchy pnmodel;
	private StateNode root;
	
	public WTS(StateNode root) {
		super(WTSEdge.class);
		
		this.root = root;
		//this.pnmodel = pnmodel;
		
		addVertex(root);
	}

//	public NetHierarchy getPnmodel() {
//		return pnmodel;
//	}

	public StateNode getRoot() {
		return root;
	}
	
	public void addExpansion(WTSExpansion exp) {
		StateNode exp_root = exp.getRoot();
		
		StateNode my_node = containsVertexByState(exp_root);
		if (my_node != null) {
			if (!exp.isMulti_expansion()) {
				safelyAddOutgoingNodes(exp, exp_root, my_node);
			} else {
				XorNode exp_xornode = exp.getXorNode();
				CapabilityEdge exp_capedge = (CapabilityEdge) exp.getEdge(exp_root, exp_xornode);
				
				WTSNode my_xornode_node = safelyAddXorNode(exp_xornode);
				addEdge(my_node, my_xornode_node,exp_capedge);
				
				safelyAddOutgoingNodes(exp, exp_xornode, my_xornode_node);
			}
		}
	}
	
	private void safelyAddOutgoingNodes(WTSExpansion exp, WTSNode a_node, WTSNode my_source) {
		Iterator<WTSEdge> edge_it = exp.outgoingEdgesOf(a_node).iterator();
		while (edge_it.hasNext()) {
			WTSEdge edge = edge_it.next();
			WTSNode dest_node =exp.getEdgeTarget(edge);
			WTSNode my_dest_node = safelyAddGenericNode(dest_node);
			addEdge(my_source,my_dest_node,edge);
		}	
	}

//	private void safelyAddEdge(WTSEdge edge, WTSNode my_source, WTSNode my_dest_node) {
//		addEdge(my_source, my_dest_node,edge);
//	}
	
	public WTSNode safelyAddGenericNode(WTSNode node) {
		if (node instanceof StateNode)
			return safelyAddStateNode((StateNode) node);
		else if (node instanceof XorNode)
			return safelyAddXorNode((XorNode) node);	
		return null;
	}

	private StateNode safelyAddStateNode(StateNode dest_node) {
		StateNode my_node = containsVertexByState(dest_node);
		if (my_node==null) {
			addVertex(dest_node);
			my_node = dest_node;
		}	
		return my_node;
	}

	private WTSNode safelyAddXorNode(XorNode exp_xornode) {
		addVertex(exp_xornode);
		return exp_xornode;
	}

	private StateNode containsVertexByState(StateNode exp_node) {
		StateNode my_node = null;
		Iterator<WTSNode> node_it = vertexSet().iterator();
		while (node_it.hasNext() & my_node==null) {
			WTSNode node = node_it.next();
			if (node instanceof StateNode) {
				StateNode state = (StateNode) node;
				if (state.getState().equals(exp_node.getState())) {
					my_node = state;
				}
			}
		}
		return my_node;
	}

	
	protected ArrayList<WTSNode> getOutgoingNodes(WTSNode focus) {
		ArrayList<WTSNode> final_nodes = new ArrayList<>();
		Iterator<WTSEdge> it = outgoingEdgesOf(focus).iterator();
		while (it.hasNext()) {
			WTSEdge edge = it.next();
			WTSNode node = getEdgeTarget(edge);
			final_nodes.add(node);
		}
		return final_nodes;
	}

	public void printForGraphviz(){
		System.out.println("digraph G {");
		HashMap<XorNode,String> map = new HashMap();
		int xorcounter = 1;
		
		for (WTSNode node : vertexSet()) {
			if (node instanceof StateNode) {
				StateNode state = (StateNode) node;
				System.out.print("\""+state.getState().toString()+"\"");
				if (state.isStartNode())
					System.out.println("[style=bold,color=green];");
				else if (state.isExitNode())
					System.out.println("[style=bold,color=red];");
				else 
					System.out.println("[color=black];");
			} else if (node instanceof XorNode) {
				XorNode xornode = (XorNode) node;
				System.out.println("X"+xorcounter+";");
				map.put(xornode, "X"+xorcounter);
				xorcounter++;
			}
		}
	
		for (WTSNode node : vertexSet()) {
			for (WTSEdge edge : outgoingEdgesOf(node) ) {
				WTSNode dest = getEdgeTarget(edge);
				
				if (node instanceof StateNode) {
					StateNode state = (StateNode) node;
					System.out.print("\""+state.getState().toString()+"\"");
				} else if (node instanceof XorNode) {
					XorNode xornode = (XorNode) node;
					System.out.print(map.get(xornode));
				}
				System.out.print("->");
				if (dest instanceof StateNode) {
					StateNode deststate = (StateNode) dest;
					System.out.print("\""+deststate.getState().toString()+"\"");
				} else if (dest instanceof XorNode) {
					XorNode destxornode = (XorNode) dest;
					System.out.print(map.get(destxornode));
				}
				if (edge instanceof CapabilityEdge) {
					CapabilityEdge capedge = (CapabilityEdge) edge;
					System.out.println("[label=\""+ capedge.getCapabilityName()+ "\"]");
				} else if (edge instanceof ScenarioEdge) {
					ScenarioEdge scenedge = (ScenarioEdge) edge;
					System.out.println("[label=\""+ scenedge.getScenario()+ "\"]");
				} else {
					System.out.println("");
				}
				
			}
		}

		
		
		System.out.println("}");
	}

}
