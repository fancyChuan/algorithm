package lagou.model1;

import org.junit.Test;

import java.util.Arrays;

public class StackSolutionTest {

    @Test
    public void testIsValid() {
        System.out.println(StackSolution.isValid("()((()()))"));
        System.out.println(StackSolution.isValid("()))"));
    }

    @Test
    public void testIsValid1() {
        System.out.println(StackSolution.isValid1("()((()()))"));
        System.out.println(StackSolution.isValid1("()))"));
    }

    @Test
    public void testIsValid2() {
        System.out.println(StackSolution.isValid2("(){[()()]}"));
        System.out.println(StackSolution.isValid2("([][)))"));
    }

    @Test
    public void testFishSolution() {
        int[] size = new int[] {4, 3, 2, 1, 5};
        int[] dir = new int[] {1, 1, 1, 1, 0};
        System.out.println(StackSolution.fishSolution(size, dir));
    }

    @Test
    public void testFindRightSmall() {
        int[] data = new int[] {4, 7, 2, 1, 5};
        System.out.println(Arrays.toString(StackSolution.findRightSmall(data)));
        System.out.println("==========================");
        int[] data2 = new int[] {4, 1, 6, 1, 5};
        System.out.println(Arrays.toString(StackSolution.findRightSmall(data2)));
    }

    @Test
    public void testFindSmallSeq() {
        int[] data1 = new int[] {4, 7, 2, 1, 5};
        int[] data2 = new int[] {4, 1, 6, 3, 5};
        int[] data3 = new int[] {9, 2, 4, 5, 1, 2, 3, 0};
        System.out.println(Arrays.toString(StackSolution.findSmallSeq(data1, 2)));
        System.out.println("==========================");
        System.out.println(Arrays.toString(StackSolution.findSmallSeq(data2, 2)));
        System.out.println("==========================");
        System.out.println(Arrays.toString(StackSolution.findSmallSeq(data3, 3)));
    }
}
