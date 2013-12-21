package dataStructures;  

public class SepChainHashTable<K extends Comparable<K>, V> 
extends HashTable<K,V> 
{ 

	static final long serialVersionUID = 0L;


	// The array of dictionaries.
	protected Dictionary<K,V>[] table;


	@SuppressWarnings("unchecked")
	public SepChainHashTable( int capacity )
	{
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Dictionary<K,V>[]) new Dictionary[arraySize];
		for ( int i = 0; i < arraySize; i++ )
			table[i] = new OrderedDoublyLL<K,V>();
		maxSize = capacity;
		currentSize = 0;
	}                                      


	public SepChainHashTable( )
	{
		this(DEFAULT_CAPACITY);
	}                                                                


	// Returns the hash value of the specified key.
	protected int hash( K key )
	{
		return Math.abs( key.hashCode() ) % table.length;
	}


	// If there is an entry in the dictionary whose key is the specified key,
	// returns its value; otherwise, returns null.
	public V find( K key )
	{
		return table[ this.hash(key) ].find(key);
	}


	// If there is an entry in the dictionary whose key is the specified key,
	// replaces its value by the specified value and returns the old value;
	// otherwise, inserts the entry (key, value) and returns null.
	public V insert( K key, V value )
	{
		if ( this.isFull()) 
			this.rehash();
		if (find(key) == null) {
			table[this.hash(key)].insert(key, value);
			currentSize++;
			return null;
		}
		return table[this.hash(key)].insert(key, value);
	}


	// If there is an entry in the dictionary whose key is the specified key,
	// removes it from the dictionary and returns its value;
	// otherwise, returns null.
	public V remove( K key )
	{
		if (this.find(key) != null) {
			currentSize--;
			return table[this.hash(key)].remove(key);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void rehash () {
		int arraySize = HashTable.nextPrime((int) 2 * maxSize);
		Dictionary<K,V>[] oldTable = table;
		table = (Dictionary<K,V>[]) new Dictionary[arraySize];
		for ( int i = 0; i < arraySize; i++ )      	
			table[i] = new OrderedDoublyLL<K,V>();
		currentSize = 0;
		for(int i = 0; i < oldTable.length; i++)   
			if(oldTable[i] != null) {
				Iterator<Entry<K,V>> it = oldTable[i].iterator();
				while(it.hasNext()) {
					Entry<K,V> next = it.next();
					insert(next.getKey(),next.getValue());
				}
			}
		maxSize = 2 * maxSize;
	}

	// Returns an iterator of the entries in the dictionary.
	public Iterator<Entry<K,V>> iterator( )
	{
		return new SCHTIterator<K,V> (table);
	} 
}