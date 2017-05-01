package db.models.hash;

import db.data.structures.position.PositionList;

public class HashEntry<K> {
	private K key;
	private PositionList<HashField> value;
	
	public HashEntry(K key, PositionList<HashField> value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public PositionList<HashField> getValue() {
		return this.value;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public void setValue(PositionList<HashField> value) {
		this.value = value;
	}

	public String toString() {
		return key.toString() + "," + value.toString();
	}
	
}
