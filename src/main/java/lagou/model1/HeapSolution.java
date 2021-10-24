package lagou.model1;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 优先级队列：堆
 *  相关实现代码
 */
public class HeapSolution {

    /**
     * 使用Java语言内置的堆-优先级队列
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k<=0 ) {
            return new int[0];
        }
        // 构建大顶堆
        PriorityQueue<Integer> Q = new PriorityQueue<>((v1, v2) -> v2-v1);

        for (int x : arr) {
            Q.offer(x);

            while (Q.size() > k) {
                Q.poll();
            }
        }

        int[] ans = new int[k];
        int i = 0;
        for (int x: Q) {
            ans[i++] = x;
        }
        return ans;
    }


    /**
     * 练习1
     * [347] 前 K 个高频元素
     *   https://leetcode-cn.com/problems/top-k-frequent-elements/description/
     */
    class IntCounter extends HashMap<Integer, Integer> {
        public Integer get(Integer key) {
            return containsKey(key) ? super.get(key) : 0;
        }
        public void add(Integer key, int v) {
            put(key, get(key) + v);
            if (get(key) <= 0) {
                remove(key);
            }
        }
    }
    class IntNode {
        public int key;
        public int cnt;

        public IntNode(int key, int cnt) {
            this.key = key;
            this.cnt = cnt;
        }
    }
    public int[] topKFrequent(int[] A, int k) {
        if (k<=0) {
            return new int[0];
        }
        // 先对元组做一下统计
        IntCounter counter = new IntCounter();
        for (int x : A) {
            counter.add(x, 1);
        }

        // 构建小顶堆，主要使用自定义类IntNode，实际上只要是二元组就行
        PriorityQueue<IntNode> Q = new PriorityQueue<>((v1, v2) -> v1.cnt - v2.cnt);
        // 遍历统计结果，取出各个元素及其统计结果，并push到堆中
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Q.add(new IntNode(entry.getKey(), entry.getValue()));
            while (Q.size() > k) {
                Q.poll();
            }
        }
        // 将堆的结果取出来
        int i = 0;
        int[] ans = new int[k];
        for (IntNode node : Q) {
            ans[i++] = node.key;
        }

        return ans;
    }
}
