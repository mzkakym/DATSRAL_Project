public interface QueueInterface<T> {
    public void offer(T item);
    public T poll();
    public T front();
    public T back();
    public int size();
    public boolean isEmpty();

    public boolean isFull();
}
