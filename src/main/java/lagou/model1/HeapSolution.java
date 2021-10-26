package lagou.model1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 优先级队列：堆
 *  相关实现代码
 */
public class HeapSolution {

    /**
     * 例1： 最小的k个数
     *  解法1：先排序，再返回前k个，算法负责度是O(nlgn) 时间复杂度较高
     *  解法2：使用堆
     *      方式1：使用Java语言内置的堆-优先级队列
     *      复杂度分析：首先堆的大小为 k，那么每次 push/pop 的复杂度都是 O(lgk)。
     *                一共有 n 个元素，所以复杂度为 O(nlgk)。
     *
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
     * 例1： 最小的k个数
     * 使用自定义的堆
     *  时间复杂度，每个元素都需要入堆，而堆 push 的时间复杂度为 O(lgk)。因为我们控制了堆的大小为 k，所以总的时间复杂度为 O(Nlgk)。
     *  因为我们使用了 k 个元素的堆，所以空间复杂度为 O(k)。
     */
    public static int[] getLeastNumbers2(int[] arr, int k) {
        int[] ans = new int[k];
        // 初始化堆。注意，这里堆的大小需要k+1
        int[] heap = new int[k + 1];
        for (int x : arr) {
            // push(x);
//            if (size() > k) {
//                pop();
//            }
        }

        return ans;
    }

    /**
     * 例1： 最小的k个数
     *  解法3：使用快排，能把时间复杂度降为O(n)
     */
    public static int[] getLeastNumbers3(int[] arr, int k) {
        // TODO
        return null;
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

    /**
     * 例 2：跳跃游戏
     *  [1642] 可以到达的最远建筑
     *      https://leetcode-cn.com/problems/furthest-building-you-can-reach/description/
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if (heights == null || heights.length == 0) {
            return -1;
        }
        // 使用大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((v1, v2) -> v2 - v1);
        int lastPos = 0;
        int qSum = 0;
        int preHeight = heights[0];
        for (int i = 1; i < heights.length; i++) {
            int curHeight = heights[i];
            if (preHeight >= curHeight) {
                lastPos = i; // 能用++吗
            } else {
                int delta = curHeight - preHeight;
                heap.add(delta);
                qSum += delta;
            }
            // 弹出元素的条件是：堆中的总和不能超出砖块的数目
            while (qSum > bricks && ladders > 0) {
                qSum -= heap.poll();
                ladders --;
            }
            if (qSum <= bricks) {
                lastPos = i;
            } else {
                break;
            }
            preHeight = curHeight;
        }
        return lastPos;
    }

    /**
     * 练习题6
     * [1705] 吃苹果的最大数目
     *      https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/description/
     */
    class AppleNode {
        // 记录苹果个数
        public int num;
        // 记录哪天坏掉
        public int bad;
        public AppleNode() {
        }
        public AppleNode(int num, int bad) {
            this.num = num;
            this.bad = bad;
        }
    }
    public int eatenApples(int[] A, int[] B) {
        // 构造小顶堆
        PriorityQueue<AppleNode> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.bad));
        int ans = 0;
        int day = 0;

        int N = A == null ? 0 : A.length;
        // 第一层循环按天。 注意核心就是两个条件：i<=N || !queue.isEmpty()
        int i = 1;
        while (i<=N || !queue.isEmpty()) {
            // 按照坏掉的时间排序，越早坏就越早吃掉。使用堆。
            if (i<=N) {
                AppleNode appleNode = new AppleNode(A[i-1], B[i-1] + i);
                if (appleNode.num > 0) {
                    queue.offer(appleNode);
                }
            }
            // 到了某一天可能会好几个坏掉，都处理掉
            while (!queue.isEmpty() && (queue.peek().bad <= i)) {
                queue.poll();
            }
            // 每天取一个出来吃
            if (!queue.isEmpty()) {
                AppleNode node = queue.poll();
                ans ++;
                if (--node.num > 0) {
                    queue.offer(node);
                }
            }
            i++;
        }
        return ans;
    }
}
