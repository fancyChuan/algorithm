package lagou.common;

/**
 * [707] 设计链表
 *
 * 测试平台链接：
 * - https://leetcode-cn.com/problems/design-linked-list/
 */
class MyLinkedList {
    // 链表结点的定义
    class ListNode {
        // val用来存放链表中的数据
        public int val = 0;
        // next指向下一个结点
        public ListNode next = null;
        public ListNode() {}
        public ListNode(int x) {
            val = x;
        }
    }

    private ListNode dummy = new ListNode();
    private ListNode tail = dummy;
    private int length = 0;

    /**code here: 初始化链表*/
    public MyLinkedList() {
    }
    public void addAtTail(int val) {
        /* code here: 将值为 val 的结点追加到链表尾部*/
        tail.next = new ListNode(val);
        tail = tail.next;
        length++;
    }

    /**
     * 头插法：插入值val的新结点，使它成为链表的第一个结点
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        node.next = dummy.next;
        dummy.next = node;
        // 要特别注意修改tail指针
        if (tail == dummy) {
            tail = node;
        }
        length++;
    }

    /**
     * 获取链表中第index个结点的值。如果索引无效，则返回-1
     * index从0开始。
     * 注意：大多数情况下，返回指定结点前面的一个结点 prev 更加有用
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        ListNode prevNode = getPrevNode(index);
        return prevNode.next.val;
    }

    /**
     * 返回指定节点的前一个节点，如果index不存在，那么返回dummy
     */
    private ListNode getPrevNode(int index) {
        ListNode front = dummy.next;
        // 所需要的指定节点的前一个节点
        ListNode back = dummy;
        for (int i = 0; i < index && front != null; i++) {
            back = front;
            front = front.next;
        }
        return back;
    }

    /**
     * 在链表中的第 index 个结点之前添加值为 val  的结点。
     * 1. 如果 index 等于链表的长度，则该结点将附加到链表的末尾。
     * 2. 如果 index 大于链表长度，则不会插入结点。
     * 3. 如果index小于0，则在头
     */
    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        } else if (index == length) {
            addAtTail(val);
        } else if (index < 0) {
            addAtHead(val);
        } else {
            ListNode prevNode = getPrevNode(index);
            ListNode node = new ListNode(val);
            node.next = prevNode.next;
            prevNode.next = node;
            length ++;
        }
    }
    /**
     * 如果索引index有效，则删除链表中的第index个结点。
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        ListNode prevNode = getPrevNode(index);
        // 注意处理最后的指针
        if (prevNode.next == tail) {
            tail = prevNode;
        }
        prevNode.next = prevNode.next.next;
        length--;
    }
    /*
     * 思考题：如果在链表中进行查找的时候， 给定的并不是下标， 而是一个数 target， 或者是一个结点 ListNode target，
     * 应该如何正确地编写这个查找函数呢？
     *
     * 注意：给定的参数链表头head并不是一个假头
     */
    // 如果给定的不是下标，而是一个具体的数值。
    static private ListNode findNode(ListNode head, int target) {
        ListNode p = head;
        while (p != null) {
            if (p.val == target) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    // 如果给定的是一个ListNode *target
    static private ListNode findNode(ListNode head, ListNode target) {
        ListNode p = head;
        while (p != null && target != null) {
            if (p == target) {
                return p;
            }
            p = p.next;
        }
        return null;
    }
    // ------------ PART 2 -----------------------

    // 但是有时候，往往找到这个target之后，我们还需要执行一些操作。
    // 比如将这个target给删除掉的操作。那么，找到target的前面一个结点
    private ListNode getPrevNode(ListNode dummy, int target) {
        ListNode pre = dummy;
        ListNode p = dummy.next;

        while (p != null) {
            if (p.val == target) {
                return pre;
            }
            pre = p;
            p = p.next;
        }

        return pre;
    }

    // 当给定的链表不是一个带dummy head的链表的时候，我们改造成一个带dummy
    // head的链表再操作
    private ListNode deleteNode(ListNode head, int target) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode pre = getPrevNode(dummy, target);

        // 删除结点
        if (pre.next != null) {
            pre.next = pre.next.next;
        }

        return dummy.next;
    }
}
