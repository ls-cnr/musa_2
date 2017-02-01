package pmr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import layer.awareness.net.Token;
import pmr.graph.WTS;
import pmr.graph.WorldNode;
import pmr.probexp.ENode;
import pmr.probexp.ExpansionNode;
import pmr.probexp.NormalExpansionNode;

public class SolutionGraph {
	private WTS wts;
	private HashMap<WorldNode, ArrayList<Token>> tokenMap;
	private HashMap<WorldNode, Double> scoreMapping;
	private HashMap<WorldNode, WorldNode> exitNodeMap;
	
	public SolutionGraph(){
		this.wts = new WTS();
		this.tokenMap = new HashMap<>();
		this.scoreMapping = new HashMap<>();
		this.exitNodeMap = new HashMap<>();
	}
	
	public HashMap<WorldNode, WorldNode> getWTS(){
		return this.wts.getWTS();
	}
	
	public HashMap<WorldNode, ArrayList<Token>> getTokenMap(){
		return this.tokenMap;
	}
	
	public HashMap<WorldNode, Double> getScoreMapping(){
		return this.scoreMapping;
	}
	
	public HashMap<WorldNode, WorldNode> getExitNodeMap(){
		return this.exitNodeMap;
	}
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
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			this.tokenMap.put(temp.getWorldNode(), temp.getTokens());
		}
	}
	
	public void updateExitNodeList(ExpansionNode node){
		if(node.getSource().isExitNode() == true)	this.exitNodeMap.put(node.getSource().getWorldNode(), node.getSource().getWorldNode());
		Iterator<ENode> i = node.getDestination().iterator();
		while (i.hasNext()){
			ENode temp = i.next();
			if(temp.isExitNode() == true)	this.exitNodeMap.put(temp.getWorldNode(), temp.getWorldNode());
		}
	}
	
	public ArrayList<ArrayList<WorldNode>> getSolutions(){
		return this.wts.getSolutions(new WorldNode(null), this.exitNodeMap);
	}
}
