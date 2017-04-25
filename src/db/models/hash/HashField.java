package db.models.hash;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by danie on 2017/04/10.
 */
@XmlRootElement
public class HashField<V> extends Object
{
        private String field;
        private V value;

        public HashField(String field, V value) {
            this.field = field;
            this.value = value;
        }

    public HashField()
    {
    }

    public String getField() {
            return field;
        }

        public V getValue() {
            return this.value;
        }

        public void setKey(String field) {
            this.field = field;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public String toString() {
            return field + "," + value.toString();
        }

        public boolean equal(HashField<V> object)
        {
            if(object.getField().equals(this.getField()) & object.getValue().equals(this.getValue()))
            {
                return true;
            }
            return false;
        }
}


