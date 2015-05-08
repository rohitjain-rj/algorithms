package algo.generic;

public class BitUtility {

	public static int totalSetBits(int num) {
		int count = 0;
		while (num > 0) {
			num &= (num - 1);
			count++;
		}
		return count;
	}
	
	public static int setBitLowToHighFromMToN(int n, int m, int low, int high) {
		int mask = 0;
		int totalBits = high - low + 1;
		
		// Create mask with lowest "totalBits" no of bits set - 00011111 for totalBits = 5
		mask = (-1) ^ ((-1) << totalBits);
		
		// Updating mask by shifting by "low" bits - 01111100 for low = 2 
		mask <<= low;
		
		// Complement mask - 10000011
		mask = ~mask;
		
		// Clearing bits from low to high in "n" with (n & mask) & then ORing with (m << low)
		return (n & mask) | (m << low);
	}
	
	public static int nextLargestWithSameNumberOfBits(int n) {
		int rightMostZeroLength = 0;
		int rightMostOneLength = 0;
		
		int bitExtracterMask = 1;
		
		// Get total rightmost 0 bits
		while ((n & bitExtracterMask) == 0) {
			rightMostZeroLength++;
			bitExtracterMask <<= 1;
		}
		
		// Get total rightmost 1 bits
		while ((n & bitExtracterMask) > 0) {
			rightMostOneLength++;
			bitExtracterMask <<= 1;
		}
		
		// Total number of bits to mask from original number
		int totalBitsToMask = rightMostOneLength + rightMostZeroLength;
		
		// Create mask to append to original number to create new number
		// Left shift the left most 1 bit in original number by 1
		int mask = 1 << totalBitsToMask;
		
		// Right shift the remaining 1 bits to the end
		mask |= 1 << (rightMostOneLength - 1) - 1;
		
		// Mask totalBitsToMask from n. Set the bits to 0
		n = (n >> totalBitsToMask) << totalBitsToMask;
		
		// Append new mask to n
		n |= mask;

		return n;
	}
}
