package com.list;

import java.util.Arrays;

public class StackUsingArray {

	private final int CAPACITY = 10;
	private int size;
	private int min = Integer.MAX_VALUE;
	private int secondMin = Integer.MAX_VALUE;
	
	private int[] arr;
	
	public StackUsingArray() {
		arr = new int[CAPACITY];
	}
	
	public StackUsingArray(int capacity) {
		arr = new int[capacity];
	}
	
	public void push(int elem) {
		ensureCapacity(size);
		arr[size] = elem;
		size++;
		
		if (elem < min) {
			secondMin = min;
			min = elem;
		} else if (elem < secondMin) {
			secondMin = elem;
		}
	}
	
	/**
	 * Problem when elements are popped
	 * @return
	 */
	public int pop() {
		if (size == 0) {
			throw new StackEmptyException("No element to pop.");
		}
		
		size--;
		int elem = arr[size];
		
		if (elem == min) {
			min = secondMin;
		} else if (elem == secondMin) {
			secondMin = Integer.MAX_VALUE;
		}
		return elem;
	}
	
	public int min() {
		return min;
	}
	
	public void ensureCapacity(int size) {
		if (size == arr.length) {
			int newCapacity = arr.length * 2;
			arr = Arrays.copyOf(arr, newCapacity);
		}
	}
	
}
