package dataStructures;

public class EntryClass<K, V> implements Entry<K,V> {

	
	private static final long serialVersionUID = 0L;

K key;
V value;

public EntryClass(K key, V value){
	this.key=key;
	this.value=value;
}
	
	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		
		return this.key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
	
	public void setKey(K key){
		this.key=key;
	}
	
	public void setValue(V value){
		this.value=value;
	}


}
