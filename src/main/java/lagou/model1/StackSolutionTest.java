package lagou.model1;

import org.junit.Test;

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
}
