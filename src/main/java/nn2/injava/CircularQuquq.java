package nn2.injava;

import nn2.injava.queue.MyQueue;

public class CircularQuquq<E> implements MyQueue<E> {
    // 使用最原始的数组，而不是Array，因为需要自己控制front和rear
    private E[] data;
    private int front = 0, rear = 0;
    private int capacity;

    public CircularQuquq(int capacity) {
        this.capacity = capacity;
        data = (E[]) (new Object[this.capacity]);
    }

    @Override
    public boolean enQueue(E e) {
        return false;
    }

    @Override
    public E deQueue() {
        return null;
    }

    @Override
    public E getFront() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
