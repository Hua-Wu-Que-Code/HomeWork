package 实验代码;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {ParametricTest.class, CalculatorTest.class})
public class AllCalculatorTest {
}
