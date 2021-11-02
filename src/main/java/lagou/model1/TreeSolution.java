package lagou.model1;

import lagou.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeSolution {


    /**
     * 递归前序遍历
     *  - 时间复杂度，由于树上的每个结点都只访问一次，并且每次访问都只有一次压栈弹栈操作，所以复杂度为 O(N)。
     *  - 空间复杂度，由于函数调用栈的深度与树的高度有关系，所以使用的空间为 O(H)。H 表示树的高度。
     *    （注意：一般而言，输出结果存放的 List 并不算在空间复杂度里面）。
     */
    public void preOrder(TreeNode root, List<Integer> ans) {
        if (root != null) {
            // 先处理根结点
            ans.add(root.value);

            // 递归处理左边子节点
            preOrder(root.left, ans);
            // 递归处理左边子节点
            preOrder(root.right, ans);
        }
    }

    /**
     * 使用栈完成前序遍历
     *  - 算法复杂度：每个结点都入栈出栈一次，遍历整棵树的时间复杂度为 O(N)
     *  - 空间复杂度就是栈的最大使用空间，而这个空间是由树的高度决定的，所以空间复杂度就是 O(H)。
     *
     */
    public void preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null & !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                ans.add(root.value);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
    }

    /**
     * Morris 遍历
     *  TODO:
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        return null;
    }

    /**
     * 例 1：验证二叉搜索树
     * 二叉搜索树有以下特点：
     *  - 根结点的值大于所有的左子树结点的值
     *  - 根结点的值小于所有的右子树结点的值
     *  - 左右子树也必须满足以上特性
     */

}
