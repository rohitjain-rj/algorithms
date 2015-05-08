package algo.generic;

import java.util.HashMap;
import java.util.Map;

public class StringAlgo {

	public static void permutation(String str) {
		System.out.println("With Map");
		permutate("", str);
		System.out.println();
		System.out.println("Without Map");
    	permutation(str, "");
    }
	
	/**
	 * Handles duplicate characters
	 * @param head
	 * @param tail
	 */
	private static void permutate(String head, String tail) {
        if (tail.isEmpty()) {
            System.out.println(head);
        } else {
            Map<Character, Boolean> seen = new HashMap<Character, Boolean>();
            for (int i = 0; i < tail.length(); i++) {
                if (!seen.containsKey(tail.charAt(i))) {
                    seen.put(tail.charAt(i), true);
                    permutate(head + tail.charAt(i), tail.substring(0, i) + tail.substring(i + 1, tail.length()));
                }
            }
        }
    }
    /**
     * Fails for duplicate characters
     * @param input
     * @param sofar
     */
    private static void permutation(String input, String sofar) {
    	if (input.equals("")) System.out.print(sofar + ", ");
    	
    	for (int i = 0; i < input.length(); ++i) {
    		permutation(input.substring(0, i) + input.substring(i + 1), sofar + input.charAt(i));
    	}
    }
    
    /**
     * Converts a string like - "aabbccdddd" to "a2b2c2d4". There is always more than 
     * 1 character in every contiguous sequence. 
     * 
     * @param str
     */
    public static void encodeString(String str) {
    	char[] arr = str.toCharArray();
    	int i = 0;
    	int indicesLeftVacant = 0;
    	
    	while (i < arr.length - 1) {
    		int j = i + 1;
    		while (j < arr.length && arr[j] == arr[i]) {
    			j++;
    		}
    		
    		// Move the current character to next eligible index, that will be after the
    		// previous occurrence count was written. So, we need to move the character 
    		// indicesLeftVacant times from the original index.
    		arr[i - indicesLeftVacant] = arr[i];
    		
    		// (j - i) gives the total number of the current character. We need to put it
    		// in the index next to this character. (i - indicesLeftVacant + 1)
    		arr[i - indicesLeftVacant + 1] = (char)((j - i) + (int)'0');
    		
    		// (j - i - 2) will be the number of indices left free. 
    		// So, add it to indicesLeftVacant
    		indicesLeftVacant += (j - i) - 2;
    		
    		// Set i to j, to start looking from the next sequence of characters.
    		i = j;
    	}
    	
    	// The new array length will be the original length reduced by indicesLeftVacant.
    	// So, create the new String from index 0 till that length from array.
    	System.out.println(new String(arr, 0, arr.length - indicesLeftVacant));
    }
    
}
