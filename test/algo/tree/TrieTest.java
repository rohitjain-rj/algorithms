package algo.tree;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	String[] words;
	Trie trie;
	
	@Before
	public void setUp() throws Exception {
		trie = new Trie();
		words = new String[] { "bell", "best", "bet", "select", "sell", "sent", "create", "cron" };
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void searchNewTrie() {
		assertFalse(trie.search("sel"));
	}
	
	@Test
	public void searchOneWord() {
		trie.insert(words[0]);
		assertTrue(trie.search(words[0]));
	}
	
	@Test
	public void searchLongWord() {
		trie.insert(words[0]);
		assertFalse(trie.search(words[0] + words[0]));
	}
	
	@Test
	public void testPartialMatch() {
		trie.insert("hello");
		assertFalse(trie.search("hell"));
	}
}
