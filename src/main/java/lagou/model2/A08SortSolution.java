package lagou.model2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A08SortSolution {
    /**
     * 归并排序/合并排序
     */
    // 写一个递归函数，针对子数组排序
    private void msort(int[] arr, int start, int end, int tmp[]) {
        // 空区间，或者只有一个元素，那么不需要排序
        if (start >= end || start+1 >= end) {
            return;
        }
        // 找到中间位置，变为两半
        int mid = start + ((end - start)>>1);
        // 分别对左右两边排序
        msort(arr, start, mid, tmp);
        msort(arr, mid, end, tmp);
        // 拍完后，arr已经是左边有序，右边有序
        int i = start;
        int j = mid;
        int to = start;

        while (i<mid || j<end) {
            if (j>=end || i<mid && arr[i]<=arr[j]) {
                tmp[to++] = arr[i++];
            } else {
                tmp[to++] = arr[j++];
            }
        }
        // 把合并的结果写回原来的数组
        for (int k = start; k < end; k++) {
            arr[k] = tmp[k];
        }
        System.out.println(Arrays.toString(arr));
    }
    public void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        System.out.println(Arrays.toString(nums));
        int[] tmp = new int[nums.length];
        msort(nums, 0, nums.length, tmp);
    }
    /**
     * 例 1：逆序对
     * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/submissions/
     *
     * 归并排序的特点：
     *  1. j永远是大于i的。  对于这个题目来说，使用归并排序就变成了只要判断a[i]是否大于a[j]
     *  2. 在排序的过程中，有3个地方是有有序的。左子序列、右子序列、tmp[]中间结果集。
     *      对于这道题目来说，就是用到了tmp[]的结果是有序的。也就是说，在比较a[i]和a[j]的时候，[m,j)的数据已经排到tmp[]中了
     *      这样一来，a[i]就和[m,j)的所有元素都构成逆序对！
     */
    private int cnt1;
    private void m1Sort(int[] arr, int start, int end, int[] tmp) {
        if (start >= end || start+1 >= end) {
            return;
        }
        int mid = start + ((end - start)>>1);
        int i = start;
        int j = mid;
        int to = start;
        while (i < mid || j < end) {
            if (j>=end || i<mid && arr[i]<=arr[j]) {
                tmp[to++] = arr[i++];
                cnt1 += 1; // 最核心的就是在这个位置写的这一行代码！
            } else {
                tmp[to++] = arr[j++];
            }
        }
    }
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        cnt1 = 0;
        int[] tmp = new int[nums.length];
        m1Sort(nums, 0, nums.length, tmp);
        return cnt1;
    }


}
