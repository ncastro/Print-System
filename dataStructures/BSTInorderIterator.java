package dataStructures;



public class BSTInorderIterator<K, V> implements Iterator<Entry<K, V>>{

	private static final long serialVersionUID = 1L;

	private BSTNode<K, V> root;
	private Stack<BSTNode<K, V>> mystack;

	public BSTInorderIterator(BSTNode<K, V> startNode){
		this.root = startNode;
		this.rewind();
	}

	public boolean hasNext() {
		return !mystack.isEmpty();
	}

	public Entry<K, V> next() throws NoSuchElementException {
		if(!this.hasNext())
			throw new NoSuchElementException();

		BSTNode<K, V> res = mystack.pop();
		BSTNode<K, V> aux = res.getRight();

		while(aux != null){
			mystack.push(aux);
			aux = aux.getLeft();
		}
		return res.getEntry();
	}

	public void rewind() {
		mystack = new StackInList<BSTNode<K, V>>();
		BSTNode<K, V> temp = root;
		while(temp != null){
			mystack.push(temp);
			temp = temp.getLeft();
		}

	}

}
