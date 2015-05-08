package algo.generic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class ArrayAlgo {

	public static int maxProduct3(int[] arr) {
		int maxProduct = Integer.MIN_VALUE;
		
		if (arr.length == 3) {
			return arr[0] * arr[1] * arr[2];
		}
		
		int totalPositive = 0;
		int totalNegative = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) totalPositive++;
			else if (arr[i] < 0) totalNegative++;
		}
		
		int max = Integer.MIN_VALUE;
		int sMax = Integer.MIN_VALUE;
		int tMax = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int sMin = Integer.MAX_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				tMax = sMax; sMax = max; max = arr[i];
			} else if (arr[i] > sMax) {
				tMax = sMax; sMax = arr[i];
			} else if (arr[i] > tMax) {
				tMax = arr[i];
			}
			
			if (arr[i] < min) {
				sMin = min; min = arr[i];
			} else if (arr[i] < sMin) {
				sMin = arr[i];
			} 
		}
		
		if (totalPositive >= 3 || totalPositive == 0) {
			maxProduct = max * sMax * tMax;
		} 
		
		if (totalPositive >= 1 && totalNegative >= 2) {
			int prod = min * sMin * max;
			maxProduct = MathUtil.max(maxProduct, prod);
		}
		
		return maxProduct;
	}
	
	/**
	 * Return maximum product of count number of elements from arr.
	 * @param arr
	 * @param count
	 * @return
	 */
	public static int maxProduct(int[] arr, int count) {
		return maxProduct(arr, 0, arr.length - 1, count);
	}
	
	private static int maxProduct(int[] arr, int fromIndex, int toIndex, int count) {
		
		if (count == 1) {
			return maximum(arr, fromIndex, toIndex);
		} else if (toIndex - fromIndex + 1 < count) {
			return 1;
		} else {
			return MathUtil.max(maxProduct(arr, fromIndex, toIndex - 1, count - 1) * arr[toIndex - 1], 
								maxProduct(arr, fromIndex, toIndex - 1, count));
		}
	}
	
	/**
	 * Given nXn matrix with 0s and 1s. For each row, all 1s come before all 0s.
	 * Return the row with maximum number of zeros.
	 * @param mat
	 * @return
	 */
	public static int rowWithMaxZero(int[][] mat) {
		int len = mat.length;
		int j = len - 1;
		int result = -1;
		
		for (int i = 0; i < len; ++i) {
			while (mat[i][j] == 0) {
				j--;
				result = i;
			}
		}
		return result;
	}
	
	/**
	 * Given a sorted array containing only zeros and ones, find index of first 1.
	 * @param arr
	 * @return
	 */
	public static int indexOfFirstOne(int[] arr) {
		for (int i = 0; i < arr.length; ++i) {
			if (arr[i] != 0 && arr[i] != 1) {
				throw new IllegalArgumentException("Pass sorted array with 0s and 1s only.");
			}
		}
		
		return binarySearchModified(arr, 0, arr.length - 1);
	}
	
	/**
	 * If element at mid is 0, then first 1 is on right subarray. 
	 * Else if element at (mid - 1) is 1, then first 1 is on the left subarray.
	 * Else (mid element is 1, and (mid - 1) is 0, then element at mid is first 1.
	 *
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	private static int binarySearchModified(int[] arr, int low, int high) {
		if (low > high) return -1;
		
		int mid = low + (high - low) / 2;
		
		if (arr[mid] == 0)
			return binarySearchModified(arr, mid + 1, high);
		else if (mid > 0 && arr[mid - 1] == 1)
			return binarySearchModified(arr, low, mid - 1);
		else
			return mid;
	}
	
	public static int equalLeftRightSumIndex(int[] arr) {
		if (arr.length == 0 || arr.length == 2) return -1;
		if (arr.length == 1) return 0;
		
		int sumLeft = arr[0];
		int sumRight = arr[arr.length - 1];
		
		int i = 1;
		int j = arr.length - 2;
		
		while (j - i > 0) {
		
			if (sumLeft < sumRight) { 
				sumLeft += arr[i];
				i++;
				
		    } else if (sumLeft > sumRight) {
				sumRight += arr[j];
		    	j--;
				
			} else {
				sumLeft += arr[i];
				sumRight += arr[j];
				i++;
				j--;
			}
		}
		
		if (sumLeft == sumRight && i == j) {
			return i;
		} else {
			return -1;
		}
	}
	
	/**
	 * You have an array of integers, such that each integer is present an odd number of time, 
	 * except 3 of them. Find the three numbers.
	 * 
	 * @param arr
	 * @return
	 */
	public static void findEvenOccurringNum(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int num: arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) ^ 1);
			} else {
				map.put(num, 1);
			}
		}
		
		for (Entry<Integer, Integer> entry: map.entrySet()) {
			if (entry.getValue() == 0) {
				System.out.println(entry.getKey());
			}
		}
	}
	
	/**
	 * Not working correctly.
	 * @param mat
	 */
	public static void rotateMatrix90Deg(int[][] mat) {
		int MAX = mat.length;
		
		for (int i = 0; i <= MAX / 2; ++i) {
			for (int j = 0; j <= MAX / 2; ++j) {
				int temp = mat[i][j];
				mat[i][j] = mat[MAX - j - 1][i];
				mat[MAX - j - 1][i] = mat[MAX - j - 1][MAX - i - 1];
				mat[MAX - j - 1][MAX - i - 1] = mat[j][MAX - i - 1];
				mat[j][MAX - i - 1] = temp;
			}
		}
	}
	
	public static int minDiff(int[] A, int B[])
	{
		int i, j, diff, prevDiff, mindiff = Integer.MAX_VALUE;
	    quickSort(A);
		quickSort(B);

		for(i=0, j=0; i<A.length; i++)
		{
			prevDiff = Integer.MAX_VALUE;
			
			while(j<B.length && (diff = Math.abs(A[i]-B[j])) <= prevDiff)
			{			
	                  prevDiff = diff;
	                  j++;
			}
			
			if(prevDiff <= mindiff)
				mindiff = prevDiff;
		}

		return mindiff;
	}
	
	
	public static int[] minimumSubArrayToSort(int[] arr) {
    	
    	int start = -1;
    	int end = -1;
    	
    	for (int i = 0; i < arr.length - 1; ++i) {
    		if (arr[i] > arr[i + 1]) {
    			start = i;
    			break;
    		}
    	}
    	
    	for (int i = arr.length - 1; i > 0; --i) {
    		if (arr[i] < arr[i - 1]) {
    			end = i;
    			break;
    		}
    	}
    	
    	int min = arr[start];
    	int max = arr[start];
    	
    	for (int i = start + 1; i <= end; ++i) {
    		if (arr[i] > max) max = arr[i];
    		if (arr[i] < min) min = arr[i];
    	}
    	
    	for (int i = 0; i < start; ++i) {
    		if (arr[i] > min) {
    			start = i;
    			break;
    		}
    	}
    	
    	for (int i = arr.length - 1; i > end; --i) {
    		if (arr[i] < max) {
    			end = i;
    			break;
    		}
    	}
    	
    	System.out.println("Subarray to sort : [" + start + ", " + end + "]");
    	return new int[] {start, end};
    }

	public static int kthSmallest(int[] arr, int k) {
    	return orderStatistic(arr, k, 0, arr.length - 1);
    }
    
    private static int orderStatistic(int[] arr, int k, int left, int right) {
    	int pivotPosition = randomizedPartition(arr, left, right);
    	
    	if (pivotPosition == k - 1) {
    		return arr[k - 1];
    	}
    	
    	if (k - 1 < pivotPosition) {
    		return orderStatistic(arr, k, left, pivotPosition - 1);
    	} else {
    		return orderStatistic(arr, k, pivotPosition + 1, right);
    	}
    }
    
    private static int partition(int[] arr, int left, int right) {
    	int pivot = arr[right];
    	
    	while (true) {
    		while (arr[left] < pivot) left++;	
    		while (arr[right] > pivot) right--;
    		
    		if (left < right) {
    			swap(arr, left, right);
    		} else {
    			return right;
    		}
    	}
    }
    
    private static int randomizedPartition(int[] arr, int left, int right) {
    	Random rand = new Random();
    	int randomIndex = rand.nextInt((right - left) + 1) + left;
    	
    	swap(arr, randomIndex, right);
    	return partition(arr, left, right);
    }
    
    public static void quickSort(int[] arr) {
    	quickSort(arr, 0, arr.length - 1);
    }
    
    private static void quickSort(int[] arr, int left, int right) {
    	if (left < right) {
    		int partition = randomizedPartition(arr, left, right);
    		quickSort(arr, left, partition);
    		quickSort(arr, partition + 1, right);
    	}
    }
    
    private static void swap(int[] arr, int first, int second) {
    	int temp = arr[first];
    	arr[first] = arr[second];
    	arr[second] = temp;
    }
    
    private static int maximum(int[] arr, int fromIndex, int toIndex) {
    	int max = arr[fromIndex];
    	for (int i = 1; i <= toIndex; ++i) {
    		if (arr[i] > max) {
    			max = arr[i];
    		}
    	}
    	return max;
    }
   
    public static void printMatrix(int[][] mat) {
    	for (int i = 0; i < mat.length; ++i) {
    		for (int j = 0; j < mat.length; ++j) {
    			System.out.print(mat[i][j] + "  ");
    		}
    		System.out.println();
    	}
    }
}
