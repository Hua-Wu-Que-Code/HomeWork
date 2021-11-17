package xyz.itxiaowu.test;

import org.junit.*;
import xyz.itxiaowu.junit.Calculator;

/**
 * @author jchen
 * @date 2021/11/4 19:20
 */

public class CalculatorTest {

   @Before
    public void init() {
       System.out.println("init被执行了");
    }

    @After
    public void close() {
        System.out.println("close被执行了\n");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass被执行了\n");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass被执行了");
    }


    @Test
    public void testAdd() {
        // 1.创建计算器对象
        System.out.println("tsetAdd 方法被执行了");
        Calculator c = new Calculator();
        // 2.调用add方法
        int result = c.add(1, 2);
//        System.out.println(result);

        // 3.断言 我断言这个结果是3
        Assert.assertEquals(3, result);
    }

    @Test
    public void testSub() {
        // 1.创建计算器对象
        Calculator c = new Calculator();
        // 2.调用add方法
        int result = c.sub(2,1);
        System.out.println("testSub方法被执行了");
        Assert.assertEquals(1, result);
    }

}
