package lagou.common;

public class ListNode {
    // val用来存放链表中的数据
    public int val = 0;
    // next指向下一个结点
    public ListNode next = null;
    public ListNode() {}
    public ListNode(int x) {
        val = x;
    }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder("" + val);
        while (next != null) {
            content.append(" -> ").append(next.val);
            next = next.next;
        }
        return content.toString();
    }
}
