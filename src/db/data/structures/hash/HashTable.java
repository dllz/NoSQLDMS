package db.data.structures.hash;

import db.data.structures.position.PositionList;
import db.data.structures.position.PositionListIterator;
import db.interfaces.hash.IMap;
import db.interfaces.position.Position;
import db.models.hash.EntryStorage;
import db.models.hash.HashEntry;
import db.models.hash.HashField;

import java.io.Serializable;
import java.util.Iterator;

public class HashTable<K> implements IMap<K>, Serializable
{
	private final int TABLE_SIZE = 1024;
	private HashEntry<K>[] map;
	private PositionList<EntryStorage<K>> reverse;

	/**
	 * Default constructor
	 */
	public HashTable()
	{
		map = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < map.length; i++)
		{
			map[i] = null;
		}
		reverse = new PositionList<>();
	}


	/**
	 * Remove an item from the hash table
	 * @param key the place you want to remove it
	 * @param field the field at the key you want rmeoved
	 * @return the removed hashfield
	 */
	public synchronized HashField remove(K key, String field)
	{
		int pos = HashString(key.toString());
		HashEntry<K> item = map[pos];
		if (item != null)
		{
			Iterator<HashField> bucketIterator = item.getValue().iterator();
			HashField value;
			while (bucketIterator.hasNext())
			{
				value = bucketIterator.next();
				if (value.getField().equals(field))
				{
					bucketIterator.remove();
					reverse.remove(searchPos(key, field));
					return value;
				}
				value = null;
			}
		}
		return null;
	}

	/**
	 * Get the value for a given key
	 *
	 * @param key the key for the item
	 * @returns the value for the associated key
	 */
	public synchronized HashField get(K key, String field)
	{
		int pos = HashString(key.toString());
		HashEntry<K> item = map[pos];
		if (item != null)
		{
			Iterator<HashField> bucketIterator = item.getValue().iterator();
			HashField value;
			while (bucketIterator.hasNext())
			{
				value = bucketIterator.next();
				if (value.getField().equals(field))
				{
					return value;
				}
				value = null;
			}
		}

		return new HashField();
	}

	/**
	 * Get all the fields at the key
	 * @param key the key you want to get
	 * @return a list of the fields
	 */
	public synchronized PositionList<HashField> getAll(K key)
	{
		int pos = HashString(key.toString());
		HashEntry<K> item = map[pos];
		if (item != null)
		{
			return item.getValue();
		}
		return null;
	}

	/**
	 * Put an item into the hash table
	 *
	 * @param key   the key for the item (unique)
	 * @param value the value for the item
	 */
	public synchronized boolean put(K key, HashField value)
	{
		try
		{
			if (getAll(key) == null)
			{
				PositionList<HashField> temp = new PositionList<>(value);
				int pos = HashString(key.toString());
				map[pos] = new HashEntry<>(key, temp);
				reverse.addFirst(new EntryStorage<>(key, value));
			} else
			{
				int pos = HashString(key.toString());
				HashEntry<K> item = map[pos];
				if (item != null)
				{
					Iterator<HashField> bucketIterator = item.getValue().iterator();
					HashField posi;
					while (bucketIterator.hasNext())
					{
						posi = bucketIterator.next();
						if (posi.getField().equals(value.getField()))
						{
							posi.setValue(value.getValue());
							Iterator<EntryStorage<K>> reverseit = reverse.iterator();
							while (reverseit.hasNext())
							{
								EntryStorage<K> temp = reverseit.next();
								if (temp.getKey().equals(key) & temp.getField().equals(value.getField()))
								{
									temp.setValue(value.getValue());
									return true;
								}
							}
						}
					}
					item.getValue().addFirst(value);
					reverse.addFirst(new EntryStorage<K>(key, value));
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return true;
	}


	private synchronized Position<EntryStorage<K>> searchPos(K key, String field)
	{
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<EntryStorage<K>>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			Position<EntryStorage<K>> pos = reverseI.getCursor();
			entry = reverseI.next();
			if (entry.getKey().equals(key) && entry.getField().equals(field))
			{
				return pos;
			}
		}
		return null;
	}

	/**
	 * Find all the keys that match the field and value
	 * @param field the field you are searching
	 * @param value the value of the field
	 * @return a list of Key Value pairs
	 */
	public synchronized PositionList<EntryStorage<K>> search(String field, String value)
	{
		PositionList<EntryStorage<K>> foundList = new PositionList<>();
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<EntryStorage<K>>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			entry = reverseI.next();
			if (entry.getValue().equals(value) && entry.getField().equals(field))
			{
				foundList.addLast(entry);
			}
		}
		return foundList;
	}

	/**
	 * Find all the keys that match the value
	 * @param value the value of the field
	 * @return a list of Key Value pairs
	 */
	public synchronized PositionList<EntryStorage<K>> search(String value)
	{
		PositionList<EntryStorage<K>> foundList = new PositionList<>();
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			entry = reverseI.next();
			if (entry.getValue().equals(value))
			{
				foundList.addLast(entry);
			}
		}
		return foundList;
	}

	private int HashString(String key)
	{
		return key.hashCode() % TABLE_SIZE;
	}
}
