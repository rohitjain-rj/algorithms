package com.list;

import com.list.MyLinkedList.LinkedListNode;

public interface MyList<T extends Comparable<T>> {
	public boolean isPalindrome();
	public void reverse();
	
}
