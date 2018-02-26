package org.icar.musa.solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
	
	private boolean solution;

	public TreeBrick(WTSNode root) {
		node = root;
		children = new ArrayList<>();
		loops = new HashSet<>();
		
		leads_to_exit = false;
		leads_to_loop = false;
	}
		
	public boolean isSolution() {
		return solution;
	}

	public void setSolution(boolean solution) {
		this.solution = solution;
	}

	public WTSNode getNode() {
		return node;
	}
	
	public boolean isLoop() {	
		return node instanceof AWLoop;
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
		
		for (TreeBrick sub : children) {		// update in detph
			sub.update_metadata();
		}
		
		if (isLeaf()) {									
			if (isLoop()) {								// IF leaf-loop node
				leads_to_loop = true;			// set loop flag
				leads_to_exit = false;
				
				AWLoop n = (AWLoop) node;
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
			leads_to_exit = true_if_at_least_an_exit_and_no_normal_nodes();
			leads_to_loop = true_if_at_least_one_loop();			
		}
	}

	private boolean true_if_at_least_one_loop() {
		boolean flag = false;
		loops.clear();
		for (TreeBrick sub : children) {
			if (sub.leads_to_loop)
				loops.addAll(sub.getLoops());
		}
		loops.remove(getNode()); 					// remove self from loop
		if (!loops.isEmpty())
			flag = true;					// set loop flag
		return flag;
	}

	private boolean true_if_at_least_an_exit_and_no_normal_nodes() {
		boolean one_exit = false;
		boolean no_normal = true;

		if (node instanceof XorNode) {
			XorNode n = (XorNode) node;
			if (children.size() < n.getCases())
				return false;
		}

		for (TreeBrick sub : children) {
			if (sub.leads_to_exit)
				one_exit = true;
			else if (!sub.leads_to_loop)
				no_normal = false;
		}
		
		return (one_exit & no_normal);
	}

	/* tries to append the sequence at the end */
	public boolean appendSequence(WTSNode src, WTSNode dst) {
		if (!solution)
			return append(src, dst, false);
		return false;
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
					if (sub.append(src,dst,loop))
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
			TreeBrick sub = new TreeBrick(new AWLoop((StateNode) child_node));
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
		if (node instanceof AWLoop) {
			AWLoop loop = (AWLoop) node;
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

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		TreeBrick other = (TreeBrick) obj;
		if (!node_equals(this,other) )
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		return result;
	}

	public boolean node_equals(TreeBrick node1,TreeBrick node2) {
		if (node1==null & node2!=null)
			return false;
		if (node2==null & node1!=null)
			return false;
		if (node1.getNode()==null & node2.getNode()!=null)
			return false;
		if (node2.getNode()==null & node1.getNode()!=null)
			return false;
		
		if (!node1.getNode().equals(node2.getNode()))
			return false;
		
		if (node1.getChilds().size()!=node2.getChilds().size())
			return false;
		
		for (TreeBrick s1 : node1.getChilds()) {
			TreeBrick s2 = exist_subnode_in_tree(node2,s1.getNode());
			if (s1 == null)
				return false;
			
			boolean compare = node_equals(s1,s2);
			if (compare==false)
				return false;
		}
		
		return true;
	}

	private TreeBrick exist_subnode_in_tree(TreeBrick tree, WTSNode node) {
		Iterator<TreeBrick> it = tree.getChilds().iterator();
		TreeBrick subnode = null;
		
		while (it.hasNext() & subnode==null) {
			TreeBrick s = it.next();
			if (s.getNode().equals(node))
				subnode = s;
		}
		
		return subnode;
	}

	public void print_as_inline() {
		System.out.print(node.toString()+" ");
		for (TreeBrick sub : children) {
			sub.print_as_inline();
		}
	}


	
	
	
}
