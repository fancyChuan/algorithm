package basecode;

/**
 * 20190129 经典的数组处理代码
 *
 *
 */
public class array {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 5, 4, 0};

        // 找出最大元素
        int max = a[0];
        for (int i=1; i<a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        // 计算数组的平均值
        int sum = 0;
        for (int i: a) {sum += i;}
        double avg = sum / a.length;

        // 复制数组
        int[] b = new int[a.length];
        for (int i=0; i<a.length; i++) {
            b[i] = a[i];
        }

        // 颠倒数组顺序
        for (int i=0; i<a.length/2; i++) {
            int tmp = a[i];
            a[i] = a[a.length-i-1];
            a[a.length-i-1] = tmp;
        }

        // 矩阵相乘 a[][] * b[][] = c[][]
        int N = 5;
        int[][] aa = new int[N][N];
        int[][] bb = new int[N][N];
        int[][] cc = new int[N][N];
        for (int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                //计算行i与列j的点乘
                for (int k=0; k<N; k++) {
                    cc[i][j] += aa[i][k] * bb[k][j];
                }
            }
        }
    }
}
