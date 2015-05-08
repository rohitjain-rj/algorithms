package algo.recursion;

public class Main {

	public static void main(String[] args) {
		for (byte b = Byte.MIN_VALUE;
	             b < Byte.MAX_VALUE; b++) {
	            if (b == 0x90)
	                System.out.print("Joy!");
	        }
		
		int fourthFibonacci = RecursiveAlgorithms.fibonacci(7);
		System.out.println(fourthFibonacci);
		
		RecursiveAlgorithms.printAllSubsets(new char[] {'a', 'b', 'c'});
		RecursiveAlgorithms.allBalancedParentheses(3);
	}
}
