package db.interfaces.tree;

/**
 * The Binary Tree Position interface used for implementation
 *
 * @param <T> the type of the generic for this object
 */

import db.models.tree.NodeKey;

/**
 * Created by danie on 2017/04/10.
 */
public interface BTPosition<T>
{
	public T element();

	public BTPosition<T> left();

	public BTPosition<T> right();

	public BTPosition<T> parent();

	public BTPosition<T> leftChild();

	public BTPosition<T> rigthChild();

	public NodeKey key();
}
