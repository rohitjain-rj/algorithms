package algo.tree.traversal;

import java.util.Stack;

import algo.tree.BinarySearchTree.BSTNode;

public class SpiralOrderTraversal<T extends Comparable<T>> extends LevelOrderTraversal<T> {

	@Override
	public void traverse(BSTNode<T> node) {
		Stack<BSTNode<T>> leftToRight = new Stack<>();
		Stack<BSTNode<T>> rightToLeft = new Stack<>();
		
		System.out.print(node.data() + ", ");
		
		if (node.right() != null)
			leftToRight.push(node.right());
		if (node.left() != null)
			leftToRight.push(node.left());
		
		while (true) {
			while (!leftToRight.isEmpty()) {
				BSTNode<T> temp = leftToRight.pop();
				System.out.print(temp.data() + ", ");
				
				if (temp.right() != null)
					rightToLeft.push(temp.left());
				if (temp.left() != null)
					rightToLeft.push(temp.right());
			}
			
			while (!rightToLeft.isEmpty()) {
				BSTNode<T> temp = rightToLeft.pop();
				System.out.print(temp.data() + ", ");
				
				if (temp.left() != null)
					leftToRight.push(temp.right());
				if (temp.right() != null)
					leftToRight.push(temp.left());
			}
			
			if (leftToRight.isEmpty() && rightToLeft.isEmpty()) { 
				break;
			}
		}
		
	}
}
