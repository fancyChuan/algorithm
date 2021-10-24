package lagou.common;

/**
 * 实现堆的pop和push操作
 *  以及上浮、下沉操作
 */
public class Heap {
    private int[] a = null;
    private int n = 0;

    /**
     * 下沉操作
     */
    public void sink(int i) {
        // 等待下沉的元素
        int t = a[i];
        int j = 0;
        // TODO
    }

    /**
     * 上浮操作
     */
    public void swim(int i) {
        // TODO
    }

    /**
     * 插入元素
     */
    public void push(int value) {
        a[n++] = value;
        // 这里要n-1是因为上一步是n++，也就是加入插入第10个元素，那么这个时候n[9]=第10个元素，然后n+1，也就是n=10
        swim(n-1);
    }

    /**
     * 从堆弹出元素
     */
    public int pop() {
        int ret = a[0];
        // 弹出元素后，堆的大小要减1，由此要把最后一个元素放到头节点
        a[0] = a[--n];
        sink(0);
        return ret;
    }

    public int size() {
        return n;
    }
}
