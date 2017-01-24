package pmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.NormalExpansionNode;

public class SolutionGraph {
	private WTS wts;
	private HashMap<WorldNode, ArrayList<String>> tokenMap;
	private HashMap<WorldNode, Double> scoreMapping;
	private HashMap<WorldNode, WorldNode> exitNodeMap;
	
	
	public void addNode(ExpansionNode node){
		this.wts.addNode(node);
		this.updateTokenMap(node);
		this.updateExitNodeList(node);
	}
	
	public void removeNode(WorldNode node){
		this.wts.removeNode(node);
		this.scoreMapping.remove(node);
		this.exitNodeMap.remove(node);
	}
	
	public void updateTokenMap(ExpansionNode node){
		this.tokenMap.put(node.getSource().getWorldNode(), node.getSource().getTokens());
		NormalExpansionNode tempnode = (NormalExpansionNode) node;
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			this.tokenMap.put(temp.getWorldNode(), temp.getTokens());
		}
	}
	
	public void updateExitNodeList(ExpansionNode node){
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			if(temp.isExitNode() == true)	this.exitNodeMap.put(temp.getWorldNode(), temp.getWorldNode());
		}
	}
}
