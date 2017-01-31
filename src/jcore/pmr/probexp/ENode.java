package pmr.probexp;

import java.util.ArrayList;
import java.util.Comparator;

import layer.awareness.net.Token;
import petrinet.logic.Place;
import pmr.graph.Node;
import pmr.graph.WorldNode;

public class ENode implements Node{
	
	private WorldNode node;
	private ArrayList<Token> tokens;
	private int score;
	private boolean exit;
	
	public ENode( WorldNode node ) {
		this.node = node;
		this.exit = false;
	}
	
	public ENode( WorldNode node, ArrayList<Token> tokens, int score, boolean exit ) {
		this.node = node;
		this.tokens = tokens;
		this.score = score;
		this.exit = exit;
	}
	
	public WorldNode getWorldNode() {
		return node;
	}
	
	public void setTokens(ArrayList<Token> tokens){
		this.tokens = tokens;
	}
	
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	public void addToken(Token token){
		this.tokens.add(token);
	}
	
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public boolean isExitNode(){
		return this.exit;
	}
	
	//Comparatore per tenere le liste aggiornate secondo lo score
	public static Comparator<ENode> getScoreComparator(){
		Comparator<ENode> comp = new Comparator<ENode>(){
			@Override
			public int compare(ENode e1, ENode e2){
				return e2.score - e1.score;
			}
		};
		return comp;
	}
	
}
