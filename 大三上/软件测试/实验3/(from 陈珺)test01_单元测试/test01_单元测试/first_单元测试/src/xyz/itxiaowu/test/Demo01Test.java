package xyz.itxiaowu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xyz.itxiaowu.junit.Demo01;

/**
 * @author jchen
 * @date 2021/11/7 13:27
 */

public class Demo01Test {

    private Demo01 demo01;

    @Before
    public void init() {
        demo01 = new Demo01();
    }

    @After
    public void close() {
    }

    @Test(expected = NumberFormatException.class)
    public void testTestNumberFormat() {
        demo01.testNumberFormat("12");
    }

    @Test(expected = NumberFormatException.class)
    public void testTestExtends() {
        demo01.testExtends();
    }

}
