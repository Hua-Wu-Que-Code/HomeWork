package xyz.itxiaowu.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import xyz.itxiaowu.junit.Demo03;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author jchen
 * @date 2021/11/7 15:43
 */

@RunWith(Parameterized.class)
public class Demo03Test {

    private Demo03 demo03;

    @Before
    public void init() {
        demo03 = new Demo03();
    }


    @Parameterized.Parameters
    public static Collection<?> data() {

        return Arrays.asList(new Object[][]{
                {1, 2, 3}, {1, 1, 1}, {-1, 1, 0}
        });
    }

    int input1;
    int input2;
    int expected;

    public Demo03Test(int input1, int input2, int expected) {
        this.input1 = input1;
        this.input2 = input2;
        this.expected = expected;
    }

    @Test
    public void testAdd() {
        int result = demo03.add(this.input1, this.input2);
        assertEquals(this.expected, result);
    }


}
