package xyz.itxiaowu.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author jchen
 * @date 2021/11/7 16:43
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {CalculatorTest.class,Demo01Test.class, Demo03Test.class})
public class AllDemoTests {
}
