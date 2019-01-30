package basecode;

/**
 * 编写递归代码时重要的三点：
 *      1. 递归总有一个最简单的情况——方法的第一条语句总是一个包含return的条件语句
 *      2. 递归调用总是去尝试解决一个规模更小的子问题，这样递归才能收敛到最简单的情况
 *      3. 递归调用的父问题和尝试解决的子问题之间不应该有交集
 *
 * 静态方法：不需要实例化就可以调用。
 * 静态方法库：可以理解为一个静态类，包含一系列的静态方法，比如Math。
 *      1. 系统标准库：java.lang.* 其中包含Math、Integer
 *      2. 导入的系统库：java.utils.Arrays;
 *
 */
public class staticfunction {

    // 判断一个数是否是素数
    public static boolean isPrime(int N) {
        if (N<2) {
            return false;
        }
        for (int i=2; i*i<=N; i++) {
            if (N % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 计算平方根

    // 计算直角三角形的斜边
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a*a + b*b);
    }

    // 计算调和级数
    public static double H(int N) {
        double sum = 0.0;;
        for (int i=1; i<=N; i++) {
            sum += 1.0 / i;
        }
        return sum;
    }

    // 二分查找的递归实现
    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length-1);
    }
    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo>hi) return -1;
        int mid = lo + (hi-lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid-1);
        else if (key>a[mid]) return rank(key, a, mid+1, hi);
        else return mid;
    }
}
