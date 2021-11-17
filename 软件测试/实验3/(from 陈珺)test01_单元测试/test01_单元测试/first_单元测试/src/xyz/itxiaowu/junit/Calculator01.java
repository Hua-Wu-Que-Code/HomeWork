package xyz.itxiaowu.junit;

/**
 * @author jchen
 * @date 2021/11/8 15:32
 */

public class Calculator01 {

    private static int result;

    public void add(int a) {
//        int n = 3/0;
        result = result + a;
    }

    public int getResult() {
        return result;
    }

}
