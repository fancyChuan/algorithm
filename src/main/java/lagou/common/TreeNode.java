package lagou.common;

/**
 * 二叉树结点的定义
 */
public class TreeNode {
    // 树结点中的元素值
    public int val;
    // 二叉树结点的左子结点
    public TreeNode left ;
    // 二叉树结点的右子结点
    public TreeNode right ;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
