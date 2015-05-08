package algo.tree;

import java.util.ArrayList;
import java.util.List;

public class Trie {

	private static final int MAX_CHILD_PER_NODE = 26;
	
	private TrieNode root;
	
	private class TrieNode {
		char value;
		boolean end;
		List<TrieNode> children;
		
		TrieNode(char value) {
			this.value = value;
			this.end = false;
			this.children = new ArrayList<>(MAX_CHILD_PER_NODE);
		}
		
		public boolean isEnd() {
			return end;
		}

		public void setEnd(boolean end) {
			this.end = end;
		}

		public List<TrieNode> getChildren() {
			return children;
		}
		
		public TrieNode getChild(TrieNode node) {
			for (TrieNode child: children) {
				if (child.equals(node)) {
					return child;
				}
			}
			return null;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof TrieNode)) 
				return false;
			TrieNode that = (TrieNode) obj;
			return value == that.value;
		}
	}
	
	public Trie() {
		root = new TrieNode('\0');
	}
	
	public void insert(String word) {
		TrieNode node = root;
		
		for (char ch: word.toCharArray()) {
			TrieNode temp = new TrieNode(ch);
			
			if (node.getChildren().contains(temp)) {
				node = temp;
			} else {
				node.getChildren().add(temp);
				node = temp;
			}
		}
		node.setEnd(true);
	}
	
	public boolean search(String word) {
		TrieNode node = root;

		for (char ch: word.toCharArray()) {
			TrieNode temp = new TrieNode(ch);
			node = node.getChild(temp);
			
			if (node == null) {
				return false;
			}
		}
		if (!node.isEnd()) {
			return false;
		}
		return true;
	}
}
