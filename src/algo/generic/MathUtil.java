package algo.generic;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MathUtil {

	public static int max(int a, int b) {
		return a > b ? a : b;
	}
	
	public static int min(int a, int b) {
		return a < b ? a : b;
	}
	
	public static int projectEuler02() {
		int maxFibonacci = 4000000;
		int a = 1;
		int b = 2;
		int c = a + b;
		int sumEven = 2;
		
		while (c < maxFibonacci) {
			if ((c & 1) == 0) {
				sumEven += c;
			}
			
			a = b;
			b = c;
			c = a + b;
		}
		return sumEven;
	}
	
	public static long projectEuler03(long num) {
		long result = 0;
		
		while (num % 2 == 0) {
			num /= 2;
		}
		
		// Now `num` is odd here, so divide from i = 3, till sqrt(num)
		// increment i by 2 every time.
		for (long i = 3; i * i <= num; i += 2) {
			while (num % i == 0) {
				num /= i;
				result = i;
			}
		}
		return result;
	}
	
	public static int projectEuler04() {
		int a = 999;
		int max = 0;
		
		while (a >= 100) {
			int b = a;
			
			// Once a * b is less than current max, it can never be greater. 
			// So, just break the loop. We already have our answer
			if (a*b < max) break;
			
			while (b >= 100) {
				int prod = a * b;
				if (!isPalindrome(prod)) {
					--b;
				} else {
					if (prod > max) max = prod;
					break;
				}
			}
			--a;
		}
		return max;
	}
	
	public static boolean isPalindrome(int num) {
		StringBuilder sb = new StringBuilder(String.valueOf(num));
		return sb.toString().equals(sb.reverse().toString());
	}
	
	public static long projectEuler06() {
		int n = 100;
		
		/*
		 *  Sum of squares till n = n * (n + 1) * (2n + 1) / 6
		 */
		int sumOfSquares = 100 * 101 * 201 / 6;  
		
		/*
		 *  Square of sum till n = (n * (n + 1) / 2) ^ 2
		 */
		long squareOfSum = power(100 * 101 / 2, 2);
		
		return squareOfSum - sumOfSquares;
	}
	
	/**
	 * Return 10001st prime
	 * @return
	 */
	public static int projectEuler07() {
		int numOfPrimes = 1;  // Pre-consider 2
		int num = 1;
		do {
			num += 2;
			if (isPrime(num)) numOfPrimes++;
		} while (numOfPrimes < 10001);
		
		return num;
	}
	
	public static boolean isPrime(int num) {
		for (int i = 2; i * i <= num; ++i) {
			if (num % i == 0) return false;
		}
		return true;
	}
	
	public static int projectEuler09() {
		
//		for (int a = 1; a <= 1000; ++a) {
//			for (int b = a; b <= 1000; ++b) {
//				int c = 1000 - a - b;
//				if (a*a + b*b == c*c) {
//					return a * b * c;
//				}
//			}
//		}
//		return 0;
		
		int s = 1000;
		for (int m = 2; m * m < s / 2; m++) {
			for (int n = 1; n < m; n++) {
				int a = m * m - n * n;
				int c = 2 * m * n;
				int b = m * m + n * n;
				
				if (s % (a + b + c) == 0) {
					int k = s / (a + b + c);
					System.out.println(a + " : " + b + " : " + c);
					return k * a * k * b * k * c;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Sum of all primes below 2 million
	 * @return
	 */
	public static long projectEuler10() {
		final int MAX = 2000000;
		boolean[] sieveOfErasthosthenes = new boolean[MAX];
		
		// Let every number is prime
		Arrays.fill(sieveOfErasthosthenes, true);
		// 0 and 1 is not prime
		sieveOfErasthosthenes[0] = false;
		sieveOfErasthosthenes[1] = false;
		
		sieveOfErasthosthenes[2] = true;
		for (int j = 4; j < MAX; j += 2) {
			sieveOfErasthosthenes[j] = false;
		}
		
		long sum = 0;
		
		for (int i = 3; i < MAX; i += 2) {
			if (sieveOfErasthosthenes[i]) {
				sum += i;
				for (int j = 2 * i; j < MAX; j += i) {
					sieveOfErasthosthenes[j] = false;
				}
			}
		}
	
		return sum;
	}
	
	public static String projectEuler13() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("projecteuler13.txt"));
		List<String> numbers = new ArrayList<String>(100);
		
		while (scanner.hasNextLine()) {
			numbers.add(scanner.nextLine());
		}
		
		BigInteger sum = BigInteger.ZERO;
		
		for (String str: numbers) {
			sum = sum.add(new BigInteger(str));
		}
		
		return sum.toString().substring(0, 10);
	}
	
	public static long projectEuler14() {
		Map<Long, Integer> chain = new HashMap<Long, Integer>();
		
		final int MAX = 1000000;
		int maxLength = 0;
		long numWithMaxLength = 0;
		
		for (long i = 1; i < MAX; ++i) {
			int length = 1;
			long num = i;
			
			do {
				num = nextNumber(num);
				if (num != 1 && chain.containsKey(num)) {
					length += chain.get(num);
					break;
				}
				length++;
				
			} while (num != 1);
			
			if (maxLength < length) {
				maxLength = length;
				numWithMaxLength = i;
			}
			chain.put(i, length);
		}
		return numWithMaxLength;
	}
	
	private static long nextNumber(long n) {
		if ((n & 1) == 0)
			return n >> 2;
		else
			return 3 * n + 1;
	}
	
	public static long projectEuler15() {
		final int MAX = 21;
		
		long[][] allPathLengths = new long[MAX][MAX];
		
		for (int i = 1; i < MAX; i++) {
			allPathLengths[i][0] = 1;
			allPathLengths[0][i] = 1;
		}
		
		for (int i = 1; i < MAX; ++i) {
			for (int j = 1; j < MAX; j++) {
				allPathLengths[i][j] = allPathLengths[i][j - 1] + allPathLengths[i - 1][j];
			}
		}
		
		return allPathLengths[MAX - 1][MAX - 1];
	}
	
	public static int projectEuler16() {
		BigInteger base = new BigInteger("2");
		BigInteger result = base.pow(1000);
		
		String str = result.toString();
		
		int sum = 0;
		for (char ch: str.toCharArray()) {
			sum += (ch - '0');
		}
		return sum;
//		int exponent = 1000;
//		byte[] arr = new byte[126];
//		arr[125] = (byte)1;
//		String str = new String(arr);
//		System.out.println(str);
		
	}
	
	public static int projectEuler17() {
		String[] unitNames = {
			"",
			"one",
			"two",
			"three",
			"four",
			"five",
			"six",
			"seven",
			"eight",
			"nine",
			"ten",
			"eleven",
			"twelve",
			"thirteen",
			"fourteen",
			"fifteen",
			"sixteen",
			"seventeen",
			"eighteen",
			"nineteen"
		};
		
		String[] tensNames = {
			"",
			"",
			"twenty",
			"thirty",
			"forty",
			"fifty",
			"sixty",
			"seventy",
			"eighty",
			"ninety"
		};
		
		int length = 0;
		
		for (int i = 1; i < 1000; ++i) {
			String word = "";
			
			if (i >= 100) {
				int hundredthDigit = i / 100;
				int tensPlace = (i % 100) / 10;
				int onesPlace = (i % 100) % 10;
				
				word = unitNames[hundredthDigit] + "hundred";
				
				if (tensPlace != 0 || onesPlace != 0) word += "and";
				
				if (tensPlace < 2) {
					word += unitNames[tensPlace * 10 + onesPlace];
				} else {
					word += tensNames[tensPlace] + unitNames[onesPlace];
				}
				
			} else {
				if (i < 20) {
					word = unitNames[i];
				} else {
					word = tensNames[i / 10] + unitNames[i % 10];
				}
			}

			length += word.length();
		}
		
		length += "onethousand".length();
		
		return length;
	}
	
	public static int projectEuler18() throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new File("projecteuler18.txt"));
		
		// dimension (15 row and 15 column)
		int MAX = 15;
		
		int[][] triangle = new int[MAX][MAX];
		
		// Populate the triangle stored as array of array
		int row = 0;
		while (row < MAX) {
			int col = 0;
			while (col <= row && scanner.hasNextInt()) {
				triangle[row][col++] = scanner.nextInt();
			}
			row++;
		}
		
		// Maximum on first column of every row is the value added to the maximum 
		// till the immediate cell above it. [row - 1][0[
		for (int i = 1; i < triangle.length; i++) {
			triangle[i][0] = triangle[i - 1][0] + triangle[i][0];
		}
		
		// Calculate maximum for columns greater than 0 for every row
		// There are only two ways to come to a particular index:
		// From top (triangle[i-1][j]) and top-left (triangle[i-1][j-1]).
		// Take the maximum of both these values, and add to triangle[i][j].
		for (int i = 1; i < triangle.length; i++) {
			for (int j = 1; j <= i; j++) {
				triangle[i][j] = max(triangle[i - 1][j], triangle[i - 1][j - 1]) + triangle[i][j];
			}
		}
		
		// Find out the maximum in the last row, to get the answer
		int max = triangle[MAX - 1][0];
		for (int i = 1; i < triangle[MAX - 1].length; i++) {
			if (triangle[MAX - 1][i] > max) max = triangle[MAX - 1][i];
		}
		
		return max;
	}
	
	public static int projectEuler20() {
		int n = 100;
		
		BigInteger fact = BigInteger.ONE;
		
		for (int i = 2; i <= n; ++i) {
			fact = fact.multiply(new BigInteger("" + i));
		}
		
		int digitSum = 0;
		while (fact.compareTo(BigInteger.ZERO) > 0) {
			digitSum += fact.mod(BigInteger.TEN).intValue();
			fact = fact.divide(BigInteger.TEN);
		}
		return digitSum;
	}
	
	public static long projectEuler22() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("projecteuler22.txt"));
		
		String str = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String[] arr = scanner.nextLine().split("@");
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		long totalScore = 0;
		
		for (int i = 0; i < arr.length; i++) {
			long score = 0;
			for (char ch: arr[i].toCharArray()) {
				score += str.indexOf(ch);
			}
			totalScore += score * (i + 1);
		}
		
		return totalScore;
	}
	
	public static void allDigitsArmstrong(int numberOfDigits) {
		long start = power(10, numberOfDigits - 1);
    	long end = power(10, numberOfDigits) - 1;
    	
    	int[] cachedPowers = new int[10];
    	
    	for (int i = 1; i < 10; ++i) {
    		cachedPowers[i] = (int)power(i, numberOfDigits);
    	}
    	
    	for (long i = start; i <= end; ++i) {
    		
    		long temp = i;
    		long cubeSum = 0;
    		
    		while (temp > 0) {
    			int digit = (int)(temp % 10);
    			
    			if (digit == 0 || digit == 1) {
    				cubeSum += digit;
    				temp /= 10;
    				continue;
    			}

    			cubeSum += cachedPowers[digit];
    			temp /= 10;
    		}
    		
    		if (cubeSum == i) {
    			System.out.println(cubeSum);
    		}
    	}
	}
    
	/**
	 * Uses exponentiation by squaring. 
	 * a^4 = a^2 * a^2
	 * a^5 = a * a^2 * a^2
	 * 
	 * @param base
	 * @param exp
	 * @return
	 */
	public static long power(long base, int exp) {
		if (base == 0) return 0;
		if (base == 1 || exp == 0) return 1;
		
		long result = 1;
		
		while (exp != 0) {
			if ((exp & 1) == 1)  
				result *= base;
			exp >>= 1;    
			base *= base;  
		}
		
		return result;
	}
}
