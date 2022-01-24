public interface IQueue<E> {
    public void enqueue(E obj);
    public E dequeue() throws EmptyQueueException;
    public void clear();
    public int size();
    public boolean isEmpty();

}
