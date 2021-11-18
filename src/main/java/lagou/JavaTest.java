package lagou;

import org.junit.Test;

public class JavaTest {

    @Test
    public void test() {
        int k = 3;
        k += 2 +1;
        System.out.println(k);
        // 相当于是 2+1 之后，再用k减去。所以结果是3
        k -= 2 + 1;
        System.out.println(k);
    }

    @Test
    public void test2() {
        int a = 3;
        int b = 4;
        System.out.println(a & 1);
        System.out.println(b & 1);
        System.out.println(a & 2);
        System.out.println(b & 2);
    }
}
