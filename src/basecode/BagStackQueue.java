package basecode;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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
}
