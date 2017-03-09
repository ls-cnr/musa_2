package configuration;

/**
 * This class describes a leaf node in the solution tree. This node can be
 * either a success state, a failure state or a loop state. If is it a loop
 * state, it is necessary to know which state it loops in.
 * 
 * @author Mirko Avantaggiato
 *
 */

public class LeafNode extends TreeNode {
	private boolean isSuccessState;
	private boolean isFailureState;

	private boolean isLoopState;
	private TreeNode toLoop;

	public boolean isLoop() {
		return this.isLoopState;
	}

	public boolean isSuccess() {
		return this.isSuccessState;
	}

	public boolean isFailure() {
		return this.isFailureState;
	}

	public TreeNode getLoopDestination() {
		return this.toLoop;
	}

}
