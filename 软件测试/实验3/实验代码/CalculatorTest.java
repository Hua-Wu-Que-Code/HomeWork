

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private static Calculator calculator = new Calculator();
    private int param;
    private int result;

    @Test
    //测试连续加
    public void testAdd() {
        calculator.clear();
        calculator.add(2);
        calculator.add(5);

        assertEquals(1,calculator.getResult());
    }

    @Test
    //测试连续减
    public void testSubtract() {
        calculator.clear();
        calculator.subtract(8);
        calculator.subtract(-5);

        assertEquals(-3,calculator.getResult());
    }

    @Test(timeout = 1000)
    //限时测试
    public void testSquareRoot() {
        calculator.clear();
        calculator.squareRoot(4);

        assertEquals(16,calculator.getResult());
    }

    @Test
    //满足分支条件覆盖测试
    public void testDivide() {

        //分支1
        calculator.clear();
        calculator.divide(2,12);

        assertEquals(6,calculator.getResult());

        //分支2
        calculator.clear();
        calculator.divide(2,6);

        assertEquals(3,calculator.getResult());
    }

    @Test
    //常规测试，连续执行两次乘法
    public void testMultiply() {

        calculator.clear();
        calculator.multiply(3);
        calculator.multiply(2);

        assertEquals(6,calculator.getResult());
    }
}
