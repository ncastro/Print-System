package dataStructures;

public class OrderedDoublyLL <K extends Comparable<K>, V> 
implements OrderedDictionary<K,V> {

	static final long serialVersionUID = 0L;

	protected int currentSize;
	protected DListNode<Entry<K,V>> head;
	protected DListNode<Entry<K,V>> tail;
	protected DListNode<Entry<K,V>> lastNode ;

	public OrderedDoublyLL () {
		currentSize = 0;
		head = null;
		tail = null;
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if ( this.isEmpty() )
			throw new EmptyListException();

		return tail.getElement();
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if ( this.isEmpty() )
			throw new EmptyListException();

		return head.getElement();
	}

	protected DListNode<Entry<K,V>> findNode(K key) throws EmptyListException {

		lastNode = head;
		DListNode<Entry<K,V>> node = head;

		while ( node != null && key.compareTo(node.getElement().getKey()) > 0 ) {
			node = node.getNext();
			if (node != null)
				lastNode = node;
		}

		if (node == null)
			return null;

		else if ( key.compareTo(node.getElement().getKey()) == 0 )
			return node;
		else
			return null;
	}

	@Override
	public V find(K key) {
		DListNode<Entry<K,V>> node = findNode(key);
		if (node == null)
			return null;
		return node.getElement().getValue();
	}

	@Override
	public V insert (K key, V value) {
		Entry<K,V> newEntry = new EntryClass<K,V> (key, value);
		if (isEmpty()) {
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, null, null);
			head = newNode;
			tail = newNode;
			currentSize++;
			return null;
		}
		DListNode<Entry<K,V>> node = head;
		
		if (node == null) {
			if (lastNode.getElement().getKey().compareTo(tail.getElement().getKey()) == 0) 
				insertAfterTail(key, newEntry);
			else if (lastNode == null)
				insertBeforeHead(key, newEntry);
			else 
				insertMiddleNode(key, newEntry);
			return null;
		}
		else {
			if (node.getElement().getKey().compareTo(head.getElement().getKey()) == 0)
				return replaceHead(key, newEntry);
			else if (node.getElement().getKey().compareTo(tail.getElement().getKey()) == 0)
				return replaceTail(key, newEntry);
			else
				return replaceMiddleNode(key, node, newEntry);
		}
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}

	@Override
	public V remove(K key) {
		if (this.isEmpty())
			throw new EmptyListException();
		DListNode<Entry<K,V>> node = findNode(key);
		if (node == null) // the node doesn't exist in the List
			return null;
		else {
			if (node.getElement().getKey().compareTo(head.getElement().getKey()) == 0)  // check if it's removing the head
				return removeHead(key);
			else if (node.getElement().getKey().compareTo(tail.getElement().getKey()) == 0)     // check it it's removing the tail
				return removeTail(key);
			else { 
				return removeMiddleNode (key, node);
			}
		}	
	}

	protected V removeMiddleNode (K key, DListNode<Entry<K,V>> node) {
		node.getPrevious().setNext(node.getNext());
		node.getNext().setPrevious(node.getPrevious());
		currentSize--;
		return node.getElement().getValue();
	}

	protected V removeHead (K key) {
		DListNode<Entry<K,V>> oldHead = head;
		head = head.getNext();
		if ( head == null )
			tail = null;
		else
			head.setPrevious(null);
		currentSize--;
		return oldHead.getElement().getValue();
	}

	protected V removeTail (K key) {
		DListNode<Entry<K,V>> oldTail = tail;
		tail = tail.getPrevious();
		if ( tail == null )
			head = null;
		else
			tail.setNext(null);
		currentSize--;
		return oldTail.getElement().getValue();
	}


	/*
	 * This method will replace the actual Head node for a new one, since their keys 
	 * are the same
	 */
	protected V replaceHead (K key, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> oldHead = head;
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, null, head.getNext());
		head = newNode;
		return oldHead.getElement().getValue();
	}

	/*
	 * This method will replace the actual Tail node for a new one, since their keys 
	 * are the same
	 */
	protected V replaceTail (K key, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, tail.getPrevious(), tail.getNext());
		tail.getPrevious().setNext(newNode);
		DListNode<Entry<K,V>> oldTail = tail;
		tail = newNode;
		return oldTail.getElement().getValue();

	}

	/*
	 * This method will insert a node after the actual tail. this node will be
	 * the new Tail.
	 */
	protected void insertAfterTail (K key, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, tail, null);
		tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	/*
	 * Inserts a node before the head, which will be the new head
	 */
	protected void insertBeforeHead (K key, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, null, head);
		head = newNode;
		currentSize++;
	}

	/*
	 * Inserts a node between two other nodes. None of those 2 nodes are the tail nor the head.
	 */
	protected void insertMiddleNode (K key, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, lastNode.getPrevious(), lastNode);
		lastNode.getPrevious().setNext(newNode);
		lastNode.setPrevious(newNode);
		currentSize++;
	}

	/*
	 * This method will replace a node that is not the head nor the tail. The node was found
	 * by using findNode and is the argument 'aNode', the node to be replaced. aNode is going
	 * to be replaced since their keys are the same.
	 */
	protected V replaceMiddleNode (K key, DListNode<Entry<K,V>> aNode, Entry<K,V> newEntry) {
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>>(newEntry, aNode.getPrevious(), aNode.getNext());
		aNode.getPrevious().setNext(newNode);
		aNode.getNext().setPrevious(newNode);
		return aNode.getElement().getValue();
	}

	@Override
	public int size() {
		return this.currentSize;
	}
}
