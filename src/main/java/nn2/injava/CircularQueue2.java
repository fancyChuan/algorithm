package nn2.injava;

/**
 * 循环队列的实现
 *  不使用了used来代表已使用的空间，仅仅使用front和rear两个
 */
public class CircularQueue2 {
    private int front = 0;
    private int rear = 0;
    private int[] a;
    private int capacity = 0;

    // 参数k表示这个循环队列最多只能容纳k个元素
    public CircularQueue2(int k) {
        capacity = k + 1;
        a = new int[capacity + 1];
    }

    // 将value放到队列中, 成功返回true
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        a[rear] = value;
        rear = (rear + 1) % capacity;
        return true;
    }
    // 删除队首元素，成功返回true
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1 + capacity) % capacity;
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

    public int getSize() {// todo; 待验证
        return (rear - front + capacity) % capacity;
    }

    // 看一下循环队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }
    // 看一下循环队列是否已放满k个元素
    public boolean isFull() {
        // rear与front之间至少有一个空格
        // 当rear指向这个最后的一个空格时，
        // 队列就已经放满了!
        return (rear + 1) % capacity == front;
    }
}