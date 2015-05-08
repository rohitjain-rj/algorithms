package algo.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import algo.tree.traversal.BinaryTreeTraversal;
import algo.tree.traversal.ColumnWiseTraversal;
import algo.tree.traversal.InOrderTraversal;
import algo.tree.traversal.LevelOrderTraversal;
import algo.tree.traversal.PostOrderTraversal;
import algo.tree.traversal.PreOrderTraversal;
import algo.tree.traversal.SpiralOrderTraversal;

public class BinarySearchTree<T extends Comparable<T>> {
	private BSTNode<T> root;
	
	public static class BSTNode<E extends Comparable<E>> {
		E data;
		BSTNode<E> left;
		BSTNode<E> right;
		
		BSTNode(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
		
		public E data() {
			return data;
		}
		
		public BSTNode<E> left() {
			return left;
		}
		
		public BSTNode<E> right() {
			return right;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof BSTNode)) return false;
			
			@SuppressWarnings("unchecked")
			boolean result = data.compareTo(((BSTNode<E>)obj).data) == 0;
			return result;
		}
		
		@Override
		public int hashCode() {
			return data.hashCode();
		}
		
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	public T getRoot() {
		return root.data;
	}
	
	public int getHeight() {
		return height(root);
	}
	
	private int height(BSTNode<T> node) {
		if (node == null) {
			return 0;
		}
		
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		
		return leftHeight > rightHeight ? (leftHeight + 1) : (rightHeight + 1);
	}
	
	public void insert(T data) {
		if (root == null) {
			root = new BSTNode<>(data);
			return;
		}
		
		insert(root, data);
	}
	
	private void insert(BSTNode<T> node, T data) {
		
		if (data.compareTo(node.data) < 0) {
			if (node.left == null) {
				node.left = new BSTNode<>(data);
				return;
			}
			
			insert(node.left, data);
			
		} else if (data.compareTo(node.data) > 0) {
			if (node.right == null) {
				node.right = new BSTNode<>(data);
				return;
			}
			
			insert(node.right, data);
		} 
	}
	
	public void preOrder() {
		BinaryTreeTraversal<T> preorder = new PreOrderTraversal<>();
		preorder.traverse(root);
	}
	
	public void postOrder() {
		BinaryTreeTraversal<T> postOrder = new PostOrderTraversal<>();
		postOrder.traverse(root);
	}
	
	public void inOrder() {
		BinaryTreeTraversal<T> inOrder = new InOrderTraversal<>();
		inOrder.traverse(root);
	}
	
	public void levelOrder() {
		BinaryTreeTraversal<T> levelOrder = new LevelOrderTraversal<>();
		levelOrder.traverse(root);
	}
	
	public void spiralOrder() {
		BinaryTreeTraversal<T> spiralOrder = new SpiralOrderTraversal<>();
		spiralOrder.traverse(root);
	}
	
	public void columnWiseTraversal() {
		BinaryTreeTraversal<T> columnWiseTraversal = new ColumnWiseTraversal<>();
		columnWiseTraversal.traverse(root);
	}
	
	public BSTNode<T> lowestCommonAncestor(T data1, T data2) {
		if (root == null) return null;
		
		BSTNode<T> node1 = search(root, data1);
		BSTNode<T> node2 = search(root, data2);
		
		if (node1 == null || node2 == null) return null;
		
		return lowestCommonAncestor(root, node1, node2);
	}
	
	private BSTNode<T> lowestCommonAncestor(BSTNode<T> root, BSTNode<T> node1, BSTNode<T> node2) {
		if (root == null) return null;
		
		T max = node1.data.compareTo(node2.data) > 0 ? node1.data : node2.data;
		T min = node1.data.compareTo(node2.data) < 0 ? node1.data : node2.data;
		
		if (max.compareTo(root.data) < 0) {
			// the nodes are on left branch
			return lowestCommonAncestor(root.left, node1, node2);
			
		} else if (min.compareTo(root.data) > 0) {
			// the nodes are on right branch
			return lowestCommonAncestor(root.right, node1, node2);
			
		} else {
			// the nodes are on separate branches
			return root;
		}
	}
	
	public List<BSTNode<T>> shortestPath(T data1, T data2) {
		if (root == null) return null;
		
		BSTNode<T> node1 = search(root, data1);
		BSTNode<T> node2 = search(root, data2);
		
		if (node1 == null || node2 == null) return null;
		
		return shortestPath(root, node1, node2);
	}
	
	private List<BSTNode<T>> shortestPath(BSTNode<T> root, BSTNode<T> node1, BSTNode<T> node2) {
		
		Stack<BSTNode<T>> path1 = new Stack<>();
		Stack<BSTNode<T>> path2 = new Stack<>();
		
		BSTNode<T> lca = lowestCommonAncestor(root, node1, node2);
		
		if (min(node1, node2).data.compareTo(lca.data) < 0) {
			shortestPathFromRoot(lca.left, min(node1, node2), path1);
		}
		
		if (max(node1, node2).data.compareTo(lca.data) > 0) {
			shortestPathFromRoot(lca.right, max(node1, node2), path2);
		}
		
		path1.push(lca);
		while (!path2.empty()) {
			path1.push(path2.pop());
		}
		
		return path1;
	}
	
	private void shortestPathFromRoot(BSTNode<T> root, BSTNode<T> node, Stack<BSTNode<T>> path) {
		if (root == null) return;
		
		if (root.equals(node)) {
			path.push(root);
			return;
		}
		
		if (node.data.compareTo(root.data) < 0) {
			shortestPathFromRoot(root.left, node, path);
		} else {
			shortestPathFromRoot(root.right, node, path);
		}
		
		path.push(root);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T data) {
		if (node == null) return null;
		
		if (node.data.compareTo(data) == 0) {
			return node;
		}
		
		if (data.compareTo(node.data) < 0) {
			return search(node.left, data);
		} else {
			return search(node.right, data);
		}
	}
	
	public T successorInorder(T data) {
		BSTNode<T> node = search(root, data);
		
		if (node == null) return null;
		
		if (node.right != null) {
			return minimum(node.right).data;
		}
		
		BSTNode<T> probe = root;
		BSTNode<T> successor = null;
		
		while (probe != null) {
			if (node.data.compareTo(probe.data) < 0) {
				successor = probe;
				probe = probe.left;
				
			} else if (node.data.compareTo(probe.data) > 0) {
				probe = probe.right;
				
			} else {
				break;
			}
		}
		
		return successor.data;
	}
	
	public BSTNode<T> minimum() {
		if (root == null) return null;
		return minimum(root);
	}
	
	private BSTNode<T> minimum(BSTNode<T> root) {
		BSTNode<T> node = root;
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public BSTNode<T> maximum() {
		if (root == null) return null;
		return maximum(root);	
	}
	
	private BSTNode<T> maximum(BSTNode<T> root) {
		BSTNode<T> node = root;
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	private BSTNode<T> min(BSTNode<T> a, BSTNode<T> b) {
		return a.data.compareTo(b.data) < 0 ? a : b;
	}
	
	private BSTNode<T> max(BSTNode<T> a, BSTNode<T> b) {
		return a.data.compareTo(b.data) > 0 ? a : b;
	}
	
	
	public void transformToRight() {
		BSTNode<T> node = transformToRight(root);
		
		while (node != null) {
			System.out.println(node.data + ", " );
			node = node.right;
		}
	}
	
	public BSTNode<T> transformToRight(BSTNode<T> node) {
		if (node == null) {
			return null;
		}
		
		if(node.left == null) {
			return node;
		}
		
		BSTNode<T> temp = transformToRight(node.left);
		BSTNode<T> temp2 = transformToRight(node.right);
		
		node.right = temp;
		while (temp.right != null)
			temp = temp.right;
		temp.right = temp2;
		return node;
	}
	
	public int maxDepth(BSTNode<T> root){
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}
			
	public int minDepth(BSTNode<T> root){
		if (root == null) {
			return 0;
		}
		return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	}
	
	public boolean isBalanced() {
		System.out.println("Height of root: " + height(root));
		return isBalanced(root);
	}
	
	private boolean isBalanced(BSTNode<T> node) {
//		return (maxDepth(root) - minDepth(root) <= 1);
		
		if (node == null || (node.left == null && node.right == null)) return true;
		
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		
		
		if (node.left == null) return rightHeight < 2; 
		if (node.right == null) return leftHeight < 2;
		
		int leftRightHeightdDiff = Math.abs(leftHeight - rightHeight);
		
		return leftRightHeightdDiff < 2 && isBalanced(node.left) && isBalanced(node.right);
	}
	
	public static <T extends Comparable<T>> BinarySearchTree<T> createMinimalBST(T[] array) {
		return minimalBST(array, 0, array.length - 1);
	}
	
	private static <T extends Comparable<T>> BinarySearchTree<T> minimalBST(T[] array, int low, int high) {
		BinarySearchTree<T> bst = new BinarySearchTree<T>();
		
		if (low > high) return bst;
		
		int mid = low + (high - low) / 2;
		
		bst.insert(array[mid]);
		bst.root.left = minimalBST(array, low, mid - 1).root;
		bst.root.right = minimalBST(array, mid + 1, high).root;
		
		return bst;
	}
	
	public Map<Integer, LinkedList<BSTNode<T>>> levelWiseLinkedList() {
		return levelWiseLinkedList(root);
	}
	
	private Map<Integer, LinkedList<BSTNode<T>>> levelWiseLinkedList(BSTNode<T> root) {
		Map<Integer, LinkedList<BSTNode<T>>> map = new HashMap<Integer, LinkedList<BSTNode<T>>>();
		
		LinkedList<BSTNode<T>> list = new LinkedList<BSTNode<T>>();
		list.add(root);
		
		int level = 0;
		
		map.put(level, list);
		
		while (true) {
			LinkedList<BSTNode<T>> nextLevellist = new LinkedList<BSTNode<T>>();
			LinkedList<BSTNode<T>> currentLevelList = map.get(level);
			
			for (BSTNode<T> node: currentLevelList) {
				if (node.left != null) {
					nextLevellist.add(node.left);
				}
				if (node.right != null) {
					nextLevellist.add(node.right);
				}
			}
			
			if (nextLevellist.isEmpty()) {
				break;
			}
			map.put(++level, nextLevellist);
		}
		
		return map;
	}
}
