package com.list;

import java.util.Stack;

public class QueueWithTwoStacks<E> {

	private Stack<E> receiverStack = new Stack<E>();
	private Stack<E> providerStack = new Stack<E>();
	
	public void enqueue(E item) {
		receiverStack.push(item);
	}
	
	public E dequeue() {
		if (providerStack.isEmpty()) {
			if (receiverStack.isEmpty()) {
				throw new QueueEmptyException("No element to dequeue.");
			}
			while (!receiverStack.isEmpty()) {
				providerStack.push(receiverStack.pop());
			}
		}
		return providerStack.pop();
	}
	
	@Override
	public String toString() {
		return providerStack.toString() + " <- " + receiverStack.toString();
	}
}
