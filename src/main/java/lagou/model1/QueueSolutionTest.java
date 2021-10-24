package lagou.model1;

import lagou.common.Node;
import lagou.common.TreeNode;
import org.junit.Test;

public class QueueSolutionTest {

    @Test
    public void testLevelOrder() {
        TreeNode tree = new TreeNode(3, new TreeNode(9, null, new TreeNode(4)), new TreeNode(8, new TreeNode(6), new TreeNode(7)));
        System.out.println(QueueSolution.levelOrder(tree));
    }

    @Test
    public void testLevelOrder2() {
        TreeNode tree = new TreeNode(3, new TreeNode(9, null, new TreeNode(4)), new TreeNode(8, new TreeNode(6), new TreeNode(7)));
        System.out.println(QueueSolution.levelOrder2(tree));
    }
    
    @Test
    public void testLevelWithNext() {
        // 使用next指针来指向右边节点

    }

    @Test
    public void testMaxSlidingWindow() {
        QueueSolution.maxSlidingWindow(new int[] {1,3,-1,-3,5,3}, 3);
    }
}
