package db.data.structures.hash.position;

import java.util.Iterator;
import db.interfaces.hash.IList;
import db.interfaces.hash.position.Position;

/**
 * An iterator over a db.data.structures.hash.position.position List. This will return the Item that is
 * contained in the list
 *
 * @param <T> The object type that will be returned by the Iterator
 */
public class PositionListIterator<T> implements Iterator<T> {
	IList<T> list;
	Position<T> cursor;
	boolean valid;
	
	/**
	 * The constructor
	 * @param list the list to iterate over
	 */
	public PositionListIterator(PositionList<T> list) {
		this.list = (IList<T>) list;
		valid = true;
		if (!list.isEmpty()) {
			this.cursor = list.first();
		}
	}
	
	@Override
	/**
	 * Returns true if there next() will return an element
	 */
	public boolean hasNext() {
		return (!list.isEmpty() && list.next(cursor) != null) && valid == true;
	}

	@Override
	/**
	 * Return the "next" item in the list and then advance the cursor.
	 */
	public T next() {
		T element = cursor.element();
		cursor = list.next(cursor);
		return element;
	}

	@Override
	/**
	 * Should be used to remove the item from the list, but for now
	 * we do not include an implementation.
	 */
	public void remove() {
		list.remove(list.prev(cursor));
		valid = false;
	}

	public Position<T> getCursor()
	{
		return cursor;
	}

}
