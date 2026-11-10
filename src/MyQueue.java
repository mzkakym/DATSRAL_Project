public class MyQueue<T> implements QueueInterface<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int front;
    private int count;
    private int capacity;

    public MyQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MyQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.count = 0;
    }

    @Override
    public void offer(T item) {
        if (isFull()) {
            grow();
        }
        int rear = (front + count) % capacity;
        data[rear] = item;
        count++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T item = (T) data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        count--;
        return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T front() {
        if (isEmpty()) {
            return null;
        }
        return (T) data[front];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T back() {
        if (isEmpty()) {
            return null;
        }
        int lastIndex = (front + count - 1) % capacity;
        return (T) data[lastIndex];
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return count == capacity;
    }

    private void grow() {
        int newCapacity = capacity * 2;
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < count; i++) {
            newData[i] = data[(front + i) % capacity];
        }
        data = newData;
        front = 0;
        capacity = newCapacity;
    }
}
