package xyz.itxiaowu.test;

import org.junit.Assert;
import org.junit.Test;
import xyz.itxiaowu.junit.Calculator01;

/**
 * @author jchen
 * @date 2021/11/8 15:34
 */

public class Calculator01Test extends Calculator01 {

    @Test
    public void testAdd() {
        Calculator01 calculator01 = new Calculator01();
        calculator01.add(1);
        calculator01.add(2);
        Assert.assertEquals(3,super.getResult());
    }

}
