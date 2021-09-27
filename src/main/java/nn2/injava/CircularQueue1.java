package nn2.injava;

import nn2.injava.queue.MyQueue;

/**
 * 循环队列的实现
 *  使用了used来代表已使用的空间
 */
public class CircularQueue1 {
    private int used = 0;
    private int front = 0;
    private int rear = 0;
    private int[] a;
    private int capacity = 0;

    // 参数k表示这个循环队列最多只能容纳k个元素
    public CircularQueue1(int k) {
        capacity = k;
        a = new int[capacity];
    }

    // 将value放到队列中, 成功返回true
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        a[rear] = value;
        rear = (rear + 1) % capacity;
        used ++;
        return true;
    }
    // 删除队首元素，成功返回true
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1 + capacity) % capacity;
        used --;
        return true;
    }
    // 得到队首元素，如果为空，返回-1
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        front = (front - 1 + capacity) % capacity;
        return a[front];
    }
    // 得到队尾元素，如果队列为空，返回-1
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int tail = (rear - 1 + capacity) % capacity;
        return a[tail];
    }

    public int getSize() {
        return used;
    }

    // 看一下循环队列是否为空
    public boolean isEmpty() {
        return used == 0;
    }
    // 看一下循环队列是否已放满k个元素
    public boolean isFull() {
        return used == capacity;
    }
}