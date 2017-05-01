package db.models.hash;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by danie on 2017/04/10.
 */
@XmlRootElement
public class HashField extends Object
{
        private String field;
        private String value;

        public HashField(String field, String value) {
            this.field = field;
            this.value = value;
        }

    public HashField()
    {
    }

    public String getField() {
            return field;
        }

        public String getValue() {
            return this.value;
        }

        public void setKey(String field) {
            this.field = field;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString() {
            return field + "," + value.toString();
        }

        public boolean equal(HashField object)
        {
            if(object.getField().equals(this.getField()) & object.getValue().equals(this.getValue()))
            {
                return true;
            }
            return false;
        }
}


