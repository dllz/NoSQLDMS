package main.db.data.structures.tree;

import main.db.data.structures.position.PositionException;
import main.db.data.structures.position.PositionList;
import main.db.interfaces.tree.BTPosition;
import main.db.interfaces.tree.IList;

import java.util.Iterator;

/**
 * A Binary Tree. There should be a reference to the root of the tree
 * and then all of the nodes contained in the tree are children of the root.
 *
 * @param <T> The type of the objects that will be contained in the tree
 */
public class BinaryTree<T> implements Iterable<T> {
	protected BTNode<T> root;
	protected int size;
	
	/**
	 * Create a new tree, with a null node in the root;
	 */
	public BinaryTree() {
		root = null;
		size = 0;
	}
	
	public BinaryTree(T element) {
		root = new BTNode<T>(null,
				new BTNode<T>(null, null, null, null),
				new BTNode<T>(null, null, null, null),
				element);
		size = 1;
	}
	
	public BinaryTree(BTNode<T> root) {
		this.root = root;
	}
	
	/**
	 * Get the root of the tree
	 * @return return the root of the tree
	 */
	public BTPosition<T> root() {
		return root;
	}
	
	/**
	 * Get an iterator for the children of a node
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> left(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		if (hasLeft(treeNode)) {
			return treeNode.left();
		}
		return null;
	}
	
	/**
	 * Get an iterator for the children of a node
	 * @param node a Position<T> which is a node in this tree;
	 * @return an Iterator over the children of a node
	 */
	public BTPosition<T> right(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		if (hasRight(treeNode)) {
			return treeNode.right();
		}
		return null;
	}

	/**
	 * Return true if the node has a has a left child
	 * @param node The main.db.interfaces.tree.BTPosition<T> node
	 * @return true if there is a left child
	 */
	public boolean hasLeft(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		return treeNode.left() != null;
	}
	
	/**
	 * Returns true if the node has a right child
	 * @param node the main.db.interfaces.tree.BTPosition<T> node
	 * @return true if the node has a right child.
	 */
	public boolean hasRight(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		return treeNode.right() != null;
	}
	
	public boolean isLeftChild(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		return treeNode.parent().left() == treeNode;
	}
	
	public boolean isRightChild(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		return treeNode.parent().right() == treeNode;
	}
	
	public boolean isRoot(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		return root == treeNode;
	}
	
	public BTNode<T> sibling(BTPosition<T> node) {
		BTNode<T> treeNode = checkPosition(node);
		
		if (isRoot(treeNode)) {
			return null;
		}
		
		if (isLeftChild(node)) {
			return treeNode.parent().right();
		}
		return treeNode.parent().left();
	}
	
	public boolean isExternal(BTPosition<T> node) {
		if (node.left() == null && node.right() == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the parent of a node
	 * @param node a Position<T> which is a node in this tree
	 * @return a Position<T> which is the parent of this node
	 */
	public BTPosition<T> parent(BTPosition<T> node) {
		return node.parent();
	}
	
	/**
	 * Returns a list of all of the Positions in this tree. These could be
	 * converted to a TreeNode<T> in order to operate on the tree
	 * @return A main.db.interfaces.tree.IList<Position<T>> of all the positions in the tree
	 */
	public IList<BTPosition<T>> positions() {
		IList<BTPosition<T>> positionList = (IList<BTPosition<T>>) new PositionList<BTPosition<T>>();
		PreorderNodeTraversal(positionList, root);
		return positionList;
	}
	
	/**
	 * Returns an iterator over all of the elements in this tree
	 * @return an Iterator<T> over all of the elements
	 */
	public Iterator<T> elements() {
		PositionList<T> elementList = new PositionList<T>();
		PreorderElementTraversal(elementList, root);
		return elementList.iterator();
	}
	
	/**
	 * Return an Iterator over all of the elements in this tree
	 */

	public Iterator<T> iterator() {
		return elements();
	}
	
	/**
	 * The number of nodes in this tree
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return true if the tree is empty
	 * @return true if the tree is empty, false if the tree is not empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Perform a preorder traversal over all of the elements in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 */
	public void PreorderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
		BTNode<T> rootNode = checkPosition(root);
		elements.addLast(rootNode.element());
		
		if (hasLeft(root)) {
			PreorderElementTraversal(elements, left(root));
		}
		
		if (hasRight(root)) {
			PreorderElementTraversal(elements, right(root));
		}
	}
	
	/**
	 * Perform a preorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 */
	public void PreorderNodeTraversal(IList<BTPosition<T>> elements, BTPosition<T> root) {
		BTNode<T> rootNode = checkPosition(root);
		elements.addLast(rootNode);
		
		if (hasLeft(root)) {
			PreorderNodeTraversal(elements, left(root));
		}
		
		if (hasRight(root)) {
			PreorderNodeTraversal(elements, right(root));
		}
	}
	
	/**
	 * Perform a Postorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 */
	public void PostOrderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
		BTNode<T> rootNode = checkPosition(root);
		
		if (hasLeft(root)) {
			PostOrderElementTraversal(elements, left(root));
		}
		
		if (hasRight(root)) {
			PostOrderElementTraversal(elements, right(root));
		}
		
		elements.addLast(rootNode.element());
	}

	/**
	 * Perform an inorder traversal over all of the Positions in the list. These elements should be
	 * added to the PositionList.
	 * @param elements a PositionList<T> that will hold all of the elements in this tree
	 * @param root The root of the subtree
	 */
	public void InorderElementTraversal(PositionList<T> elements, BTPosition<T> root) {
		BTNode<T> rootNode = checkPosition(root);
		
		if (hasLeft(root)) {
			InorderElementTraversal(elements, left(root));
		}
		
		elements.addLast(rootNode.element());
		
		if (hasRight(root)) {
			InorderElementTraversal(elements, right(root));
		}
	}

	/**
	 * Convert a Position<T> into a TreeNode<T>
	 * @param p a Position<T> to convert
	 * @return the corresponding TreeNode<T>
	 */
	private BTNode<T> checkPosition(BTPosition<T> p) {
		if (!(p instanceof BTNode<?>)) {
			throw new PositionException("Invalid Position");
		}
		
		return (BTNode<T>)p;
	}

}
