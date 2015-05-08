package algo.tree.traversal;

import algo.tree.BinarySearchTree.BSTNode;

public abstract class BinaryTreeTraversal<T extends Comparable<T>> {

	public void traverse(BSTNode<T> node) {
		if (node == null) return;
		
		before(node);
		traverse(node.left());
		middle(node);
		traverse(node.right());
		after(node);
	}
	
	protected abstract void before(BSTNode<T> node);
	protected abstract void middle(BSTNode<T> node);
	protected abstract void after(BSTNode<T> node);
}
