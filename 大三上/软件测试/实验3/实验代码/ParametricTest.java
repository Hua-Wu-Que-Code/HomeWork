
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParametricTest {
    private static Calculator calculator = new Calculator();
    private int param;
    private int result;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {2,4},{0,0},{-3,9}
        });
    }

    public ParametricTest(int param, int result) {
        this.param = param;
        this.result = result;
    }

    @Test
    //参数化测试，要求至少包含3个参数（对应3个测试用例）
    public void testSquare() {
        calculator.clear();
        calculator.square(param);
        assertEquals(result,calculator.getResult());
    }
}
