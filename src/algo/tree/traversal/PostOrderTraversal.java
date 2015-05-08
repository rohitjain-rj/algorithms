package algo.tree.traversal;

import algo.tree.BinarySearchTree.BSTNode;

public class PostOrderTraversal<T extends Comparable<T>> extends BinaryTreeTraversal<T> {
	
	@Override
	protected void before(BSTNode<T> node) {
		// Empty for post-order
	}
	
	@Override
	protected void middle(BSTNode<T> node) {
		// Empty for post-order
	}
	
	@Override
	protected void after(BSTNode<T> node) {
		System.out.print(node.data() + ", ");
	}
}
