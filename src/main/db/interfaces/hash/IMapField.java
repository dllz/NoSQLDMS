package main.db.interfaces.hash;

import java.util.Iterator;

/**
 * Created by danie on 2017/04/10.
 */
public interface IMapField<F,V>
{
        public V remove(F field);
        public V get(F field);
        public void put(F field, V value);

        public Iterator<F> fields();
        public Iterator<V> values();

        public int size();
        public boolean isEmpty();
}
