package db.data.structures.hash;

import db.data.structures.hash.position.PositionList;
import db.data.structures.hash.position.PositionListIterator;
import db.interfaces.hash.IMap;
import db.interfaces.hash.position.Position;
import db.models.hash.EntryStorage;
import db.models.hash.HashEntry;
import db.models.hash.HashField;

import java.util.Iterator;

public class HashTable<K> implements IMap<K>
{
	private PositionList<HashEntry<K,PositionList<HashField>>> map;
	private PositionList<EntryStorage<K>> reverse;
	/**
	 * Default constructor
	 */
	public HashTable() {
		map	= new PositionList<HashEntry<K,PositionList<HashField>>>();
		reverse = new PositionList<EntryStorage<K>>();
	}


	/**
	 * Remove an item from the hash table
	 */
	public synchronized HashField remove(K key, String field) {
		Iterator<HashEntry<K,PositionList<HashField>>> keyIterator = map.iterator();
		HashEntry<K,PositionList<HashField>> item;
		while (keyIterator.hasNext()) {
			item = keyIterator.next();
			if(item.getKey().equals(key))
			{
				Iterator<HashField> bucketIterator = item.getValue().iterator();
				HashField value;
				while(bucketIterator.hasNext())
				{
					value = bucketIterator.next();
					if(value.getField().equals(field))
					{
						bucketIterator.remove();
						reverse.remove(searchPos(key, field));
						return value;
					}
					value = null;
				}
			}
			item = null;
		}
		return null;
	}

	/**
	 * Get the value for a given key
	 * @param key the key for the item
	 * @returns the value for the associated key
	 */
	public synchronized HashField get(K key, String field) {
		Iterator<HashEntry<K,PositionList<HashField>>> keyIterator = map.iterator();
		HashEntry<K,PositionList<HashField>> item;
		while (keyIterator.hasNext()) {
			item = keyIterator.next();
			if(item.getKey().equals(key))
			{
				Iterator<HashField> bucketIterator = item.getValue().iterator();
				HashField value;
				while(bucketIterator.hasNext())
				{
					value = bucketIterator.next();
					if(value.getField().equals(field))
					{
						return value;
					}
					value = null;
				}
			}
			item = null;
		}
		return null;
	}

	public synchronized PositionList<HashField> getAll(K key)
	{
		Iterator<HashEntry<K,PositionList<HashField>>> keyIterator = map.iterator();
		HashEntry<K,PositionList<HashField>> item;
		while (keyIterator.hasNext()) {
			item = keyIterator.next();
			if(item.getKey().equals(key))
			{
				return item.getValue();
			}
			item = null;
		}
		return null;
	}

	/**
	 * Put an item into the hash table
	 * @param key the key for the item (unique)
	 * @param value the value for the item
	 */
	public synchronized void put(K key, HashField value) {
		if(getAll(key) == null)
		{
			PositionList<HashField> temp = new PositionList<HashField>(value);
			map.addLast(new HashEntry(key, temp));
		}
		else
		{
			Iterator<HashEntry<K,PositionList<HashField>>> keyIterator = map.iterator();
			HashEntry<K,PositionList<HashField>> item;
			while (keyIterator.hasNext()) {
				item = keyIterator.next();
				if(item.getKey().equals(key))
				{
					Iterator<HashField> bucketIterator = item.getValue().iterator();
					HashField pos;
					while(bucketIterator.hasNext())
					{
						pos = bucketIterator.next();
						if(pos.equal(value))
						{
							pos.setValue(value.getValue());
							reverse.addFirst(new EntryStorage<K>(key, value));
							return;
						}
						pos = null;
					}
					item.getValue().addFirst(value);
				}
				item = null;
			}
		}


	}


	public synchronized Position<EntryStorage<K>> searchPos(K key, String field)
	{
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<EntryStorage<K>>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			Position pos = reverseI.getCursor();
			entry = reverseI.next();
			if(entry.getKey().equals(key) && entry.getField().equals(field))
			{
				return pos;
			}
		}
		return null;
	}

	public synchronized PositionList<K> search (String field, Object value)
	{
		PositionList<K> foundList = new PositionList<K>();
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<EntryStorage<K>>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			entry = reverseI.next();
			if(entry.getValue().equals(value) && entry.getField().equals(field))
			{
				foundList.addLast(entry.getKey());
			}
		}
		return foundList;
	}

	public synchronized PositionList<K> search (Object value)
	{
		PositionList<K> foundList = new PositionList<K>();
		PositionListIterator<EntryStorage<K>> reverseI = new PositionListIterator<EntryStorage<K>>(reverse);
		EntryStorage<K> entry;
		while (reverseI.hasNext())
		{
			entry = reverseI.next();
			if(entry.getValue().equals(value))
			{
				foundList.addLast(entry.getKey());
			}
		}
		return foundList;
	}


}
