package com.list;

public class QueueEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueEmptyException() {
	}
	
	public QueueEmptyException(String message) {
		super(message);
	}
}
