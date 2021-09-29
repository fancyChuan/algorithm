package nn2.injava.queue;

public interface MyQueue<E> {
    boolean enQueue(E e);
    E deQueue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
