package pmr.probexp;

import java.util.ArrayList;

import petrinet.logic.Place;
import pmr.graph.WorldNode;

public class ENode {
	
	private WorldNode node;
	private ArrayList<String> tokens;
	private double score;
	
	public ENode( WorldNode node ) {
		this.node = node;
	}
	
	public ENode( WorldNode node, ArrayList<String> tokens, double score ) {
		this.node = node;
		this.tokens = tokens;
		this.score = score;
	}
	
	public WorldNode getWorldNode() {
		return node;
	}
	
	public ArrayList<String> getTokens() {
		return tokens;
	}
	
}
