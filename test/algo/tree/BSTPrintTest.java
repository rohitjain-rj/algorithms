package algo.tree;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BSTPrintTest {

	private BinarySearchTree<Integer> bst;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() {
		bst = new BinarySearchTree<Integer>();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() {
		bst = null;
		System.setOut(null);
	}

	@Test
	public void preOrderTraversal() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
		
		bst.preOrder();
		assertEquals("5, 4, 2, 56, 10, ", outContent.toString());
	}

	@Test
	public void postOrderTraversal() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
		
		bst.postOrder();
		assertEquals("2, 4, 10, 56, 5, ", outContent.toString());
	}

	@Test
	public void inOrderTraversal() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
		
		bst.inOrder();
		assertEquals("2, 4, 5, 10, 56, ", outContent.toString());
	}

	@Test
	public void testLevelOrder() {
		bst.insert(5);
		bst.insert(4);
		bst.insert(56);
		bst.insert(2);
		bst.insert(10);
		
		bst.levelOrder();
		assertEquals("5, 4, 56, 2, 10, ", outContent.toString());
	}

	@Test
	public void testSpiralOrder() {
		bst.insert(5);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		bst.insert(4);
		bst.insert(7);
		bst.insert(6);
		bst.insert(8);
		
		bst.spiralOrder();
		assertEquals("5, 3, 7, 8, 6, 4, 1, ", outContent.toString());
	}
}
