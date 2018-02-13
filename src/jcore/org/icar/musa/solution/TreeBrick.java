package org.icar.musa.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.icar.musa.pmr.problem_exploration.StateNode;
import org.icar.musa.pmr.problem_exploration.WTSNode;
import org.icar.musa.pmr.problem_exploration.XorNode;


public class TreeBrick {
	private ArrayList<TreeBrick> children;
	private WTSNode node;
	
	private boolean leads_to_exit;
	private boolean leads_to_loop;
	Set<StateNode> loops;

	public TreeBrick(WTSNode root) {
		node = root;
		children = new ArrayList<>();
		loops = new HashSet<>();
		
		leads_to_exit = false;
		leads_to_loop = false;
	}
	
	public WTSNode getNode() {
		return node;
	}
	
	public boolean isLoop() {	
		return node instanceof WTSLoop;
	}
	
	public Set<StateNode> getLoops() {
		return loops;
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public boolean leadsToExit() {
		return leads_to_exit;
	}

	public boolean leadsToLoop() {
		return leads_to_loop;
	}

	public boolean isXor() {
		return (node instanceof XorNode);
	}
	
	public void update_metadata() {
		loops.clear();
		
		for (TreeBrick sub : children) {		// update in detph
			sub.update_metadata();
		}
		
		if (isLeaf()) {									
			if (isLoop()) {								// IF leaf-loop node
				leads_to_loop = true;			// set loop flag
				leads_to_exit = false;
				
				WTSLoop n = (WTSLoop) node;
				loops.add(n.getLoop());			// set loop reference
				
			} else {										// IF leaf-normal node
				leads_to_loop = false;
				leads_to_exit = false;
				if (node instanceof StateNode) {
					StateNode n = (StateNode) node;
					leads_to_exit = n.isExitNode();	// set loop flag
				}
			}
		} 
		
		if (!isLeaf()) {									// IF intermediate node
			leads_to_loop = false;
			leads_to_exit = true;
			for (TreeBrick sub : children) {
				if (!sub.leads_to_exit)
					leads_to_exit = false;			// set leads_to_exit flag
				if (sub.leads_to_loop)
					loops.addAll(sub.getLoops());		// search for sub-loops
			}
			loops.remove(getNode()); 					// remove self from loop
			if (!loops.isEmpty())
				leads_to_loop = true;					// set loop flag
		}
	}

	/* tries to append the sequence at the end */
	public boolean appendSequence(WTSNode src, WTSNode dst, boolean loop) {
		return append(src, dst, loop);
	}

	/**
	 * @param src
	 * @param dst
	 * @param loop
	 * @return
	 */
	private boolean append(WTSNode src, WTSNode dst, boolean loop) {
		if (dst.equals(node)) {
			loop = true;
		}
		
		if (src.equals(node)) {
			if (isLeaf()) {
				addChild(dst,loop);
				return true;
			} else if (isXor()) {
				addChild(dst,loop);
				return true;
			}
			return false;
		
		} else {
			if (!isLeaf()) {
				boolean append = false;
				for (TreeBrick sub : children) {
					if (sub.appendSequence(src,dst,loop))
						append = true;
				}
				return append;
			} else {
				return false;
			}
		}
	}
		
	/* return a new tree if the sequence attaches */
	public TreeBrick clone_if_attach(WTSNode src, WTSNode dst,boolean loop) {
		if (dst.equals(node)) {
			loop = true;
		}
		if ( src.equals(node) ) {
			
			TreeBrick clone_node = new TreeBrick(node);
			TreeBrick trunk = trunk(dst);
			//System.out.println("TRUNK:" +trunk);
			if (trunk!=null)
				clone_node.attachExistingNodeAsChild( trunk );
			else
				clone_node.addChild(dst,loop);
			return clone_node;
			
		} else {
			if (isLeaf()) {
				return null;
			}
			
			if (!isLeaf()) {
				TreeBrick manual_clone = new TreeBrick(node);
				boolean contain_src = false;
				for (TreeBrick sub : children) {
					TreeBrick new_sub = sub.clone_if_attach(src,dst,loop);
					if (new_sub!=null) {
						manual_clone.attachExistingNodeAsChild(new_sub);
						contain_src = true;
					} else {
						manual_clone.attachExistingNodeAsChild(sub.cloneBrick());
					}
				}
				if (contain_src==true)
					return manual_clone;
				else
					return null;
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
		
	
	private void attachExistingNodeAsChild(TreeBrick child) {
		children.add(child);
	}
	
	
	private TreeBrick cloneBrick() {
		TreeBrick clone = new TreeBrick(node);
		for (TreeBrick sub : children) {
			TreeBrick sub_clone = sub.cloneBrick();
			clone.attachExistingNodeAsChild(sub_clone);
		}	
		return clone;
	}
	
	
	private void addChild(WTSNode child_node,boolean loop) {
		if (loop) {
			TreeBrick sub = new TreeBrick(new WTSLoop((StateNode) child_node));
			children.add(sub);
		} else {
			TreeBrick sub = new TreeBrick(child_node);
			
			if (child_node instanceof StateNode) {
				StateNode s = (StateNode) child_node;				
			}
			
			children.add(sub);
		}
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
		if (node instanceof WTSLoop) {
			WTSLoop loop = (WTSLoop) node;
			System.out.print(loop.getLoop().toString()+"(L)");
		} else {
			System.out.print(node.toString());
		}
		if (leads_to_exit)
			System.out.print("[leads_to_exit]");
		if (leads_to_loop)
			System.out.print("[leads_to_loop]");
		System.out.println("");
		for (TreeBrick sub : children) {
			sub.log(tab);
		}
	}


	
	
	
}
