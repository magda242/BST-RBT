package trees;

import java.util.*;

public class BST_Tree {

	public Node root;

	public class Node {

		public int key;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int item) {
			this.key = item;
			this.left = this.right = null;

		}

		public Node() {

		}

	}

	class QItem {
		Node node;
		int hd;

		public QItem(Node n, int h) {
			node = n;
			hd = h;
		}
	}

	public BST_Tree() {
		this.root = null;
	}

	
	public void insert(int key) {
		if (root == null)
			root = new Node(key);
		else {
			Node actual = root;
			Node parent = null;
			while (actual != null) {
				parent = actual;
				actual = (actual.key > key) ? actual.left : actual.right;
			}
			if (parent.key > key) {
				parent.left = new Node(key);
				parent.left.parent = parent;
			} else {
				parent.right = new Node(key);
				parent.right.parent = parent;
			}
		}
	}

	// znalezienie wêz³a o podanym kluczu
	public Node search(Node root, int key) {
		if (root == null || root.key == key)
			return root;

		if (root.key > key)
			return search(root.left, key);

		return search(root.right, key);

	}

	public Node search(int key) {
		if (root.key == key)
			return root;
		if (root == null) {
			throw new NullPointerException();
		}
		if (root.key > key)
			return search(root.left, key);

		return search(root.right, key);

	}

	public int wysokosc(Node root) {

		if ((root) == null) {
			return 0;
		}

		int lewawys = wysokosc(root.left);
		int prawawys = wysokosc(root.right);
		return 1 + Math.max(lewawys, prawawys);

	}

	public int wysokosc() {

		if ((root) == null) {
			return 0;
		}

		int lewawys = wysokosc(root.left);
		int prawawys = wysokosc(root.right);
		return 1 + Math.max(lewawys, prawawys);

	}

	public int numberofnodes(Node root) {
		{
			if (root == null)
				return 0;
			else if (root.left == null && root.right == null)
				return 1;
			else
				return (1 + (numberofnodes(root.left)) + numberofnodes(root.right));
		}
	}

	public int numberofnodes() {

		{
			if (root == null)
				return 0;
			else if (root.left == null && root.right == null)
				return 1;
			else
				return (1 + (numberofnodes(root.left)) + numberofnodes(root.right));
		}
	}

	public int minimal(Node root) {

		Node temp = root;
		int minimal = temp.key;
		while (temp.left != null) {
			minimal = temp.left.key;
			temp = temp.left;
		}
		return minimal;

	}

	public int minimal() {

		Node temp = root;
		int minimal = temp.key;
		while (temp.left != null) {
			minimal = temp.left.key;
			temp = temp.left;
		}
		return minimal;

	}

	public Node minimalnode(Node node) {

		Node current = root;

		while (current.left != null) {
			current = current.left;
		}
		return current;
	}
	/*
	 * Node temp = root; Node min = temp; while (temp.left != null) { min =
	 * temp.left; temp = temp.left; } return min;
	 * 
	 */

	public int maxvalue() {

		Node temp = root;
		int max = root.key;
		while (temp.right != null) {
			max = temp.right.key;
			temp = temp.right;
		}
		return max;
	}


	public int leaves(Node node) {
		if (node == null)
			return 0;
		if (node.left == null && node.right == null)
			return 1;
		else
			return leaves(node.left) + leaves(node.right);
	}

	public int numberofinternalnodes() {
		{
			if (root == null) {

				return 0;
			} else if (root != null && root.left == null && root.left == null)
				return 1;
			else
				return (numberofnodes() - leaves(root));
		}
	}
	

	public void delete(int key) {
		root = deleteRec(root, key);
	}

	Node deleteRec(Node root, int key) {
		if (root == null)
			return root;

		if (key < root.key)
			root.left = deleteRec(root.left, key);
		else if (key > root.key)
			root.right = deleteRec(root.right, key);

		else {
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;

			root.key = minimal(root.right);

			root.right = deleteRec(root.right, root.key);
		}

		return root;
	}

	// do rysowania drzewa

	private List<List<Node>> traverseLevels(Node root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<List<Node>> levels = new LinkedList<>();

		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root);

		while (!nodes.isEmpty()) {
			List<Node> level = new ArrayList<>(nodes.size());
			levels.add(level);

			for (Node node : new ArrayList<>(nodes)) {
				level.add(node);
				if (node.left != null) {
					nodes.add(node.left);
				}
				if (node.right != null) {
					nodes.add(node.right);
				}
				nodes.poll();
			}
		}
		return levels;
	}

	public void printLevelWise(Node root) {
		List<List<Node>> levels = traverseLevels(root);

		for (List<Node> level : levels) {
			for (Node node : level) {
				System.out.print(node.key + " ");
			}
			System.out.println();
		}
	}
}