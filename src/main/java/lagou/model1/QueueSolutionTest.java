package lagou.model1;

import lagou.common.TreeNode;
import org.junit.Test;

public class QueueSolutionTest {

    @Test
    public void testLevelOrder() {
        TreeNode tree = new TreeNode(3, new TreeNode(9, null, new TreeNode(4)), new TreeNode(8, new TreeNode(6), new TreeNode(7)));
        System.out.println(QueueSolution.levelOrder(tree));
    }
}
