package main.db.interfaces.hash;

import main.db.data.structures.position.PositionList;
import main.db.models.hash.HashField;

public interface IMap<K> {
	public HashField remove(K key, String field);
	public HashField get(K key, String field);
	public PositionList<HashField> getAll(K key);
	public void put(K key, HashField hashField);
}