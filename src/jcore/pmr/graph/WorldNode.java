package pmr.graph;

import java.util.HashSet;

import layer.awareness.AbstractCapability;
import layer.semantic.StateOfWorld;

public class WorldNode extends Node {

	
	public WorldNode(StateOfWorld worldState,  Node parent, AbstractCapability capability){
		super(worldState);
	}
	 //Costruttore per il primo nodo del grafo, non ha genitori.
	public WorldNode(StateOfWorld worldState){
		super(worldState);
	}
}	
