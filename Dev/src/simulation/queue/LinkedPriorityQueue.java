package simulation.queue;

import java.util.Comparator;

/**
 * Created by Ismail on 12-10-2015.
 */
public class LinkedPriorityQueue<T> {

    private LinkSimple<T> node;
    private LinkSimple<T> queue;
    private Comparator<T> comparator;

    public LinkedPriorityQueue(Comparator<T> _comparator){
        this.comparator = _comparator;
    }

    public void add(T _value) {
        LinkSimple<T> newNode = new LinkSimple<T>();
        newNode.setValue(_value);

        if(this.node == null){
            this.node = newNode;
            this.queue = newNode;
        }
        else
        {
            LinkSimple<T> next = this.node;
            LinkSimple<T> current = new LinkSimple<T>();

            while (next != null && comparator.compare(_value, next.getValue()) > 0) {
                current = next;
                next = next.getNext();
            }

            if(next == null){
                this.queue.setNext(newNode);
                this.queue = newNode;
            }
            else
            {
                if(current.getValue() != null)
                    current.setNext(newNode);
                else
                    this.node = newNode;

                newNode.setNext(next);
            }
        }
    }

    public T peek(){
        return this.node != null ? node.getValue() : null;
    }

    public T remove(){
        if(this.node == null)
            return null;

        T value = this.peek();

        LinkSimple<T> next = this.node.getNext();
        this.node = next;

        return  value;
    }
}
