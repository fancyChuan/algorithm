package basecode;

import edu.princeton.cs.algs4.*;

/**
 * 背包、栈、队列的用例
 */
public class BagStackQueue {

    public static void main(String[] args) {
        bagCase();
    }

    public static void bagCase() {
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        StdOut.println("this bag has " + numbers.size() + "items");
    }

    public static int[] queueCase(String name) {
        In in = new In(name);
        Queue<Integer> q = new Queue<Integer>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }
        int N = q.size();
        int[] a = new int[N];
        for (int i=0; i<N; i++) {
            a[i] = q.dequeue();
        }
        return a;
    }

    public static void stackCase() {
        Stack<Integer> stack = new Stack<Integer>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }
        for (int i: stack) {
            StdOut.println(i);
        }
    }
}
