package basecode;

import edu.princeton.cs.algs4.*;

/**
 * 背包、栈、队列的用例
 *
 * 注意StdIn是用了Scanner，输入结束时应该用Ctrl-D或者Ctrl-Z，而不是回车
 */
public class BagStackQueue {

    public static void main(String[] args) {
        // bagCase();
        // queueCase("doc/FIFO.txt");
        // stackCase();
        testEvaluate();
    }

    public static void bagCase() {
        Bag<Double> numbers = new Bag<Double>();
        while (!StdIn.isEmpty()) {
            numbers.add(StdIn.readDouble());
        }
        StdOut.println("this bag has " + numbers.size() + " items");
    }

    /**
     * new In(String name)
     *      其实等于：   File file = new File(name);
     *                 Scanner scanner = new Scanner(new BufferedInputStream(new InputStream(file)), "UTF-8")
     */
    public static void queueCase(String name) {
        In in = new In(name); // 读取一个文件作为输入流
        Queue<Integer> q = new Queue<Integer>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }
        int N = q.size();
        int[] a = new int[N];
        for (int i=0; i<N; i++) {
            a[i] = q.dequeue();
        }
        System.out.println("队列的元素分别为：");
        for (int i: a) {
            System.out.println(i);
        }
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

    // Dijkstra的双栈算术表达式求值算法
    public static void testEvaluate() {
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else {
                vals.push(Double.parseDouble(s));
            }
            StdOut.println(vals.pop());
        }
    }
}
