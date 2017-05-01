//Overall BinaryTree class: 25 marks *****************************************
package db.data.structures.tree;

import db.data.structures.position.PositionException;
import db.data.structures.position.PositionList;
import db.interfaces.tree.BTPosition;
import db.models.tree.KeyValue;
import db.models.tree.NodeKey;

import java.util.Iterator;

/**
 * A Binary Tree. There should be a reference to the root of the tree
 * and then all of the nodes contained in the tree are children of the root.
 *
 * @param <T> The type of the objects that will be contained in the tree
 */
public class BinaryTree<T> implements Iterable<KeyValue<T>>
{
	private BTNode<T> root;
	private int size;
	private String key;

	/**
	 * Create a new tree, with a null node in the root;
	 */
	public BinaryTree(String key)
	{
		root = new BTNode<T>(null, null, null, null, null);
		size = 0;
		this.key = key;
	}

	public String getKey()
	{
		return key;
	}

	public BinaryTree(String key, BTNode elemetn)
	{
		root = elemetn;
		size = 1;
		this.key = key;

	}

	public BinaryTree(BTNode<T> root)
	{
		this.root = root;
	}

	/**
	 * Get the root of the tree
	 *
	 * @return return the root of the tree
	 */
	public BTPosition<T> root()
	{
		return root;
	}

	/**
	 * Get an iterator for the children of a node
	 *
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> left(BTPosition<T> node)
	{
		BTNode<T> treeNode = checkPosition(node);

		if (hasLeft(treeNode))
		{
			return treeNode.left();
		}
		return null;
	}

	/**
	 * Get an iterator for the children of a node
	 *
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> right(BTPosition<T> node)
	{
		BTNode<T> treeNode = checkPosition(node);

		if (hasRight(treeNode))
		{
			return treeNode.right();
		}
		return null;
	}

	public boolean hasLeft(BTPosition<T> node)
	{
		BTNode<T> treeNode = checkPosition(node);

		return treeNode.left() != null;
	}

	public boolean hasRight(BTPosition<T> node)
	{
		BTNode<T> treeNode = checkPosition(node);

		return treeNode.right() != null;
	}

	/**
	 * Get the parent of a node
	 *
	 * @param node a Position<T> which is a node in this tree
	 * @return a Position<T> which is the parent of this node
	 */
	public BTPosition<T> parent(BTPosition<T> node)
	{
		return node.parent();
	}

	/**
	 * Returns a list of all of the Positions in this tree. These could be
	 * converted to a TreeNode<T> in order to operate on the tree
	 *
	 * @return A IList<BTPosition<T>> of all the positions in the tree
	 * 5 marks ***********************************************************************************
	 */
	public PositionList<BTPosition<T>> positions()
	{
		PositionList<BTPosition<T>> tree = new PositionList<>();
		if (!isEmpty())
		{
			nodeTraversal(tree, root());
		}
		return tree;
	}

	/**
	 * Returns an iterator over all of the elements in this tree
	 *
	 * @return an Iterator<T> over all of the elements
	 */
	public Iterator<KeyValue<T>> elements()
	{
		PositionList<KeyValue<T>> elementList = new PositionList<>();
		PreorderElementTraversal(elementList, root);
		return elementList.iterator();
	}

	/*
	 * Calculates the depth of a Binary tree node position in the overall binary tree
	 * 5 marks ***********************************************************************************
	 */
	public Integer nodeDepth(BTPosition<T> nodePos)
	{
		if (nodePos.parent() == null)
		{
			return 0;
		} else
		{
			return 1 + nodeDepth(nodePos.parent());
		}
	}

	/**
	 * Return an Iterator over all of the elements in this tree
	 */
	@Override
	public Iterator<KeyValue<T>> iterator()
	{
		return elements();
	}

	/**
	 * The number of nodes in this tree
	 *
	 * @return the number of nodes in this tree
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Return true if the tree is empty
	 *
	 * @return true if the tree is empty, false if the tree is not empty;
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * Perform a preorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 *
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
	 */
	public void nodeTraversal(PositionList<BTPosition<T>> elements, BTPosition<T> root)
	{
		BTNode<T> rootNode = checkPosition(root);
		elements.addLast(rootNode);

		if (root.leftChild() != null)
		{
			nodeTraversal(elements, root.leftChild());
		}

		if (root.rigthChild() != null)
		{
			nodeTraversal(elements, root.rigthChild());
		}
	}

	/**
	 * Perform a preorder traversal over all of the elements in the list. These elements should be
	 * added to the PositionList.
	 *
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
	 *                 5 marks ***********************************************************************************
	 */
	public void PreorderElementTraversal(PositionList<KeyValue<T>> elements, BTPosition<T> root)
	{
		if (root != null)
		{
			elements.addLast(new KeyValue<>(root.key(), root.element()));
			if (root.leftChild() != null)
				PreorderElementTraversal(elements, root.leftChild());
			if (root.rigthChild() != null)
				PreorderElementTraversal(elements, root.rigthChild());
		}
	}


	/**
	 * Perform a preorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 *
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
	 *                 5 marks ***********************************************************************************
	 */
	public void PostOrderElementTraversal(PositionList<KeyValue<T>> elements, BTPosition<T> root)
	{
		if (root != null)
		{
			if (root.leftChild() != null)
				PostOrderElementTraversal(elements, root.rigthChild());
			if (root.rigthChild() != null)
				PostOrderElementTraversal(elements, root.rigthChild());
			elements.addLast(new KeyValue<>(root.key(), root.element()));
		}
	}

	/**
	 * Perform an inorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 *
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
	 *                 5 marks ***********************************************************************************
	 */
	public void InorderElementTraversal(PositionList<KeyValue<T>> elements, BTPosition<T> root)
	{
		if (root != null)
		{
			if (root.leftChild() != null)
				InorderElementTraversal(elements, root.leftChild());
			elements.addLast(new KeyValue<>(root.key(), root.element()));
			if (root.rigthChild() != null)
				InorderElementTraversal(elements, root.rigthChild());
		}
	}

	/**
	 * Convert a Position<T> into a TreeNode<T>
	 *
	 * @param p a Position<T> to convert
	 * @return the corresponding TreeNode<T>
	 */
	private BTNode<T> checkPosition(BTPosition<T> p)
	{
		if (!(p instanceof BTNode<?>))
		{
			throw new PositionException("Invalid Position");
		}

		return (BTNode<T>) p;
	}

	public void setRoot(BTNode<T> root)
	{
		this.root = root;
	}

	public BTNode<T> searchNode(String key)
	{
		PositionList<BTPosition<T>> nodes = new PositionList<>();
		nodeTraversal(nodes, root);
		Iterator<BTPosition<T>> nodeIt = nodes.iterator();
		BTNode<T> node = null;
		while (nodeIt.hasNext())
		{
			node = checkPosition(nodeIt.next());
			if (node.getKey().getNodeKey().equals(key))
			{
				return node;
			}
		}
		return null;
	}

}
