package algo.recursion;

public class RecursiveAlgorithms {

	public static int fibonacci(int n) {
		int[][] matrix = { { 1, 1 }, { 1, 0 } };
		matrix = power(matrix, n - 1);
		return matrix[0][0];
	}

	private static int[][] power(int[][] matrix, int n) {
		int[][] copy = { { 1, 1 }, { 1, 0 } };

		for (int i = 2; i <= n; i++) {
			multiply(matrix, copy);
		}
		return matrix;
	}

	private static void multiply(int[][] matrix, int[][] matrix2) {
		int temp00 = matrix[0][0] * matrix2[0][0] + matrix[0][1] * matrix2[1][0];
		int temp01 = matrix[0][0] * matrix2[0][1] + matrix[0][1] * matrix2[1][1];
		int temp10 = matrix[1][0] * matrix2[0][0] + matrix[1][1] * matrix2[1][0];
		int temp11 = matrix[1][0] * matrix2[0][1] + matrix[1][1] * matrix2[1][1];

		matrix[0][0] = temp00;
		matrix[0][1] = temp01;
		matrix[1][0] = temp10;
		matrix[1][1] = temp11;
	}

	/**
	 * Not working
	 * 
	 * @param set
	 */
	public static void printAllSubsets(char[] set) {
		allSubsets(set, "", 0, set.length);
	}

	private static void allSubsets(char[] set, String subset, int startIndex, int len) {
		if (len == 0) {
			System.out.println(subset);
			return;
		}

		for (int i = startIndex; i < len; i++) {
			System.out.println(set[i] + subset);
			allSubsets(set, set[i] + subset, i + 1, len - 1);
		}
	}

	public static void allBalancedParentheses(int n) {
		balancedParentheses(n, n, "");
	}

	private static void balancedParentheses(int openLeft, int closedLeft, String currentSequence) {
		if (openLeft < 0 || closedLeft < openLeft)
			return;

		if (openLeft == 0 && closedLeft == 0) {
			System.out.println(currentSequence);

		} else {
			if (openLeft > 0) {
				balancedParentheses(openLeft - 1, closedLeft, currentSequence + "(");
			}
			if (closedLeft > openLeft) {
				balancedParentheses(openLeft, closedLeft - 1, currentSequence + ")");
			}
		}
	}
	
	public static void main(String[] args) {
		allBalancedParentheses(3);
		allBalancedParentheses(5);
	}

}
