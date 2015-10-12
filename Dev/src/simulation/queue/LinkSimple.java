package simulation.queue;

/**
 * Created by Ismail on 12-10-2015.
 */
public class LinkSimple<V> {
    private V value;
    private LinkSimple<V> next;

    public LinkSimple<V> getNext(){
        return this.next;
    }

    public void setNext(LinkSimple<V> _next){
        this.next = _next;
    }

    public V getValue(){
        return this.value;
    }

    public void setValue(V _value){
        this.value = _value;
    }
}
