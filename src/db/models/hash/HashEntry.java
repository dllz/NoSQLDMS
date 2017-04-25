package db.models.hash;

public class HashEntry<K,V> {
	private K key;
	private V value;
	
	public HashEntry(K key, V value) {
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

	public String toString() {
		return key.toString() + "," + value.toString();
	}
	
}
