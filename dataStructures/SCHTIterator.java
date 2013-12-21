package dataStructures;

public class SCHTIterator<K,V> implements Iterator<Entry<K,V>> {

	private static final long serialVersionUID = 0L;

	private Dictionary<K,V>[] table;
	private List<Entry<K,V>> allElements;
	private Iterator<Entry<K,V>> listElements;
	private int counter;

	public SCHTIterator (Dictionary<K,V>[] table) {
		this.table = table;
		counter = 0;
		allElements = new DoublyLinkedList<Entry<K,V>>();
		rewind();
	}

	@Override
	public boolean hasNext() {
		if (allElements.isEmpty())
			return false;
		if(allElements.size() == counter)
			return false;
		return true;
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		if (allElements.isEmpty())
			throw new NoSuchElementException();
		if (hasNext()) {
			return allElements.get(counter++);
		}
		return null;
	}

	@Override
	public void rewind() {
		counter = 0;
		int j;
		for (int i=0; i< table.length; i++) {
			if (!table[i].isEmpty()) {
				listElements = table[i].iterator();
				j = 0;
				while (listElements.hasNext()) {
					allElements.add(j, listElements.next());
					j++;
				}
			}
		}
	}

}
