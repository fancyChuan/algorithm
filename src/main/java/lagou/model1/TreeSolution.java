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
            ans.add(root.val);

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
                ans.add(root.val);
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
     *  [98] 验证二叉搜索树
     *      https://leetcode-cn.com/problems/validate-binary-search-tree/description/
     *
     *  核心：
     *    lnode rnode
     *      \    /
     *       node
     *   判断 node是否在(lnode, rnode)内
     */
    boolean ansBST = true;
    public boolean isValidBST(TreeNode root) {
        ansBST = true;
        // 根据核心，根结点没有父节点，因此用 (Long.MIN_VALUE, Long.MAX_VALUE)
        preOrder(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return ansBST;
    }
    public void preOrder(TreeNode root, Long leftValue, Long rightValue) {
        // 一旦发现非 ansBST 或者发现结点是null，那么本次就不需要判断，直接跳出
        if (root == null || !ansBST) {
            return;
        }
        if (!(leftValue < root.val && root.val < rightValue)) {
            ansBST = false;
            return;
        }
        // 遍历左子树
        preOrder(root.left, leftValue, Long.valueOf(root.val));
        // 遍历右子树
        preOrder(root.right, Long.valueOf(root.val), rightValue);
    }

    /**
     * TODO:使用栈来完成影子二叉树的验证
     */

    /**
     * 练习题 1：“影子”二叉树还可以解决“是否相同的树”的问题。比如给定两棵二叉树，要求判断这两棵二叉树是不是一样的？
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return  false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 练习题 2：当我们写出“判断是否相同的树”的代码之后，可以开始思考另外一个问题——如何判断一棵树是不是另外一棵树的子树？
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == t) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        // TODO 注意这里的条件
        return s.val==t.val && isSameTree(s, t)
            || isSubtree(s.left, t)
            || isSubtree(s.right, t);
    }

    /**
     * 例 2：目标和的所有路径
     * 【题目】给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *      叶子节点 是指没有子节点的节点。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum-ii
     *
     * 这道题的隐藏意思是：从根节点遍历所有的叶子结点，拿到所有的“完整路径”，并计算完整的路径是否等于目标值
     *  - 递归解法的关键：写一个递归函数
     *  - 栈解法的关键：两层遍历
     *
     *  【小结】本质上，这道题的考点就是：回溯，只不过借用了二叉树这个皮。反过来，在二叉树上进行回溯的代码模板，也需要熟练掌握
     */
    // 存放答案的对象
    private List<List<Integer>> pathAns = null;
    // 递归函数
    private void backTrace(TreeNode root, List<Integer> path, int sum, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        sum += root.val;
        // 如果已经访问到末尾的叶子结点的。判断是否等于目标值
        if (root.left == null && root.right == null) {
            if (sum == target) {
                // pathAns.add(path); // 这行代码在本地编译器是正常的，但是在leecode上，添加进去的就是[]
                pathAns.add(new ArrayList<>(path));
            }
        } else {
            // 递归左右子树
            backTrace(root.left, path, sum, target);
            backTrace(root.right, path, sum, target);
        }
        // 每走完一次递归，都需要把最后的节点干掉！
        path.remove(path.size() - 1);

    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathAns = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        backTrace(root, path, 0, targetSum);
        return pathAns;
    }

    /**
     * 使用栈完成中序遍历
     */
    public ArrayList<Integer> inOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        while (root != null || !stack.isEmpty()) {
            // 内层循环，不断访问左节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 外层循环开启取出结点，并直接先访问，然后再查看是否有右节点（有的话压栈）
            TreeNode node = stack.pop();
            // 先访问结点
            ans.add(node.val);
            // 转向右节点
            root = node.right;
        }
        return ans;
    }

    /**
     * 使用中序遍历验证“搜索二叉树”
     */
    // 递归的关键是写一个递归函数
    private boolean midAnsBST = true;
    // 注意! 这个变量必须使用全局变量
    private Long preValue;
    private void midOrder(TreeNode root) {
        if (root == null || !midAnsBST) {
            return;
        }
        // 递归访问左节点
        midOrder(root.left);
        // 访问当前节点
        if (root.val <= preValue) {
            midAnsBST = false;
            return;
        }
        // 如果不使用全局变量，因为采用中序遍历，这行代码导致上一个递归用到的preValue是旧的数据！
        preValue = (long) root.val;
        // 递归访问右节点
        midOrder(root.right);
    }
    public boolean midIsValidBST(TreeNode root) {
        // 注意这个地方还是得用Long类型！
        preValue = Long.MIN_VALUE;
        midOrder(root);
        return midAnsBST;
    }

    /**
     * 使用栈实现 后序遍历
     *  https://leetcode-cn.com/problems/binary-tree-postorder-traversal/description/
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = root;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 先不弹出来
            TreeNode node = stack.peek();
            if (node.right != null && node.right != pre) {
                // 注意要避免右节点的重复遍历
                // 怎么发现需要加个pre呢？当你自己画一个二叉树的时候，某个节点模拟一次发挥发现！
                // 一定要勤画图！！
                root = node.right;
            } else {
                // 说明没有右子树了，开始真正的弹栈
                stack.pop();
                // 开始访问节点
                ans.add(node.val);
                pre = node; // 记录已访问的节点
            }
        }
        return ans;
    }
    /**
     * 使用后序遍历，验证二叉搜索树
     */
    public boolean isBSTPost;
    private Long leftValue;
    private Long rightValue;
    // 关键的递归函数
    public void parsePost(TreeNode root) {
        if (!isBSTPost) {
            return;
        }
        if (root.left == null) {
            leftValue = (long) root.val;
        } else {
            parsePost(root.left);
        }
        if (root.right == null) {
            rightValue = (long) root.val;;
        } else {
            parsePost(root.right);
        }
        if (root.val <= leftValue || root.val >= rightValue) {
            isBSTPost = false;
        }
    }
    public boolean isValidBSTPost(TreeNode root) {
        leftValue = Long.MIN_VALUE;
        rightValue = Long.MAX_VALUE;
        parsePost(root);
        return isBSTPost;
    }
}

