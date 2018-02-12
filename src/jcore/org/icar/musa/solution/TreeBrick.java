package org.icar.musa.solution;

import java.util.ArrayList;
import java.util.List;

import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;


public class TreeBrick {
	private ArrayList<TreeBrick> children;
	private WTSNode node;

	public TreeBrick(WTSNode root) {
		node = root;
		children = new ArrayList<>();
	}
	
	public WTSNode getNode() {
		return node;
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}

	public boolean isXor() {
		return (node instanceof XorNode);
	}

	/* tries to append the sequence at the end */
	public boolean appendSequence(WTSNode src, WTSNode dst) {
		if (src.equals(node)) {
			if (isLeaf()) {
				addChild(dst);
				return true;
			} else if (isXor()) {
				addChild(dst);
				return true;
			}
			return false;
		
		} else {
			if (!isLeaf()) {
				boolean append = false;
				for (TreeBrick sub : children) {
					if (sub.appendSequence(src,dst))
						append = true;
				}
				return append;
			} else {
				return false;
			}
		}
	}
	
	public ArrayList<TreeBrick> clone_root_to_node(WTSNode node) {
		ArrayList<TreeBrick> res = new ArrayList<>();
		
		
		
		return res;
	}
	
	public ArrayList<TreeBrick> clone_node_to_leaves(WTSNode node) {
		ArrayList<TreeBrick> res = new ArrayList<>();
		
		
		
		return res;
	}
	
	
	/* return a new tree if the sequence attaches */
	public TreeBrick clone_if_attach(WTSNode src, WTSNode dst) {
		if ( src.equals(node) ) {
			
			TreeBrick clone_node = new TreeBrick(node);
			clone_node.attachChild( trunk(dst) );
			return clone_node;
			
		} else {
			if (!isLeaf()) {
				TreeBrick clone_node = new TreeBrick(node);
				
				boolean result = false;
				for (TreeBrick sub : children) {
					TreeBrick new_sub = sub.clone_if_attach(src,dst);
					if (new_sub!=null) {
						clone_node.attachChild(new_sub);
						result = true;
					} else {
						clone_node.attachChild(sub.cloneBrick());
					}
				}
				
				if (result==true)
					return clone_node;

			}
		}		
		
		return null;
	}
	
	/* search for the dest and clone all the rest */
	public TreeBrick trunk(WTSNode dst) {
		if (dst.equals(node) ) {
			/* dest trovata, clono tutto il resto */
			return cloneBrick();
		
		} else {
			
			/* provo a cercare tra i figli */
			/* ASSUNZIONE: non considero che piu' figli potrebbero avere lo stesso nodo */
			if (!isLeaf()) {
				TreeBrick result = null;
				for (TreeBrick sub : children) {
					TreeBrick new_sub = sub.trunk(dst);
					if (new_sub!=null) {
						result = new_sub;
					}
				}
				return result;
				
			} else {
				/* raggiunta la fine, dest non trovata */
				return null;				
			}
			
		}
	}
		
	public void attachChild(TreeBrick child) {
		children.add(child);
	}
	public void addChild(WTSNode child_node) {
		TreeBrick sub = new TreeBrick(child_node);
		children.add(sub);
	}
	public TreeBrick cloneBrick() {
		TreeBrick clone = new TreeBrick(node);
		for (TreeBrick sub : children) {
			TreeBrick sub_clone = sub.cloneBrick();
			clone.attachChild(sub_clone);
		}	
		return clone;
	}
	public List<TreeBrick> getChilds() {
		return children;
	}

	public void log(int tab) {
		for (int i=0; i<tab; i++) {
			System.out.print("\t");
		}
		//if (node instanceof XorNode) 
		tab++;
		System.out.println(node.toString());
		for (TreeBrick sub : children) {
			sub.log(tab);
		}
	}

	
	
	
}
