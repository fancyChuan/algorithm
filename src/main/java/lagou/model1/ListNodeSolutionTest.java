package lagou.model1;

import lagou.common.ListNode;
import org.junit.Test;

public class ListNodeSolutionTest {

    @Test
    public void testDeleteDuplicates2() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        System.out.println(head);

        System.out.println(ListNodeSolution.deleteDuplicates2(head));
    }
}
