package db.data.structures.hash;

import db.data.structures.hash.position.PositionList;
import java.nio.ByteBuffer;
import java.util.Iterator;

public class HashTable<K,V> implements IMap<K,V> {
	Object[] table;
	int size;
	int capacity;

	/**
	 * Default constructor
	 */
	public HashTable() {
		this(100);
	}
	
	/**
	 * Constructor - provides the size of the array
	 * @param initialSize the initial size
	 */
	public HashTable(int initialSize) {
		this.capacity = initialSize;
		this.table = createArray(this.capacity);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Create an array that contains the positionslists that act as buckets
	 * @param size the size of the array to create
	 * @return the array that was created
	 */
	private Object[] createArray(int size) {
		Object[] arr = new Object[size];
		//db.data.structures.hash.position.PositionList<Entry<K,V>>[] objArray = (db.data.structures.hash.position.PositionList<Entry<K,V>>[])arr;
		
		//Initialise the array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new PositionList<HashEntry<K,V>>();
		}
		return arr;
	}
	
	/**
	 * Hash a string input
	 * @param str The input string
	 * @return the hash code for the integer
	 */
	private long hash(String str) {
		return hash(str.getBytes());
	}
	
	/**
	 * A hash an integer input
	 * @param inputInt the input input
	 * @return the hash code for the integer
	 */
	private long hash(int inputInt) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(inputInt).array();
		return hash(bytes);
	}
	
	/**
	 * Calculate a hash code using the djb2 hash function
	 * This hash function was created by Dan Bernstein, however
	 * normally it works with string inputs, this has been modified
	 * to work with byte inputs
	 * @param input the input array of bytes
	 * @return a hash value for the input
	 */
	private long hash(byte[] input) {
		long hash = 5381;
		for (int i = 0; i < input.length; i++) {
			hash = ((hash << 5) + hash) + input[i];
		}
		return hash;
	}
	
	/**
	 * Calculate a hash for either a string or an Integer
	 * @param item the item to hash
	 * @return a compressed hash code for the item
	 */
	private long hash(K item) {
		if (item instanceof Integer) {
			return hash((Integer)item) % capacity;
		}
		
		if (item instanceof String) {
			return hash((String)item) % capacity;
		}
		
		return (long)item.hashCode() % capacity;
	}
	
	@Override
	/**
	 * Remove an item from the hash table
	 * @param key the key of the item to remove
	 * 10 marks ***********************************************
	 */
	public V remove(K key) {
		V res = null;
		for (int i = 0; i < table.length; i++) {
			PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[i];
			Iterator<HashEntry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				HashEntry<K,V> item = bucketIterator.next();
				if(item.getKey() == key) {
					res = item.getValue();
					bucket.remove(bucket.search(item));
				}
			}
		}
		return res;
	}

	@Override
	/**
	 * Get the value for a given key
	 * @param key the key for the item
	 * @returns the value for the associated key
	 * 10 marks ***********************************************
	 */
	public V get(K key) {
		V res = null;
		for (int i = 0; i < table.length; i++) {
			PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[i];
			Iterator<HashEntry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				HashEntry<K,V> item = bucketIterator.next();
				if(item.getKey() == key) {
					res = item.getValue();
				}
			}
		}
		return res;
	}

	@Override
	/**
	 * Put an item into the hash table
	 * @param key the key for the item (unique)
	 * @param value the value for the item
	 * 8 marks ***********************************************
	 */
	public void put(K key, V value) {
		if(get(key) == null)
		{
			PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[0];
			HashEntry<K, V> entry = new HashEntry<K, V>(key, value);
			bucket.addLast(entry);
		}
		else
		{
			for (int i = 0; i < table.length; i++) {
				PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[i];
				Iterator<HashEntry<K,V>> bucketIterator = bucket.iterator();
				while (bucketIterator.hasNext()) {
					HashEntry<K,V> item = bucketIterator.next();
					if(item.getKey() == key)
					{
						item.setValue(value);
					}
				}
			}
		}


	}

	@Override
	/**
	 * Returns an iterator over the keys of the hash table
	 * 6 marks ***********************************************
	 */
	public Iterator<K> keys() {
		PositionList<K> val = new PositionList<K>();
		for (int i = 0; i < table.length; i++) {
			PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[i];
			Iterator<HashEntry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				HashEntry<K,V> item = bucketIterator.next();
				val.addLast(item.getKey());
			}
		}
		return val.iterator();
	}

	@Override
	/**
	 * Returns an iterator over the values in the hash table
	 */
	public Iterator<V> values() {
		PositionList<V> val = new PositionList<V>();
		for (int i = 0; i < table.length; i++) {
			PositionList<HashEntry<K,V>> bucket = (PositionList<HashEntry<K,V>>)table[i];
			Iterator<HashEntry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				HashEntry<K,V> item = bucketIterator.next();
				val.addLast(item.getValue());
			}
		}
		
		return val.iterator();
	}

	@Override
	/**
	 * Returns the size of the hashtable
	 */
	public int size() {
		return this.size;
	}

	@Override
	/**
	 * Returns true if the hashtable is empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}
