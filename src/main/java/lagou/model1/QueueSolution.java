package lagou.model1;

import lagou.common.Node;
import lagou.common.TreeNode;

import java.util.*;

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
                levelValue.add(node.val); // int[]没有add或者put方法，需要通过下标的方式读写
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
                curResult.add(treeNode.val);
            }
            curLevel = nextLevel;
            ans.add(curResult);
        }
        return ans;
    }

    /**
     * 思考题：
     *  填充二叉树里所有的 next 指针，使其指向右边的结点，如果右边没有结点，那么设置为空指针。
     *
     *  结题的关键思路还是：层次遍历，一层一层的处理【问题拆解，分层解决】
     *  测试地址：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/
     */
    public static Node treeNextSolution(Node root) {
        Node Q = null;
        if (root != null) {
            Q = root;
        }
        // 一个Q遍历一层，在这一层遍历的时候，就要把下一层都找到，然后通过next来连接
        while (Q != null) {
            // 下一层的起点。只需要被赋值一次
            Node nextLevelHead = null;
            // 用于填充next的主节点，nextLevelPreNode.next=?? 因此这个变量需要不断的移动
            Node nextLevelPreNode = null;

            // 这里的循环需要遍历这一层的每个节点
            // 刚开始就是遍历Q
            Node currentNode = Q;
            while (currentNode != null) {
                /**
                 * 左结点不为空的处理，有以下职责
                 * 1. 如果下一层的头结点nextLevelHead还没有被赋值，需要赋值
                 * 2. 如果有前一个节点nextLevelPreNode，那么其next指针需要被填充。这种情况出现在下一层非第一个左节点时
                 * 3. 将自己作为可能存在的下一节点的前节点。即nextLevelPreNode=自己
                 */
                if (currentNode.left != null) {
                    if (nextLevelHead == null) {
                        nextLevelHead = currentNode.left;
                    }
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = currentNode.left;
                    }
                    nextLevelPreNode = currentNode.left;
                }
                /**
                 * 职责跟左节点类似
                 */
                if (currentNode.right != null) {
                    if (nextLevelHead == null) {
                        nextLevelHead = currentNode.right;
                    }
                    // 记录好下一个节点
                    if (nextLevelPreNode != null) {
                        nextLevelPreNode.next = currentNode.right;
                    }
                    // nextLevelPreNode滑动到下一个节点
                    nextLevelPreNode = currentNode.right;
                }
                // 将当层的下一个节点给到currentNode，以便遍历
                currentNode = currentNode.next;
            }
            // 将下一层的头节点赋值给Q
            Q = nextLevelHead;
        }
        return root;
    }

    /**
     * 单调队列的应用场景：
     *  [239] 滑动窗口最大值
     *      https://leetcode-cn.com/problems/sliding-window-maximum/description/
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        // 单调队列使用双端队列来实现
        ArrayDeque<Integer> Q = new ArrayDeque<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            System.out.println("===== 第" + i + "个元素为：" + val);
            // 下面的循环是为了实现单调的目的
            while (!Q.isEmpty() && Q.getLast() < val) {
                Q.removeLast();
            }
            Q.addLast(val);
            // 前k个元素要全部入队了，才能开始取最大值
            if (i < k - 1) {
                continue;
            }
            ans.add(Q.getFirst());
            // 取出数据后，将首元素出队
            if (!Q.isEmpty() && Q.getFirst() == nums[i-k+1]) {
                Q.removeFirst();
            }
            System.out.println("      处理后，单调队列的内容为：" + Q);
        }
        return ans.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * 例4：捡金币游戏
     *  [1696] 跳跃游戏 VI
     *      https://leetcode-cn.com/problems/jump-game-vi/description/
     */
    public int maxResult(int[] nums, int k) {
        // 处理掉各种临界条件
        if (nums == null || nums.length == 0 || k <= 0) {
            return 0;
        }
        // 使用get[]数组来存放递归求解中的中间值
        // 即表示：截止到某个位置所能获取的最大金币
        int[] get = new int[nums.length];
        ArrayDeque<Integer> Q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                // 千万注意，这是里针对get[]数组，也就是中间结果进入单调队列
                if (!Q.isEmpty() && Q.getFirst() == get[i-k-1]) {
                    Q.removeFirst();
                }
            }
            // 注意第一次Q是空的
            int old = Q.isEmpty() ? 0: Q.getFirst();
            get[i] = old + nums[i];
            // get[i]入单调队列，需要判断
            while (!Q.isEmpty() && Q.getLast() < get[i]) {
                Q.removeLast();
            }
            Q.addLast(get[i]);
        }
        return get[nums.length -1];
    }
}
