package algo.dynamicprogramming;

import java.util.LinkedList;

public class EditDistance {

	/**
	 * Least number of edits required in X to make it Y. Edits that are allowed:
	 * 		insert a character - cost 1
	 * 		delete a character - cost 1
	 * 		replace a character - cost 1 if old != new, else 0
	 * 
	 * base conditions:
	 * 		editDistanceDP("", "") == 0
	 * 		editDistanceDP(s, "") == editDistanceDP("", s) == s.length()
	 * @param X
	 * @param Y
	 * @return least number of edits to change X into Y
	 */
	public static int editDistanceDP(String X, String Y) {
		int lenX = X.length();
		int lenY = Y.length();

		int[][] cost = new int[lenX + 1][lenY + 1];
		
		for (int i = 0; i <= lenX; ++i) cost[i][0] = i;
		for (int j = 0; j <= lenY; ++j) cost[0][j] = j;
		
		for (int i = 1; i <= lenX; ++i) {
			for (int j = 1; j <= lenY; ++j) {
				int insert = cost[i][j - 1] + 1;
				int delete = cost[i - 1][j] + 1;
				int replace = cost[i - 1][j - 1] + (X.charAt(i - 1) != Y.charAt(j - 1) ? 1 : 0);
				
				cost[i][j] = minimum(insert, delete, replace);
			}
		}
		return cost[lenX][lenY];
	}
	
	public static int longestCommonSubsequence(String X, String Y) {
		int lenX = X.length();
		int lenY = Y.length();
		
		int[][] lcs = new int[lenX + 1][lenY + 1];
		
		for (int i = 0; i <= lenX; ++i) {
			lcs[i][0] = 0;
		}
		
		for (int j = 0; j <= lenY; ++j) {
			lcs[0][j] = 0;
		}
		
		for (int i = 1; i <= lenX; ++i) {
			for (int j = 1; j <= lenY; ++j) {
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = maximum(lcs[i - 1][j], lcs[i][j - 1]); 
				}
			}
		}
		
		return lcs[lenX][lenY];
	}
	
	public static int knapsack(int[] wt, int[] val, final int W, int n) {
		
		int[][] knapsack = new int[n + 1][W + 1];
		
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= W; w++) {
				
				if (i == 0 || w == 0) {
					knapsack[i][w] = 0;
					
				} else if (wt[i - 1] > w) {
					// Weight of current item > current capacity. Ignore it
					knapsack[i][w] = knapsack[i - 1][w];
					
				} else {
					// First include this item, second exclude this item. 
					// Find max for both the cases
					knapsack[i][w] = maximum(knapsack[i - 1][w - wt[i - 1]] + val[i - 1],  // Include item
							                 knapsack[i - 1][w]);    // Exclude item
				}
			}
		}
		
		return knapsack[n][W];
	}
	
	private static int minimum(int x, int y, int z) {
		return minimum(x, minimum(y, z));
	}
	
	private static int minimum(int x, int y) {
		return x < y ? x : y;
	}
	
	private static int maximum(int x, int y) {
		return x > y ? x : y;
	}
}
