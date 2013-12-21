package dataStructures;

import java.io.Serializable;

public interface Entry<K,V> extends Serializable
{

    // Returns the key in the entry.
    K getKey( );

    // Returns the value in the entry.
    V getValue( );
    
    // muda o valor da key
    void setKey(K key);
    
    //muda o valor de value
    void setValue(V value);

}
