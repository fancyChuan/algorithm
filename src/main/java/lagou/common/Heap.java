package lagou.common;

/**
 * 实现堆的pop和push操作
 *  以及上浮、下沉操作
 *
 *  这里以大堆为例
 */
public class Heap {
    private int[] arr = null;
    private int n = 0;

    /**
     * 下沉操作
     *  由于堆是一个完全二叉树，所以在下沉的时候，下沉路径就是树的高度。如果堆中有 N 个元素的话，所以时间复杂度为 O(lgN)
     */
    public void sink(int i) {
        // 等待下沉的元素
        int curNodeValue = arr[i];
        // 用来存放子节点的索引，以及在比较时使用
        int j = 0;
        // 每次都能找到左子节点，并且小于堆的总元素个数n
        while ((j = i * 2 + 1) < n) {
            // 看左右子节点谁大
            // 1. 是否存在子节点 2. 如果右边节点大
            if (j+1<n && arr[j]< arr[j+1]) {
                j++;
            }
            // 节点数据开始和子节点比较：子节点大
            if (curNodeValue < arr[j]) {
                // 交换位置
                arr[i] = arr[j];
                i = j;
            } else {
                // 当前节点比左右子节点都要大，跳出循环
                break;
            }
        }
        // 注意：最后这一步别忘了，i在循环过程中可能变化了，要记得将目标数据放到所找到的i的位置上
        arr[i] = curNodeValue;
    }

    /**
     * 上浮操作
     *  由于堆是一个完全二叉树，所以在上浮的时候，上浮路径就是树的高度。如果堆中有 N 个元素的话，所以时间复杂度为 O(lgN)
     */
    public void swim(int i) {
        int curNodeValue = arr[i];
        int parent = 0;
        // 还没到顶点，意味着还有父节点
        while (i > 0) {
            parent = (i -1) / 2;
            // 如果父节点小，则交换位置
            if (arr[parent] < curNodeValue) {
                arr[i] = arr[parent];
                i = parent;
            } else {
                break;
            }
            arr[i] = curNodeValue;
        }
    }

    /**
     * 插入元素
     */
    public void push(int value) {
        arr[n++] = value;
        // 这里要n-1是因为上一步是n++，也就是加入插入第10个元素，那么这个时候n[9]=第10个元素，然后n+1，也就是n=10
        swim(n-1);
    }

    /**
     * 从堆弹出元素
     */
    public int pop() {
        int ret = arr[0];
        // 弹出元素后，堆的大小要减1，由此要把最后一个元素放到头节点
        arr[0] = arr[--n];
        sink(0);
        return ret;
    }

    public int size() {
        return n;
    }
}
