package xyz.itxiaowu.test;

import org.junit.Before;
import org.junit.Test;
import xyz.itxiaowu.junit.Demo02;

/**
 * @author jchen
 * @date 2021/11/7 14:30
 */

public class Demo02Test {

    private Demo02 demo02;

    @Before
    public void init() {
        demo02 = new Demo02();
    }

    @Test(timeout = 1000)
    public void testSum1() {
        demo02.sum1();
    }

    @Test(timeout = 1000)
    public void testSum2() {
        demo02.sum2();
    }
}
