package algo.tree.traversal;

import algo.tree.BinarySearchTree.BSTNode;

public class InOrderTraversal<T extends Comparable<T>> extends BinaryTreeTraversal<T> {

	@Override
	protected void before(BSTNode<T> node) {
		// Empty for inorder
	}
	
	@Override
	protected void middle(BSTNode<T> node) {
		System.out.print(node.data() + ", ");
	}
	
	@Override
	protected void after(BSTNode<T> node) {
		// Empty for inorder
	}
}
