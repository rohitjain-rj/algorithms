package algo.tree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

	private BinarySearchTree<Integer> bst;
	
	@Before
	public void setUp() {
		bst = new BinarySearchTree<Integer>();
	}
	
	@After
	public void tearDown() {
		bst = null;
	}
	
	@Test
	public void height() {
		bst.insert(5);
		bst.insert(2);
		bst.insert(1);
		
		assertEquals(3, bst.getHeight());
	}

	@Test
	public void lca() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
		
		int lca = bst.lowestCommonAncestor(4, 56).data;
		assertEquals(5, lca);
	}

	@Test
	public void balancedBST() {
		bst.insert(5);
		bst.insert(3);
		bst.insert(8);
		bst.insert(7);
		bst.insert(2);
		bst.insert(4);
		bst.insert(10);
		bst.insert(9);
		assertTrue(bst.isBalanced());
	}
	
	@Test
	public void unbalancedBST() {
		bst.insert(5);
		bst.insert(3);
		bst.insert(8);
		bst.insert(10);
		bst.insert(9);
		assertFalse(bst.isBalanced());
	}
	
	@Test
	public void testShortestPathTT() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
//		
//		List<Node<Integer>> actual = bst.shortestPath(2, 56);
//		
//		List<Node<Integer>> expected = new ArrayList<Node<Integer>>();
	}

}
