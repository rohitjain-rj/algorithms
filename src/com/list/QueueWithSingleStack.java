package com.list;

import java.util.Stack;

public class QueueWithSingleStack<E> {

	private Stack<E> stack = new Stack<E>();
	
	public void enqueue(E elem) {
		if (!stack.isEmpty()) {
			E topElem = stack.pop();
			enqueue(elem);
			stack.push(topElem);
		} else {
			stack.push(elem);
		}
	}
	
	public E dequeue() {
		if (stack.isEmpty()) {
			throw new QueueEmptyException("No element to dequeue.");
		}
		return stack.pop();
	}
	
	@Override
	public String toString() {
		return stack.toString();
	}
}
