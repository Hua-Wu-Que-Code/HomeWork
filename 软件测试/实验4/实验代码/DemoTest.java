import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DemoTest {

    @Test
    public void test1() {
        Demo demo1 = new Demo();

        assertEquals(0,demo1.calday(-1111));
    }

    @Test
    public void test2() {
        Demo demo2 = new Demo();

        assertEquals(0,demo2.calday(8888));
    }

    @Test
    public void test3() {
        Demo demo3 = new Demo();

        assertEquals(2,demo3.calday(2));
    }

    @Test
    public void test4() {
        Demo demo2 = new Demo();

        assertEquals(2,demo2.calday(3));
    }
}
