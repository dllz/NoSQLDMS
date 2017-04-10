package db.data.structures.hash;

import java.util.Iterator;

public interface IMap<K, V> {
	public V remove(K key);
	public V get(K key);
	public void put(K key, V value);
	
	public Iterator<K> keys();
	public Iterator<V> values();
	
	public int size();
	public boolean isEmpty();
}
