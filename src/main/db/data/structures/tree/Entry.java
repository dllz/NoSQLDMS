package main.db.data.structures.tree;

public class Entry<K extends Comparable<? super K>,V> implements Comparable<K> {
	private K key;
	private V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return this.value;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(V value) {
		this.value = value;
	}

	public int compareTo(Entry<K, V> entry) {
		return this.compareTo(entry.getKey());
	}


	public int compareTo(K o) {
		return key.compareTo(o);
	}
	
	public String toString() {
		return key.toString() + "," + value.toString();
	}
	
}
