package algo.tree.traversal;

import java.util.LinkedList;
import java.util.Queue;

import algo.tree.BinarySearchTree.BSTNode;

public class LevelOrderTraversal<T extends Comparable<T>> extends BinaryTreeTraversal<T> {

	@Override
	public void traverse(BSTNode<T> node) {
		Queue<BSTNode<T>> level = new LinkedList<>();
		level.add(node);
		
		while (!level.isEmpty()) {
			BSTNode<T> currentNode = level.poll();
			System.out.print(currentNode.data() + ", ");
			
			if (currentNode.left() != null) {
				level.add(currentNode.left());
			}
			
			if (currentNode.right() != null) {
				level.add(currentNode.right());
			}
		}
	}
	
	@Override
	protected void before(BSTNode<T> node) {
		
	}
	
	@Override
	protected void middle(BSTNode<T> node) {
	
	}
	
	@Override
	protected void after(BSTNode<T> node) {
		
	}
}
