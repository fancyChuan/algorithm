package basecode;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 典型的字符串处理代码
 *
 * int charAt(int i)                第i个字符
 * String substring(int i, int j)   j标识要截取的字符串的长度，hive的函数substr也是
 * hashCode()                       散列值
 *
 */
public class string {

    public static void main(String[] args) {
         // System.out.println("abcd".charAt(2)); // 索引从0开始
        StdOut.println(isPalindrome("abca"));


        // 从命令行中提取文件名和拓展名
        String s = args[0];
        int dot = s.indexOf(".");
        String base = s.substring(0, dot);
        String extension = s.substring(dot+1, s.length());
        System.out.println(base + ": " + extension);

        // 以空白字符为分隔符从StdIn中创建一个字符串数组
        String input = StdIn.readAll();
        String[] words = input.split("\\s+");


    }

    // 判断字符串是否一条回文
    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i=0; i<N/2; i++) {
            if (s.charAt(i) != s.charAt(N-1-i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSorted(String[] a) {
        for (int i=1; i<a.length; i++) {
            if (a[i-1].compareTo(a[i])>0) {
                return false;
            }
        }
        return true;
    }
}
