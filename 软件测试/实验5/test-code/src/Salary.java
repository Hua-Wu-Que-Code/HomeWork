public class Salary {

    private static final Integer salary = 20;
    private static final Integer MaxTime = 100;
    private static final Integer MinTime = 0;
    private static final Integer FirstStageTime = 40;
    private static final Integer SecondStageTime = 50;

    public void hello() {
        System.out.println("hello");
    }

    public  double countTotal(Integer workTime) {
        double total = 0;
        while (true) {
            if (workTime > 100) {
                return -1;
            } else if (workTime > 50){
                total += (workTime-50)*salary*3;
                workTime = 50;
            } else if (workTime > 40) {
                total += (workTime-40)*salary*1.5;
                workTime = 40;
            } else if (workTime >= 0) {
                total += workTime*salary;
                return  total;
            } else {
                return -2;
            }
        }
    }
}
