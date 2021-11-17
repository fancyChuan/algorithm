package lagou.model2;

import lagou.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A08SortSolution {
    /**
     * 归并排序/合并排序
     *  1. 拆分数组（找到mid）
     *  2. 合并两个子序列[start,mid) [mid, end) 到tmp[]
     *  3. 将tmp[]的内容覆盖写入arr。千万注意k的起始和终止位置分别为[start,end)
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
        // > 在递归的过程中始终会保持只覆盖参与本次递归的元素！
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
     * 思考题：给定一个链表，如何排序，使其时间复杂度能够达到 O(NlogN)。空间复杂度为 O(1)
     *      https://leetcode-cn.com/problems/sort-list/description/
     * 关键点：
     *  1. 找到链表的中间节点：使用快慢双指针
     *  2. 拆成两个链表的时候，注意断开！（也就是要在middle.next=null）
     *  3. 使用空头链表，作为合并结果
     *
     * 注意链表的归并排序和数组的归并排序的对比：
     *  1. 中间节点：链表要用快慢指针，数组int m = start + ((end - start)>>1)
     *  2. 归并中间结果：链表使用空头新链表，并返回。而数组使用tmp[]，不返回，但通过覆盖写入arr的方式
     *  3. 稳定排序：链表通过next指针是否为空来判断，而数组通过下标是否超过[start,mid) 和 [mid,end)来判断
     */
    // 找中间节点
    private ListNode findMiddle(ListNode head) {
        ListNode s1 = head;
        ListNode s2 = head;
        ListNode pre = head;
        while (s2 != null && s2.next != null) {
            pre = s1;
            s1 = s1.next;
            s2 = s2.next.next;
        }
        // 注意奇偶个数的链表的不同情况判断
        return s2 != null ? s1 : pre;
    }
    // 递归排序的方法
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到中间节点之后，需要拆成2个链表。因此要注意通过 middle.next=null 与back断开
        ListNode middle = findMiddle(head);
        ListNode back = middle.next;
        middle.next = null;
        // 递归排序
        ListNode left = mergeSort(head);
        ListNode right = mergeSort(back);
        // 进行合并，使用空头链表
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (left != null || right != null) {
            if (right == null || left != null && left.val <= right.val) {
                tail.next = left;
                tail = left;
                left = left.next;
            } else {
                tail.next = right;
                tail = right;
                right = right.next;
            }
        }
        tail.next = null; // TODO：这行代码是否有必要？
        // 返回合并后的结果
        return dummy.next;
    }
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
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
        m1Sort(arr, start, mid, tmp);
        m1Sort(arr, mid, end, tmp);

        int i = start;
        int j = mid;
        int to = start;
        while (i < mid || j < end) {
            if (j>=end || i<mid && arr[i]<=arr[j]) {
                tmp[to++] = arr[i++];
                cnt1 += (j-mid); // 最核心的就是在这个位置写的这一行代码！
            } else {
                tmp[to++] = arr[j++];
            }
        }
        for (int k = start; k < end; k++) {
            arr[k] = tmp[k];
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
    /**
     * 例1 扩展题 [315] 计算右侧小于当前元素的个数
     *  https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/description/
     */
    private void m2Sort(int[] nums, int start, int end, int[] tmp) {
        if (start >= end || start+1 >= end) {
            return;
        }
        int m = start + ()
    }
    public List<Integer> countSmaller(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();


        return ans;
    }

}
