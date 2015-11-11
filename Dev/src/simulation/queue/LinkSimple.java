package simulation.queue;

import algo.linkedlist.nodes.interfaces.ILinkSimple;

public class LinkSimple<V> implements ILinkSimple<V> {

	private V value;
	private ILinkSimple<V> next;

	public ILinkSimple<V> getNext() {
		return next;
	}

	public V getValue() {
		return value;
	}

	public void setNext(ILinkSimple<V> link) {
		this.next = link;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
