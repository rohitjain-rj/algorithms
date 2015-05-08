package com.list;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingSingleQueue<E> {

	private int size = 0;
	private Queue<E> queue = new LinkedList<E>();
	
	public void push(E elem) {
		int i = size;
		++i;
		queue.add(elem);
		size = i;
	}
	
	public E pop() {
		int i = size;
		if (i == 0) {
			throw new StackEmptyException("No more element to pop.");
		}
		
		for (int j = i - 1; j > 0; --j) {
			queue.add(queue.poll());
		}
		
		E elem = queue.poll();
		size = --i;
		
		return elem;
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}
}
