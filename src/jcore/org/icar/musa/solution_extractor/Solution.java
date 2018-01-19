package org.icar.musa.solution_extractor;

import java.util.Collections;

import org.icar.musa.core.runtime_entity.AbstractWorkflowNode;
import org.icar.musa.solution_extractor.distributed.Tree;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class Solution  {
	private Tree<AbstractWorkflowNode> tree;
	
	public Solution(Tree<AbstractWorkflowNode> tree) {
		this.tree = tree;
	}

	public Tree<AbstractWorkflowNode> getTree() {
		return tree;
	}
	
	public void print() {
		recursivePrintTree(tree,0);
	}
	
	private void recursivePrintTree(Tree<AbstractWorkflowNode> node, int depth) {
		if (node != null) {
			String tab = String.join("", Collections.nCopies(depth, "  "));
			System.out.println(tab + node.getValue().getAbstract_cap_name()+"/" + node.getValue().getAgent());
			for (Tree<AbstractWorkflowNode> t : node.getChildren())
				recursivePrintTree(t, depth + 1);
		}
	}

}
