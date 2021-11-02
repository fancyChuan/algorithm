package lagou.model1;

import lagou.common.ListNode;

import java.util.PriorityQueue;

public class ListNodeSolution {

    /**
     * 例 1：链表反转
     * 解法：新链表+头插法
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();
        while (head != null) {
            // 当前节点的后序节点要保持住
            ListNode leftList = head.next;
            // 要把head节点弄到新链上
            head.next = dummy.next; // 这个时候head和leftList就断开了
            dummy.next = head;
            head = leftList;
        }
        // 返回新链表的头，注意，不要返回dummy!!
        return dummy.next;
    }

    /**
     * 例 2：删除结点
     * 【题目】给定一个链表头及一个整数值，要求把链表里面等于整数值的结点都从链表中移除出去。
     *
     * 输入：1->2->3->2->4, remove = 2
     *
     * 输出：1->3->4。
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (head != null) {// 这里如果为了不修改head这条链，那最好 p = head，然后遍历p来保留head的完整信息
            ListNode temp = head.next;
            if (head.val != val) {
                head.next = null;
                tail.next = head;
                tail = head;
            }
            head = temp;
        }
        return dummy.next;
    }
    /**
     * 练习题 1：给定一个排序链表，删除重复出现的元素，使得每个元素只出现一次。
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        // 不直接处理head，而是增加一个新的对象来遍历
        ListNode p = head;
        while (p != null) {
            ListNode temp = p.next;
            // TODO: 为什么还需要有 tail == dummy这个条件？
            if (p.val != tail.val || tail == dummy) {
                tail.next = p;
                tail = tail.next;
            }
            p = temp;
        }
        tail.next = null;
        return dummy.next;
    }
    /**
     * 练习题 2：给定一个排序链表，删除重复出现的元素，只留下没有重复出现的元素。
     * 输入：1->1->2->3->3
     * 输出：2
     *
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/description/
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        // 用来存放最后结果的链表
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        // 用来存放相同节点的链表
        ListNode tmp_dummy = new ListNode();
        ListNode tmp_tail = tmp_dummy;

        ListNode p = head;

        while (p != null) {
            ListNode back = p.next;
            // 第一个元素和重复的元素都存在到tmp_dummy中
            if (tmp_tail == tmp_dummy || p.val == tmp_tail.val) {
                tmp_tail.next = p;
                tmp_tail = tmp_tail.next;
            } else {
                // 当发现不重复时
                // 如果只有一个元素，那也写入dummy
                if (tmp_dummy.next == tmp_tail) {
                    tail.next = tmp_tail;
                    tail = tail.next;
                }
                // 如果tmp链表中有多个结点，那么什么也不做

                // 无论tmp链表中是一个结点，还是有多个结点
                // 都要清空tmp链表
                tmp_tail = tmp_dummy;
                tmp_dummy.next = null;

                // 再把p结点安装到tmp上
                // 可以和前面的语句合并
                // 这里为了逻辑更清晰这么写。
                tmp_tail.next = p;
                tmp_tail = p;
            }
            p = back;
        }
        // 全部遍历完了，如果tmp链表中还有一个元素（说明不重复），那也要加入
        if (tmp_dummy.next == tmp_tail) {
            tail.next = tmp_tail;
            tail = tail.next;
        }
        tail.next = null;
        return dummy.next;
    }

    /**
     * 例 3 ：合并两个有序链表
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        ListNode A = l1;
        ListNode B = l2;
        while (A != null || B != null) {
            if (B == null || (A != null && A.val <= B.val)) {
                tail.next = A;
                tail = tail.next;
                // A指针移动到下一个
                A = A.next;
            } else {
                tail.next = B;
                tail = tail.next;
                B = B.next;
            }
        }

        tail.next = null;
        return dummy.next;
    }
    /**
     * 练习题 3：给定 k 个有序链表，合并成一个有序链表
     * [23] 合并K个升序链表
     *
     *  https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
     */
    public ListNode mergeKLists(ListNode[] A) {
        int N = A!=null ? A.length : 0;
        PriorityQueue<ListNode> Q = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            if (A[i] != null) {
                Q.offer(A[i]);
            }
        }
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (!Q.isEmpty()) {
            ListNode cur = Q.poll();

            tail.next = cur;
            tail = cur;

            if (cur.next != null) {
                Q.add(cur.next);
            }
        }

        tail.next = null;
        return dummy.next;
    }
    /**
     * 例 4：交换链表中的结点
     * 【题目】给定一个链表，需要将里面的结点两两交换。
     *
     * 输入：[1->2->3->4->5->6]
     *
     * 输出：[2->1->4->3->6->5]
     */
    private ListNode merge(ListNode odd, ListNode even) {
        // todo
        return null;
    }
}
