package nn2.injava;

/**
 * 循环队列的实现
 *  使用了used来代表已使用的空间
 * 理解：
 *  1. 队列是一种逻辑上的规定，只允许某种操作。比如这个类中，使用了 int[] 数组
 *     int[]是可以通过下标来随机读取和更新的，但是顺序队列/循环队列都规定了只能从队头取元素，也就是“先进先出”
 *  2. 对于顺序队列来说，随着front的不断“前进”，int[]的前面几个空间是得不到利用的，并且当rear=capacity的时候就出现了“假溢出”
 *  3. 队头出元素了之后，int[]实际上还是存了元素的，这是规定了按front读取，而front只能往前加
 *  4. 在实际上，顺序队列用得非常少，绝大部分都会使用循环队列
 */
public class CircularQueue1 {
    private int used = 0;
    // 指向队头元素
    private int front = 0;
    // 指向队尾元素的下一个位置
    private int rear = 0;
    private int[] a;
    private int capacity = 0;

    // 参数k表示这个循环队列最多只能容纳k个元素
    public CircularQueue1(int k) {
        capacity = k;
        a = new int[capacity];
    }

    // 将value放到队列中, 成功返回true。放在队尾
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
    // 注意这个方法不会让元素出队列，所以指针front和rear是不变的
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