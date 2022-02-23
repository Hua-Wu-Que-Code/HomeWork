package xyz.itxiaowu.junit;

/**
 * @author jchen
 * @date 2021/11/7 14:09
 */

public class Demo02 {

    public int sum1() {
        int result = 0;
        for (int i = 1; i <= 1000; i++) {
            result += i;
        }

        return result;
    }

    public int sum2() {
        int result = 0;
        for (int i = 1; i <= 1000; i++) {
            result += i;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
