package xyz.itxiaowu.junit;

/**
 * @author jchen
 * @date 2021/11/3 16:30
 */

public class CalculatorTest {

    public static void main(String[] args) {

        // 创建对象
        Calculator c = new Calculator();
        // 调用方法
        int result;
        result = c.add(1,2);
//        result = c.sub(1,1);
        System.out.println(result);
    }

}
