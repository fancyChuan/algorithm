package lagou.model2;

import java.util.Arrays;

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

}
