package db.interfaces.hash;

import db.data.structures.position.PositionList;
import db.models.hash.HashField;

public interface IMap<K> {
	public HashField remove(K key, String field);
	public HashField get(K key, String field);
	public PositionList<HashField> getAll(K key);
	public boolean put(K key, HashField hashField);
}
