package pmr.probexp;

import java.util.ArrayList;

import petrinet.logic.Place;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public class ENode implements Node{
	
	private WorldNode node;
	private ArrayList<String> tokens;
	private double score;
	private boolean exit;
	
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
	
	public void setTokens(ArrayList<String> tokens){
		this.tokens = tokens;
	}
	
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	public void addToken(String token){
		this.tokens.add(token);
	}
	
	public ArrayList<String> getTokens() {
		return tokens;
	}
	
	public double getScore(){
		return this.score;
	}
	
	public void setScore(double score){
		this.score = score;
	}
	
	public boolean isExitNode(){
		return this.exit;
	}
}
