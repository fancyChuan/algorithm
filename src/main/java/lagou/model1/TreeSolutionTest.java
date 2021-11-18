package lagou.model1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeSolutionTest {

    @Test
    public void test() {
        // int型相处，结果也是int
        System.out.println(3/2);
        System.out.println(4/2);
        System.out.println(5/2);
        System.out.println("==========");
        // 向下取整
        System.out.println(3>>1);
        System.out.println(3>>2);
        System.out.println(4>>1);
        System.out.println(4>>2);

        // pathAns.add(new ArrayList<>(path));
        // pathAns.add(path);
        // 这两行代码的差异
        ArrayList<Integer> path = new ArrayList<>();
        List<List<Integer>> pathAns = new ArrayList<>();
        path.add(1);
        path.add(2);
        path.add(3);

        pathAns.add(path);
        System.out.println(pathAns);

        pathAns.add(new ArrayList<>(path));
        System.out.println(pathAns);
    }
}
