package db.interfaces.tree;

/**
 * The Binary Tree Position interface used for implementation
 *
 * @param <T> the type of the object for this position
 */
/**
 * Created by danie on 2017/04/10.
 */
public interface BTPosition<T>
{
	public T element();

	public BTPosition<T> left();

	public BTPosition<T> right();

	public BTPosition<T> parent();
}
