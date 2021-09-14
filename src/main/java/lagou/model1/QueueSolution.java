package lagou.model1;

import lagou.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueSolution {

    /**
     *  例 1：二叉树的层次遍历，解法一
     *  由于二叉树的每个结点，我们都只访问了一遍，所以时间复杂度为 O(n)。
     *  如果不算返回的数组，那么空间复杂度为 O(k)，这里的 k 表示二叉树横向最宽的那一层的结点数目。
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

    /**
     * 例 1：二叉树的层次遍历，解法二
     *  采用两层ArrayList来表示
     */
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        // 最终的结果
        List<List<Integer>> ans = new LinkedList<>();
        // 新建一个用来存当前节点的列表
        ArrayList<TreeNode> curLevel = new ArrayList<>();
        // 根结点的初始化
        if (root != null) {
            curLevel.add(root);
        }

        while (curLevel.size() > 0) {
            ArrayList<Integer> curResult = new ArrayList<>();
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode treeNode : curLevel) {
                if (treeNode.left != null) {
                    nextLevel.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nextLevel.add(treeNode.right);
                }
                curResult.add(treeNode.value);
            }
            curLevel = nextLevel;
            ans.add(curResult);
        }
        return ans;
    }
}
