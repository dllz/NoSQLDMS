package db.interfaces.hash;

import db.data.structures.position.PositionList;
import db.models.hash.HashField;
/**
 * Created by danie on 2017/04/10.
 */

/**
 * The Map interface
 * @param <K> the type fo the key
 */
public interface IMap<K>
{
	public HashField remove(K key, String field);

	public HashField get(K key, String field);

	public PositionList<HashField> getAll(K key);

	public boolean put(K key, HashField hashField);
}
