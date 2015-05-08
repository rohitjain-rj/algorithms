package com.list;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingTwoQueues<E> {

	private Queue<E> queue1 = new LinkedList<E>();
	private Queue<E> queue2 = new LinkedList<E>();
	
	public void push(E elem) {
		queue1.add(elem);
	}
	
	public E pop() {
		if (queue1.isEmpty()) {
			throw new StackEmptyException("No element to pop.");
		}
		
		while (queue1.size() > 1) {
			queue2.add(queue1.poll());
		}
		
		E elem = queue1.poll();
		
		Queue<E> temp = queue1;
		queue1 = queue2;
		queue2 = temp;
		
		return elem;
	}
}
