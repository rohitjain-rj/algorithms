package algo.tree.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

import algo.tree.BinarySearchTree.BSTNode;

public class ColumnWiseTraversal<T extends Comparable<T>> extends BinaryTreeTraversal<T> {

	@Override
	public void traverse(BSTNode<T> node) {
		
		Queue<BSTNode<T>> queue = new LinkedList<>();
		
		Map<Integer, List<BSTNode<T>>> columnMap = new TreeMap<>();
		Map<BSTNode<T>, Integer> map = new HashMap<>();
		
		queue.add(node);
		
		while (!queue.isEmpty()) {
			
			BSTNode<T> temp = queue.poll();
			
			if (map.isEmpty()) {
				map.put(temp, 0);
				columnMap.put(0, new ArrayList<BSTNode<T>>(Arrays.asList(temp)));
			}
			
			int column = map.get(temp);
			
			if (temp.left() != null) {
				queue.add(temp.left());
				
				if (columnMap.containsKey(column - 1)) {
					columnMap.get(column - 1).add(temp.left());
				} else {
					columnMap.put(column - 1, new ArrayList<BSTNode<T>>(Arrays.asList(temp.left())));
				}
				
				map.put(temp.left(), column - 1);
			}
			
			if (temp.right() != null) {
				queue.add(temp.right());
				
				if (columnMap.containsKey(column + 1)) {
					columnMap.get(column + 1).add(temp.right());
				} else {
					columnMap.put(column + 1, new ArrayList<BSTNode<T>>(Arrays.asList(temp.right())));
				}
				
				map.put(temp.right(), column + 1);
			}
		}
		
		for (Entry<Integer, List<BSTNode<T>>> entry: columnMap.entrySet()) {
			System.out.println("Column - " + entry.getKey() + " : " + entry.getValue());
		}
	}
	
	@Override
	protected void after(BSTNode<T> node) {	
	}
	
	@Override
	protected void before(BSTNode<T> node) {	
	}
	
	@Override
	protected void middle(BSTNode<T> node) {
	}
	
}
