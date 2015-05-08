package algo.main;

import java.awt.geom.Rectangle2D;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.list.MyLinkedList;
import com.list.QueueWithSingleStack;
import com.list.QueueWithTwoStacks;
import com.list.StackUsingArray;
import com.list.StackUsingSingleQueue;
import com.list.MyLinkedList.LinkedListNode;
import com.list.MyList;
import com.list.StackUsingTwoQueues;

import algo.generic.ArrayAlgo;
import algo.generic.BitUtility;
import algo.generic.MathUtil;
import algo.generic.StringAlgo;
import algo.tree.BinarySearchTree;
import algo.tree.BinarySearchTree.BSTNode;

public class Main {
	static int count = 1;

	public static void main(String[] args) {
		
	    final int range = 1000000;
	    int maxSeq = 0;
	    List<Integer> chainList = new ArrayList<Integer>();

	    for(int i = 1; i <= range; i++) {
	        generateSequence(i);
	        if(chainList.isEmpty()) {
	            chainList.add(count);
	            count = 1;
	        } else if(!chainList.contains(count) && count > Collections.max(chainList)) {
	            chainList.clear();
	            chainList.add(count);
	            maxSeq = i;
	            count = 1;
	        } 
	    }
	    System.out.println("Sequence starting number: "+maxSeq);
	}

	private static void generateSequence(long num) {
	    if(num == 1) {
	        return;
	    }
	    if(num % 2 == 0) {
	        count++;
	        generateSequence(num/2);
	    } else {
	        count++;
	        generateSequence(num*3+1);
	    }
	}
	
//	public static void main(String[] args) {
//		System.out.println(MathUtil.projectEuler14());
//	}
	
	/*public static void main (String args[]) throws Exception {
	
		System.out.println(BitUtility.nextLargestWithSameNumberOfBits(0b100111010110));
		System.out.println(0b100111011001);
		System.out.println(0b10001010100);
		Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		
		BinarySearchTree<Integer> bst = BinarySearchTree.createMinimalBST(array);
		bst.levelOrder();
		System.out.println(bst.isBalanced());
		
		System.out.println("-----------------------------");
		Map<Integer, LinkedList<BSTNode<Integer>>> map = bst.levelWiseLinkedList();
		System.out.println(map);
		
		System.out.println("-----------------------------");
		StackUsingArray stack = new StackUsingArray();
		stack.push(3);
		System.out.println("Min : " + stack.min());
		stack.push(4);
		System.out.println("Min : " + stack.min());
		stack.push(2);
		System.out.println("Min : " + stack.min());
		stack.push(1);
		System.out.println("Min : " + stack.min());
		System.out.println(stack.pop());
		System.out.println("Min : " + stack.min());
		System.out.println(stack.pop());
		System.out.println("Min : " + stack.min());
		
		System.out.println("-------------------------------");
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		LinkedListNode<Integer> one = list.insert(1);
		LinkedListNode<Integer> two = list.insert(2);
		LinkedListNode<Integer> three = list.insert(3);
		LinkedListNode<Integer> four = list.insert(4);
		LinkedListNode<Integer> three2 = list.insert(3);
		LinkedListNode<Integer> two2 = list.insert(2);
		LinkedListNode<Integer> one2 = list.insert(1);

		one.setRandom(three);
		one2.setRandom(two2);
		two.setRandom(three2);
		three.setRandom(four);
		four.setRandom(one2);
		three2.setRandom(one);
		two2.setRandom(two);
		
		System.out.println("Original");
		list.removeDuplicates();
		System.out.println(list);
		list.reverse();
		System.out.println("Reversed");
		System.out.println(list);
		
//		System.out.println(BitUtility.totalSetBits(0b11111001));
//		
//		System.out.println(ArrayAlgo.indexOfFirstOne(new int[] {0, 0, 0, 0, 1, 1, 1, 1}));
//		System.out.println(ArrayAlgo.indexOfFirstOne(new int[] {1, 1, 1, 1}));
//		System.out.println(ArrayAlgo.indexOfFirstOne(new int[] {0, 0, 0, 0}));
//		
//		int[][] mat = {{1, 1, 0, 0, 0}, {1, 1, 1, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}};
//		System.out.println(ArrayAlgo.rowWithMaxZero(mat));
//		
//		int[] arr = {4, 2, 1, 9};
//		int[] arr2 = {-3, -4, -5, -6};
//		
//		int[] arr3 = {-2, 3, 4};  // fail
//		int[] arr4 = {-1, -2, 3, 4};
//		int[] arr5 = {-1, -5, -2, 3, 4};
//		
//		int[] arr6 = {-6, -3, 6};
//		int[] arr7 = {-2, -7, 5, 6};
//		int[] arr8 = {-2, -7, 5, 6, 9};
//		
//		System.out.println("arr: " + ArrayAlgo.maxProduct3(arr));
//		System.out.println(ArrayAlgo.maxProduct3(arr2));
//		System.out.println(ArrayAlgo.maxProduct3(arr3));
//		System.out.println(ArrayAlgo.maxProduct3(arr4));
//		System.out.println(ArrayAlgo.maxProduct3(arr5));
//		System.out.println(ArrayAlgo.maxProduct3(arr6));
//		System.out.println(ArrayAlgo.maxProduct3(arr7));
//		System.out.println(ArrayAlgo.maxProduct3(arr8));
//		
//		System.out.println("-------------------------");
//		arr = new int[] {4, 5, -19, 3};
//
//        List<Integer> superSet = new ArrayList<>();
//
//        for (int a : arr ){
//        superSet.add(a);
//        }
//
//        int k = 3;
//
//        int maxProduct = computeMaxProduct(superSet, k);
//        System.out.println("maximum product is : " + maxProduct);
	}*/
	
	private static int computeMaxProduct( List<Integer> superSet, int k ){
        List<Set<Integer>> res = getSubsets(superSet,k);
        int maxProduct = 1;
        for(int index = 0; index < res.size(); index++){
        int product = 1;
        for(Integer i : res.get(index)){
            product *= i;
        }

        if (product > maxProduct){
            maxProduct = product;
        }
        }

    return maxProduct;
    }


    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current,List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx+1, current, solution);
        current.remove(x);
        //"guess" x is not in the subset
        getSubsets(superSet, k, idx+1, current, solution);
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }
    
   
}
