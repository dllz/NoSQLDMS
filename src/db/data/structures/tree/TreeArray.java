package db.data.structures.tree;

import db.data.structures.position.PositionList;
import db.models.tree.EntryKeys;
import db.models.tree.NodeKey;

import java.util.Iterator;

/**
 * Created by danie on 2017/04/25.
 */
public class TreeArray
{
	private PositionList<BinaryTree<String>> list;
	private PositionList<BTNode<String>> reverse;

	public TreeArray()
	{
		list = new PositionList<>();
		reverse = new PositionList<>();
	}

	public synchronized void addLeft(String key, EntryKeys keys, String value)
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
					BTNode parent = tree.searchNode(keys.getParentKey());
					BTNode right = tree.searchNode(keys.getRightKey());
					BTNode<String> node = new BTNode<>(parent, null, right, new NodeKey(key, keys.getKey()), value);
					reverse.addLast(node);
					parent.setLeft(node);
				}
			}
		}
	}

	public synchronized void addRight(String key, EntryKeys keys, String value)
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
					BTNode parent = tree.searchNode(keys.getParentKey());
					BTNode left = tree.searchNode(keys.getLeftKey());
					BTNode<String> node = new BTNode<>(parent, left, null, new NodeKey(key, keys.getKey()), value);
					reverse.addLast(node);
					parent.setRight(node);
				}
			}
		}
	}

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
					reverse.addLast(node);
					tree.setRoot(node);
				}
			}
		}
	}

	public synchronized BinaryTree getTree(String key)
	{
		Iterator<BinaryTree<String>> treeIterator = list.iterator();
		BinaryTree<String> tree = null;
		while (treeIterator.hasNext())
		{
			tree = treeIterator.next();
			if (tree.getKey().equals(key))
			{
				return tree;
			}
			tree = null;
		}
		return null;
	}

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

	public synchronized BTNode<Object> getNode(NodeKey key)
	{
		BinaryTree<Object> tree = getTree(key.getTreeKey());
		if (tree != null)
		{
			return tree.searchNode(key.getNodeKey());
		}
		return null;
	}

	public synchronized Object getElement(NodeKey key)
	{
		BTNode<Object> node = getNode(key);
		if (node != null)
		{
			return node.element();
		}
		return null;
	}

	public synchronized PositionList<String> inOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<String> elemts = new PositionList<>();
			tree.InorderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}

	public synchronized PositionList<String> PostOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<String> elemts = new PositionList<>();
			tree.PostOrderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}

	public synchronized PositionList<String> PreOrderTraversal(String key)
	{
		BinaryTree<String> tree = getTree(key);
		if (tree != null)
		{
			PositionList<String> elemts = new PositionList<>();
			tree.PreorderElementTraversal(elemts, tree.root());
			return elemts;
		}
		return null;
	}

}
