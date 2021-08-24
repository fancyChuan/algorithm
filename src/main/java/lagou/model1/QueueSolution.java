package lagou.model1;

import lagou.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueSolution {

    /**
     *  例 1：二叉树的层次遍历（两种方法）
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        // 生成FIFO队列
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        // 最终的结果
        List<List<Integer>> ans = new LinkedList<>();

        // 因为是将遍历到的节点不断的放入队列的，因此可以用队列的大小来作为循环的依据
        while (queue.size() > 0) {
            // 当层的结点数
            int levelSize = queue.size();
            // int[] levelValue = new int[levelSize]; // 这里使用int[]那么结果就应该是int[][]，问题是最开始并不知道二维数组有多大，其实不适合用int[]
            List<Integer> levelValue = new LinkedList<>();
            // 通过遍历，把当层的结点都取出来
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelValue.add(node.value); // int[]没有add或者put方法，需要通过下标的方式读写
                // 把遍历到的下一层结点都放入队列，并且不让空指针放入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 遍历完了当层的内容，写入结果
            ans.add(levelValue);
        }
        return ans;
    }
}
