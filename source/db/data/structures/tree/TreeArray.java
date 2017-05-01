package db.data.structures.tree;

import db.data.structures.position.PositionList;
import db.models.hash.EntryStorage;
import db.models.tree.EntryKeys;
import db.models.tree.KeyValue;
import db.models.tree.NodeKey;

import java.util.Iterator;

/**
 * Created by danie on 2017/04/25.
 */
public class TreeArray
{
	private PositionList<BinaryTree<String>> list;
	private PositionList<BTNode<String>> reverse;

	/**
	 * Default constructor to intialise the tree
	 */
	public TreeArray()
	{
		list = new PositionList<>();
		reverse = new PositionList<>();
	}

	/**
	 * Add a node to the left child of a parent
	 * @param key The key of the node
	 * @param keys an EntryKeys object holding all the keys that the node needs to be attached to
	 * @param value The value to be stored
	 * @return A string holding the result
	 */
	public synchronized String addLeft(String key, EntryKeys keys, String value)
	{
		if (getTree(key) == null)
		{
			return "Tree Not Created";
		} else
		{
			Iterator<BinaryTree<String>> treeIterator = list.iterator();
			BinaryTree<String> tree = null;
			while (treeIterator.hasNext())
			{
				tree = treeIterator.next();
				if (tree.getKey().equals(key))
				{
					BTNode parent = tree.searchNode(keys.getParentKey());
					BTNode right = tree.searchNode(keys.getRightKey());
					BTNode<String> node = new BTNode<>(parent, null, right, new NodeKey(key, keys.getKey()), value);
					parent.setLeftChild(node);
					if(right != null)
					{
						BTNode rightsLeft = (BTNode) right.left();
						if(rightsLeft == null)
						{
							right.setLeft(node);
						}
					}
					Iterator<BTNode<String>> reverseit = reverse.iterator();
					while (reverseit.hasNext())
					{
						BTNode<String> temp = reverseit.next();
						if (temp.equals(node))
						{
							temp.setElement(value);
							return "Added";
						}
					}
					reverse.addLast(node);
					return "Added";
				}
			}
		}
		return "Failed";
	}

	/**
	 * Add a node to the right of a parent
	 * @param key The key of the node
	 * @param keys an EntryKeys object holding all the keys that the node needs to be attached to
	 * @param value The value to be stored
	 * @return A string holding the result
	 */
	public synchronized String addRight(String key, EntryKeys keys, String value)
	{
		if (getTree(key) == null)
		{
			return "Tree Not Created";
		} else
		{
			Iterator<BinaryTree<String>> treeIterator = list.iterator();
			BinaryTree<String> tree = null;
			while (treeIterator.hasNext())
			{
				tree = treeIterator.next();
				if (tree.getKey().equals(key))
				{
					BTNode parent = tree.searchNode(keys.getParentKey());
					BTNode left = tree.searchNode(keys.getLeftKey());
					BTNode<String> node = new BTNode<>(parent, left, null, new NodeKey(key, keys.getKey()), value);
					parent.setRightChild(node);
					if(left != null)
					{
						BTNode lefsRight = (BTNode) left.right();
						if(lefsRight == null)
						{
							left.setRight(node);
						}
					}
					Iterator<BTNode<String>> reverseit = reverse.iterator();
					while (reverseit.hasNext())
					{
						BTNode<String> temp = reverseit.next();
						if (temp.equals(node))
						{
							temp.setElement(value);
							return "Added";
						}
					}
					reverse.addLast(node);
					return "Added";
				}
			}
		}
		return "Failed";
	}

	/**
	 * Create the node a the roof of the tree
	 * @param key The key of the node
	 * @param keys an EntryKeys object holding all the keys that the node needs to be attached to
	 * @param value The value to be stored
	 */
	public synchronized void addRoot(String key, EntryKeys keys, String value)
	{
		if (getTree(key) == null)
		{
			BTNode<String> temp = new BTNode<>(null, null, null, new NodeKey(key, keys.getKey()), value);
			reverse.addLast(temp);
			BinaryTree<String> tempTree = new BinaryTree<>(key, temp);

			list.addLast(tempTree);
		} else
		{
			Iterator<BinaryTree<String>> treeIterator = list.iterator();
			BinaryTree<String> tree = null;
			while (treeIterator.hasNext())
			{
				tree = treeIterator.next();
				if (tree.getKey().equals(key))
				{
					BTNode<String> node = new BTNode<>(null, null, null, new NodeKey(key, keys.getKey()), value);
					Iterator<BTNode<String>> reverseit = reverse.iterator();
					while (reverseit.hasNext())
					{
						BTNode<String> temp = reverseit.next();
						if (temp.equals(node))
						{
							temp.setElement(value);
							return;
						}
					}
					reverse.addLast(node);
					tree.setRoot(node);
				}
			}
		}
	}

	/**
	 * Gets a tree in the array
	 * @param key you want to get
	 * @return BinaryTree at the key
	 */
	private synchronized BinaryTree getTree(String key)
	{
		Iterator<BinaryTree<String>> treeIterator = list.iterator();
		BinaryTree<String> tree = null;
		while (treeIterator.hasNext())
		{
			tree = treeIterator.next();
			if(tree == null)
				return null;
			if (tree.getKey().equals(key))
			{
				return tree;
			}
			tree = null;
		}
		return null;
	}

	/**
	 * Searches for matching keys
	 * @param value the value to be looked for
	 * @return a list of Nodekeys that match
	 */
	public synchronized PositionList<NodeKey> search(Object value)
	{
		PositionList<NodeKey> foundList = new PositionList<>();
		Iterator<BTNode<String>> nodeIt = reverse.iterator();
		BTNode entry = null;
		while (nodeIt.hasNext())
		{
			entry = nodeIt.next();
			if (entry.element().equals(value))
			{
				foundList.addLast(entry.getKey());
			}
		}
		return foundList;
	}

	/**
	 * Get the node matching the key
	 * @param key the key of the node u want
	 * @return the node at the key
	 */
	public synchronized BTNode<Object> getNode(NodeKey key)
	{
		BinaryTree<Object> tree = getTree(key.getTreeKey());
		if (tree != null)
		{
			return tree.searchNode(key.getNodeKey());
		}
		return null;
	}

	/**
	 * The element at a key
	 * @param key the key of the element you want
	 * @return the element at the key
	 */
	public synchronized Object getElement(NodeKey key)
	{
		BTNode<Object> node = getNode(key);
		if (node != null)
		{
			return node.element();
		}
		return null;
	}

	/**
	 * Do an inorder transversal of a tree
	 * @param key the key of the tree to transverse
	 * @return a list of keyValues of the traversal
	 */
	public synchronized PositionList<KeyValue<String>> inOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<KeyValue<String>> elemts = new PositionList<>();
			tree.InorderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}

	/**
	 * Do an postorder transversal of a tree
	 * @param key the key of the tree to transverse
	 * @return a list of keyValues of the traversal
	 */
	public synchronized PositionList<KeyValue<String>> PostOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<KeyValue<String>> elemts = new PositionList<>();
			tree.PostOrderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}


	/**
	 * Do an preorder transversal of a tree
	 * @param key the key of the tree to transverse
	 * @return a list of keyValues of the traversal
	 */
	public synchronized PositionList<KeyValue<String>> PreOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<KeyValue<String>> elemts = new PositionList<>();
			tree.PreorderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}

}
