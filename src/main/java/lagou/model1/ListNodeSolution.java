package lagou.model1;

import lagou.common.ListNode;

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
        while (head != null) {
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
}
