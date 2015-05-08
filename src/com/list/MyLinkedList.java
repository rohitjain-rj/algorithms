package com.list;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T> {

	private LinkedListNode<T> head;
	
	public static class LinkedListNode<E extends Comparable<E>> {
		E data;
		LinkedListNode<E> next;
		LinkedListNode<E> random;
		
		public LinkedListNode(E data) {
			this.data = data;
			this.next = null;
			this.random = null;
		}
		
		public E getData() {
			return data;
		}
		
		public void setRandom(LinkedListNode<E> random) {
			this.random = random;
		}
		
		public void setNext(LinkedListNode<E> next) {
			this.next = next;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof LinkedListNode)) {
				return false;
			}
			
			@SuppressWarnings("unchecked")
			LinkedListNode<E> that = (LinkedListNode<E>) obj;
			if (this.data.compareTo(that.data) == 0) {
				return true;
			}
			
			return false;
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
	
	public LinkedListNode<T> insert(T data) {
		LinkedListNode<T> node = new LinkedListNode<T>(data);

		if (head == null) {
			head = node;
			return node;
		}
		
		// Traverse till the last node
		LinkedListNode<T> prev = head;
		while (prev.next != null) {
			prev = prev.next;
		}
		
		// Add new node as next node of the last node
		prev.next = node;
		return node;
	}
	
	public void removeDuplicates() {
		LinkedListNode<T> temp = head;
		Set<LinkedListNode<T>> seen = new HashSet<LinkedListNode<T>>();
		seen.add(temp);
		
		while (temp != null) {
			if (seen.contains(temp.next)) {
				temp.next = temp.next.next;
			} else {
				temp = temp.next;
				seen.add(temp);
			}
		}
	}
	
	public T getNthToLastElement(int n) {
		LinkedListNode<T> forward = head;
		LinkedListNode<T> nBackward = head;
		
		while (n-- > 0) {
			if (forward == null) {
				throw new IllegalArgumentException("Not enough element");
			}
			forward = forward.next;
		}
		
		while (forward != null) {
			forward = forward.next;
			nBackward = nBackward.next;
		}
		
		return nBackward.data;
	}
	
	/**
	 *  Will work only for MyLinkedList<Integer>
	 */
//	public MyLinkedList<T> add(MyLinkedList<T> addend) {
//		if (this.head == null) return addend;
//		if (addend.head == null) return this;
//		
//		if (!(this.head.data instanceof Integer && addend.head.data instanceof Integer)) {
//			throw new IllegalArgumentException("Only LinkedList of Integers can be added.");
//		}
//		
//		MyLinkedList<T> result = new MyLinkedList<>();
//		Node<T> node1 = this.head;
//		Node<T> node2 = addend.head;
//		int carry = 0;
//		
//		while (node1 != null && node2 != null) {
//			int val1 = (Integer) node1.data;
//			int val2 = (Integer) node2.data;
//			
//			int sum = val1 + val2 + carry;
//			if (sum > 9) {
//				carry = 1;
//				sum = 10 - sum;
//			}
//			result.insert(sum);  // ISSUE HERE
//		}
//	}
	
	/**
	 * Fails, if two nodes are equal and have equal hashCode.
	 * @return
	 */
	public MyLinkedList<T> copy() {
		MyLinkedList<T> list = new MyLinkedList<T>();
		Map<LinkedListNode<T>, LinkedListNode<T>> oldToNew = new HashMap<>();
		LinkedListNode<T> node = head;
		
		while (node != null) {
			LinkedListNode<T> newNode = list.insert(node.data);
			oldToNew.put(newNode, node);
			node = node.next;
		}
		
		for (Entry<LinkedListNode<T>, LinkedListNode<T>> entry: oldToNew.entrySet()) {
			entry.getKey().setRandom(entry.getValue().random);
		}
		
		return list;
	}
	
	
	public boolean isPalindrome() {
		Stack<LinkedListNode<T>> stack = new Stack<>();
		
		LinkedListNode<T> slow = head;
		LinkedListNode<T> fast = head;
		boolean moveSlow = true;
		
		while (fast != null) {
			if (moveSlow) {
				stack.push(slow);
				
				slow = slow.next;
				fast = fast.next;
				moveSlow = !moveSlow;
				
			} else {
				fast = fast.next;
				moveSlow = !moveSlow;
			}
		}

		if (stack.size() % 2 == 0) {
			stack.pop();
		}
		
		while (!stack.isEmpty() && slow != null && stack.peek().equals(slow)) {
			stack.pop();
			slow = slow.next;
		}
		
		return stack.isEmpty();
	}

	/**
	 * In-place reversal of this linked-list
	 */
	public void reverse() {
//		head = reverseRecursive(head, null);
		head = reverseIterative(head, null);
	}
	
	@SuppressWarnings("unused")
	private LinkedListNode<T> reverseRecursive(LinkedListNode<T> head, LinkedListNode<T> prev) {
		if (head.next == null) {
			return head;
		}
		LinkedListNode<T> temp = head.next;
		head.next = prev;
		return reverseRecursive(temp, head);
	}
	
	private LinkedListNode<T> reverseIterative(LinkedListNode<T> head, LinkedListNode<T> prev) {
		while (head != null) {
			LinkedListNode<T> temp = head.next;
			head.next = prev;
			prev = head;
			head = temp;
		}
		return prev;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		LinkedListNode<T> node = head;
		
		// Iterate till 2nd last node
		while (node != null) {
			T nextData = node.next == null ? null : node.next.data;
			
			builder.append(node.data).append(" => [")
				   .append(nextData).append(", ")
				   .append(node.random.data).append("]")
				   .append("\n");
			node = node.next;
		}

		return builder.toString();
	}
}
