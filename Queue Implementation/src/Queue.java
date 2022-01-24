public class Queue<E> implements IQueue<E>{

    public Queue() { }

    OneWayLinkedListWithHeadAndTail<E> lista = new OneWayLinkedListWithHeadAndTail<E>();

    @Override
    public void enqueue(E obj) {
        lista.add(obj);
    }

    @Override
    public E dequeue() {
    return lista.removeDequeue();
    }

    @Override
    public void clear() {
        lista.clear();
    }

    @Override
    public int size() {
        return lista.size();
    }

    @Override
    public boolean isEmpty() {
        return lista.isEmpty();
    }
}
