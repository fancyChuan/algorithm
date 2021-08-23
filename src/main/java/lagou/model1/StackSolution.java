package lagou.model1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class StackSolution {

    /**
     * 例 1：判断字符串括号是否合法【常规做法】
     *  复杂度分析：每个字符只入栈一次，出栈一次，所以时间复杂度为 O(N)，
     *          而空间复杂度为 O(N)，因为最差情况下可能会把整个字符串都入栈。
     */
    public static boolean isValid(String s) {
        // 边界：1. 为空，2. 字符串长度为奇数
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        // 配对的核心逻辑：遇到(则压栈，遇到)则将(出栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("---------\n扫描字符：" + c);
            if (c == '(') {
                stack.push(c);
                System.out.println("压栈，现在栈的内容为：" + stack.toString());
            } else if (c == ')') {
                // 注意这步，容易遗漏，如果为空，则栈的弹出会失败
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
                System.out.println("出栈，现在栈的内容为：" + stack.toString());
            }
        }

        return stack.empty();
    }

    /**
     * 例 1：判断字符串括号是否合法
     * 【优化】：因为栈里边存的都是同样的字符，可以使用leftBraceNumber来替换栈
     * 复杂度分析：每个字符只入栈一次，出栈一次，所以时间复杂度为 O(N)，
     *          而空间复杂度为 O(1)，因为我们已经只用一个变量来记录栈中的内容
     */
    public static boolean isValid1(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        //
        int leftBraceNumber = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("---------\n扫描字符：" + c);
            if (c == '(') {
                leftBraceNumber += 1;
                System.out.println("压栈，现在栈的大小为："  + leftBraceNumber);
            } else if (c == ')') {
                if (leftBraceNumber == 0) {
                    return false;
                }
                leftBraceNumber -= 1;
                System.out.println("出栈，现在栈的大小为："  + leftBraceNumber);
            }
        }
        return leftBraceNumber == 0;
    }

    /**
     * 例 1：判断字符串括号是否合法
     * 【扩展】给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效，需要正确配对
     */
    public static boolean isValid2(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        // 配对的核心逻辑：遇到(则压栈，遇到)则将(出栈
        Stack<Character> stack = new Stack<>();
        // 这里使用一个map来将字符做配对，便于从栈取出数据后来判断是否配对
        HashMap<Character, Character> charMap = new HashMap<>();
        charMap.put('[', ']');
        charMap.put('(', ')');
        charMap.put('{', '}');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("---------\n扫描字符：" + c);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                System.out.println("压栈，现在栈的内容为：" + stack.toString());
            } else if (c == ')' || c == ']' || c == '}') {
                // 注意这步，容易遗漏，如果为空，则栈的弹出会失败
                if (stack.empty()) {
                    return false;
                }
                Character popChar = stack.pop();
                System.out.println("出栈，现在栈的内容为：" + stack.toString());
                if (charMap.get(popChar) != c) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    /**
     * 例 1：判断字符串括号是否合法
     * 【扩展】给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效，需要正确配对
     * 【另一种实现方法】
     */
    public boolean isValid3(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> t = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '(' || c == '[') {
                t.push(c);
            } else if (c == '}') {
                // TODO：这里一定必须得使用peek()么，不一定吧
                if (t.empty() || t.peek() != '{') {
                    return false;
                }
                t.pop();
            } else if (c == ']') {
                if (t.empty() || t.peek() != '[') {
                    return false;
                }
                t.pop();
            } else if (c == ')') {
                if (t.empty() || t.peek() != '(') {
                    return false;
                }
                t.pop();
            } else {
                return false;
            }
        }

        return t.empty();
    }

    /**
     * 例 2：大鱼吃小鱼
     * 复杂度分析：每只鱼只入栈一次，出栈一次，所以时间复杂度 为 O(N)，
     *          而空间复杂度为 O(N)，因为最差情况下可能把所有的鱼都入栈。
     */
    public static int fishSolution(int[] fishSize, int[] fishDir) {
        Stack<Integer> stack = new Stack<>();
        // 鱼的方向
        final int left = 0;
        final int right = 1;

        final int fishNumber = fishSize.length;
        if (fishNumber <= 1) {
            return fishNumber;
        }
        for (int i = 0; i < fishNumber; i++) {
            final int curFishSize = fishSize[i];
            final int curFishDir = fishDir[i];
            System.out.println("------\n扫描到第" + i + "条鱼，大小为：" + curFishSize + "，方向为：" + curFishDir);
            boolean hasEat = false;
            while (!stack.isEmpty() && fishDir[stack.peek()] == right && curFishDir == left) {
                // 需要消除掉栈顶的内容：栈顶的鱼要么吃掉右边的鱼，要么被右边的鱼吃掉。那么要求栈顶的与是向右游的，而当前的鱼是向左游的
                // 这个时候如果栈顶的与大，则吃掉右边的，自己保留
                if (fishSize[stack.peek()] > curFishSize) {
                    hasEat = true;
                    break;
                } else {
                    // 栈顶的鱼比当前的小，那么栈顶被吃掉，也就需要出栈
                    stack.pop();
                    System.out.print("栈顶鱼被吃掉\t");
                    printStack(stack);
                }
            }
            // 只有栈里边的鱼被吃掉了或者栈里没有鱼，才需要入栈
            if (!hasEat) {
                stack.push(i);
                System.out.print("新鱼入栈\t");
                printStack(stack);
            }
        }

        return stack.size();
    }

    /**
     * 例 3：找出数组中右边第一个比我小的元素
     *
     */
    public static int[] findRightSmall(int[] A) {

        int[] ans = new int[A.length];
        // 栈中元素存的是下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            final int x = A[i];
            System.out.println("--------\n扫描到数字：" + x);
            // 如果比栈顶存的下标所对应的数据要小，那么弹出出栈
            while (!stack.empty() && A[stack.peek()] > x) {
                ans[stack.peek()] = i;
                stack.pop();
                System.out.print("下标出栈\t");
                printStack(stack);
            }
            stack.push(i);
            System.out.print("下标" + i + "入栈\t");
            printStack(stack);
            System.out.println(Arrays.toString(ans));
        }
        while (!stack.empty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }

        return ans;
    }


    static void printStack(Stack stack) {
        System.out.println("【此时】栈的大小为：" + stack.size() + "\t栈的内容为：" + stack.toString());
    }
}
