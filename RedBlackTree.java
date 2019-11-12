package trees;

import trees.BST_Tree.Node;

public class RedBlackTree {
	private final int red = 0;
	private final int black = 1;

	public class Node {

		public int key=-1;
		public int color = black;
		public Node left = ab;
		public Node right = ab;
		Node parent = ab;

		Node(int key) {
			this.key = key;
		}
	}

	private final Node ab = new Node(-1);
	public Node root = ab;

	public int depth(Node root)
    {
        // Corner case. Should never be hit unless the code is
        // called on root = NULL
        if (root == null)
            return 0;
 
        // Base case : Leaf Node. This accounts for height = 1.
        if (root.left == null && root.right == null)
            return 1;
 
        // If left subtree is NULL, recur for right subtree
        if (root.left == null)
            return depth(root.right) + 1;
 
        // If right subtree is NULL, recur for right subtree
        if (root.right == null)
            return depth(root.left) + 1;
 
        return Math.min(depth(root.left),
                        depth(root.right)) + 1;
    }
	
	
	public void printPreorder(Node node)
    {
        if (node == null)
            return;
 
        if (node.parent.key!=1) {
        System.out.print(((node.color == red) ? "color: red   " : "color: black ") + "key: " + node.key + " parent: "
				);
        System.out.println(node.parent.key + "");
        
        
        }
         
        printPreorder(node.left);
 
        
        printPreorder(node.right);
        
    }
	
	public void print(Node root) {
		Node temp=root;
		if (temp == null)
            return;
 
        if (root.parent.key!=-1) {
        System.out.print(((temp.color == red) ? "color: red   " : "color: black ") + "key: " + temp.key + " parent: "
				);
        System.out.println(temp.parent.key + "");
        }
        
        if(temp.left!=null) {
        	print(temp);
        	
        }
		
		
	}
	
	
	public void printTree(Node node) {
		if (node == ab) {
			return;
		}
		
		
		
		if(node.parent.key!=-1 && depth(node)==1) {
		printTree(node.left);
		System.out.print(((node.color == red) ? "color: red   " : "color: black ") + "key: " + node.key + " parent: "
				+ node.parent.key + "");
		printTree(node.right);
		System.out.print("/n");
		
	}
		if(node.parent.key!=-1 && depth(node)==2) {
			printTree(node.left);
			System.out.print(((node.color == red) ? "color: red   " : "color: black ") + "key: " + node.key + " parent: "
					+ node.parent.key + "");
			printTree(node.right);
			System.out.print("\n");
		}	
		if(node.parent.key!=-1 && depth(node)==3) {
			printTree(node.left);
			System.out.print(((node.color == red) ? "color: red   " : "color: black ") + "key: " + node.key + " parent: "
					+ node.parent.key + "");
			printTree(node.right);
			System.out.print("\n");
		}	
	}
	
	public int getLevel(Node node, int data, int level) 
    {
        if (node == null)
            return 0;
  
        if (node.key == data)
            return level;
  
        int downlevel = getLevel(node.left, data, level + 1);
        if (downlevel != 0)
            return downlevel;
  
        downlevel = getLevel(node.right, data, level + 1);
        return downlevel;
    }

	private Node findNode(int key, Node node) {
		if (root == ab) {
			return null;
		}
		Node findNode = new Node(key);

		if (findNode.key < node.key) {
			if (node.left != ab) {
				return findNode(key, node.left);
			}
		} else if (findNode.key > node.key) {
			if (node.right != ab) {
				return findNode(key, node.right);
			}
		} else if (findNode.key == node.key) {
			return node;
		}
		return null;
	}

	public void insert(int key) {
		Node temp = root;
		Node node = new Node(key);
		if (root == ab) {
			root = node;
			node.color = black;
			node.parent = ab;
		} else {
			node.color = red;
			while (true) {
				if (node.key < temp.key) {
					if (temp.left == ab) {
						temp.left = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.left;
					}
				} else if (node.key >= temp.key) {
					if (temp.right == ab) {
						temp.right = node;
						node.parent = temp;
						break;
					} else {
						temp = temp.right;
					}
				}
			}
			fixTree(node);
		}
	}

	// bierze nowo dodany wezel
	public void fixTree(Node node) {
		while (node.parent.color == red) {
			Node uncle = ab;
			if (node.parent == node.parent.parent.left) {
				uncle = node.parent.parent.right;

				if (uncle != ab && uncle.color == red) {
					node.parent.color = black;
					uncle.color = black;
					node.parent.parent.color = red;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.right) {
					// podwojny obrot
					node = node.parent;
					rotateLeft(node);
				}
				node.parent.color = black;
				node.parent.parent.color = red;
				// jeden obrot
				rotateRight(node.parent.parent);
			} else {
				uncle = node.parent.parent.left;
				if (uncle != ab && uncle.color == red) {
					node.parent.color = black;
					uncle.color = black;
					node.parent.parent.color = red;
					node = node.parent.parent;
					continue;
				}
				if (node == node.parent.left) {
					// podwojny obrot
					node = node.parent;
					rotateRight(node);
				}
				node.parent.color = black;
				node.parent.parent.color = red;
				// 1obr.
				rotateLeft(node.parent.parent);
			}
		}
		root.color = black;
	}
	public int minimal() {

		Node temp = root;
		int minimal = temp.key;
		while (temp.left != ab) {
			minimal = temp.left.key;
			temp = temp.left;
		}
		return minimal;

	}
	public int maxvalue() {

		Node temp = root;
		int max = root.key;
		while (temp.right != ab) {
			max = temp.right.key;
			temp = temp.right;
		}
		return max;
	}
	void rotateLeft(Node node) {
		if (node.parent != ab) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}
			node.right.parent = node.parent;
			node.parent = node.right;
			if (node.right.left != ab) {
				node.right.left.parent = node;
			}
			node.right = node.right.left;
			node.parent.left = node;
		} else {
			Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = ab;
			root = right;
		}
	}

	void rotateRight(Node node) {
		if (node.parent != ab) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent;
			node.parent = node.left;
			if (node.left.right != ab) {
				node.left.right.parent = node;
			}
			node.left = node.left.right;
			node.parent.right = node;
		} else {
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = ab;
			root = left;
		}
	}

	public void deleteTree() {
		root = null;
	}

	public void przenies(Node cel, Node with) {
		if (cel.parent == ab) {
			root = with;
		} else if (cel == cel.parent.left) {
			cel.parent.left = with;
		} else
			cel.parent.right = with;
		with.parent = cel.parent;
	}

	public int numberofnodes() {
		return numberofnodes(root);
	}

	public int numberofnodes(Node node) {
		if (node == ab)
			return 0;
		else
			return (numberofnodes(node.left) + 1 + numberofnodes(node.right));
	}

	public int leaves(Node node) {
		if (node == ab)
			return 0;
		if (node.left == ab && node.right == ab)
			return 1;
		else
			return leaves(node.left) + leaves(node.right);
	}

	public int numberofinternalnodes() {
		{
			if (root == ab) {

				return 0;
			} else if (root != ab && root.left == ab && root.left == ab)
				return 1;
			else
				return (numberofnodes() - leaves(root));
		}
	}

	public int height(Node node) {
		if (node == ab) {
			return 0;
		} else {
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}

	public boolean delete(int key) {
		Node z = new Node(key);
		if ((z = findNode(z.key, root)) == null)
			return false;
		
		Node x;
		Node y = z; // temporary reference y
		int y_original_color = y.color;

		if (z.left == ab) {
			x = z.right;
			przenies(z, z.right);
		} else if (z.right == ab) {
			x = z.left;
			przenies(z, z.left);
		} else {
			y = treeMinimum(z.right);
			y_original_color = y.color;
			x = y.right;
			if (y.parent == z)
				x.parent = y;
			else {
				przenies(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			przenies(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if (y_original_color == black)
			fixDelete(x);
		return true;
	}

	void fixDelete(Node x) {
		while (x != root && x.color == black) {
			if (x == x.parent.left) {
				Node w = x.parent.right;
				if (w.color == red) {
					w.color = black;
					x.parent.color = red;
					rotateLeft(x.parent);
					w = x.parent.right;
				}
				if (w.left.color == black && w.right.color == black) {
					w.color = red;
					x = x.parent;
					continue;
				} else if (w.right.color == black) {
					w.left.color = black;
					w.color = red;
					rotateRight(w);
					w = x.parent.right;
				}
				if (w.right.color == red) {
					w.color = x.parent.color;
					x.parent.color = black;
					w.right.color = black;
					rotateLeft(x.parent);
					x = root;
				}
			} else {
				Node w = x.parent.left;
				if (w.color == red) {
					w.color = black;
					x.parent.color = red;
					rotateRight(x.parent);
					w = x.parent.left;
				}
				if (w.right.color == black && w.left.color == black) {
					w.color = red;
					x = x.parent;
					continue;
				} else if (w.left.color == black) {
					w.right.color = black;
					w.color = red;
					rotateLeft(w);
					w = x.parent.left;
				}
				if (w.left.color == red) {
					w.color = x.parent.color;
					x.parent.color = black;
					w.left.color = black;
					rotateRight(x.parent);
					x = root;
				}
			}
		}
		x.color = black;
	}

	Node treeMinimum(Node subTreeRoot) {
		while (subTreeRoot.left != ab) {
			subTreeRoot = subTreeRoot.left;
		}
		return subTreeRoot;
	}
}
