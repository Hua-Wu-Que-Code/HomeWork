import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NextDateTest {

    private static NextDate nextDate = new NextDate();
    private String param;
    private String result;

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"2000-9-16","2000-9-17"},
                {"2000-9-30","2000-10-1"},
                {"2000-1-31","2000-2-1"},
                {"2000-1-30","2000-1-31"},
                {"2000-2-28","2000-2-29"},
                {"2000-2-29","2000-3-1"},
                {"2001-2-28","2001-3-1"}
        });
    }

    public NextDateTest(String param, String result) {
        this.param = param;
        this.result = result;
    }

    @Test
    //参数化测试，要求至少包含3个参数（对应3个测试用例）
    public void test() {
        assertEquals(result,nextDate.nextDate(param));
    }
}
