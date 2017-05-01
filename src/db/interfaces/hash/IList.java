package db.interfaces.hash;

import db.interfaces.position.Position;
/**
 * Created by danie on 2017/04/10.
 */

/**
 * The hash table list interface
 * @param <T> ADT type
 */
public interface IList<T> extends Iterable<T>
{
	public Position<T> addAfter(Position<T> p, T item);

	public Position<T> addBefore(Position<T> p, T item);

	public Position<T> addFirst(T item);

	public Position<T> addLast(T item);

	public T remove(Position<T> p);


	public Position<T> next(Position<T> p);

	public Position<T> prev(Position<T> p);

	public Position<T> first();

	public boolean isEmpty();

	public int size();
}
