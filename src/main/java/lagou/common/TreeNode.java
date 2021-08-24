package lagou.common;

/**
 * 二叉树结点的定义
 */
public class TreeNode {
    // 树结点中的元素值
    public int value ;
    // 二叉树结点的左子结点
    public TreeNode left ;
    // 二叉树结点的右子结点
    public TreeNode right ;

    public TreeNode(int val) {
        this.value = val;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
