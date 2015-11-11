package simulation.queue;

import java.util.Comparator;

import algo.linkedlist.nodes.interfaces.ILinkSimple;
import algo.queue.interfaces.IPriorityQueue;

public class LinkedPriorityQueue<T> implements IPriorityQueue<T> {

	private ILinkSimple<T> node, queue;

	private Comparator<T> cmp;

	public LinkedPriorityQueue(Comparator<T> comparator) {
		this.cmp = comparator;
	}

	public void add(T value) {
		ILinkSimple<T> newNode = new LinkSimple<T>();
		newNode.setValue(value);
		if (node == null) {
			this.node = newNode;
			this.queue = newNode;
		} else {

			ILinkSimple<T> next = this.node;
			ILinkSimple<T> prev = new LinkSimple<T>();
			while (next != null && cmp.compare(value, next.getValue()) > 0) {
				prev = next;
				next = next.getNext();
			}

			if (next == null) {
				queue.setNext(newNode);
				queue = newNode;
			} else {
				if (prev.getValue() != null) {
					prev.setNext(newNode);
				} else {
					this.node = newNode;
				}
				newNode.setNext(next);
			}
		}
	}

	public T peek() {
		if (node != null)
			return node.getValue();
		else
			return null;
	}

	public T remove() {

		if (node != null) {
			T value = this.peek();

			ILinkSimple<T> next = this.node.getNext();
			this.node = next;

			return value;
		} else {
			return null;
		}
	}

}
