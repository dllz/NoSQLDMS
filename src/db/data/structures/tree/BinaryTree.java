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

	/**
	 * Get the root of the tree
	 * @return return the root of the tree
	 */
	public BTPosition<T> root()
	{
		return root;
	}


	/**
	 * Returns an iterator over all of the elements in this tree
	 * @return an Iterator<T> over all of the elements
	 */
	public Iterator<KeyValue<T>> elements()
	{
		PositionList<KeyValue<T>> elementList = new PositionList<>();
		PreorderElementTraversal(elementList, root);
		return elementList.iterator();
	}

	/**
	 * iterator for the elements in the tree
	 * @return
	 */
	@Override
	public Iterator<KeyValue<T>> iterator()
	{
		return elements();
	}

	/**
	 * Perform a traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 *
	 * This is done recurrsively
	 *
	 * @param elements a PositionList<BTPosition<T>> that will hold all of the elements in this tree
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
	 * Perform a PreOrderElement traversal over all of the elements in the tree
	 *
	 * @param elements a PositionList<KeyValue<T>> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
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
	 * Performs a PostOrderElement traversal over all of the elements in the tree
	 *
	 * @param elements a PositionList<KeyValue<T>> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
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
	 * Perform an InOrder traversal over all of the elements in the tree
	 *
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root     The root of the subtree
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

	/**
	 * Searches for a node in the tree
	 * @param key the key that you looking for
	 * @return the found node, null if not found
	 */
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
