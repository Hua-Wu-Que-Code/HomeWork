import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SalaryTest {

    private static Salary salary = new Salary();
    private int param;
    private int result;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {20,400},{46,980},{55,1400},{-1,-2},{101,-1},{0,0},{40,800},
                {41,830},{50,1100},{51,1160},{100,4100}
        });
    }

    public SalaryTest(int param, int result) {
        this.param = param;
        this.result = result;
    }

    @Test
    //参数化测试，要求至少包含3个参数（对应3个测试用例）
    public void test() {
        assertEquals(result,salary.countTotal(param),0);
    }
}
