public class Demo {

    public int calday(int total) {
        int day = 0, x1, x2;
        float t, s = 0 ;
        x1 = total;
        s = x1;
        while (s > 0  &&  s<=1000) {
            x2 = x1 / 2;
            t = x1 / 2.0f;
            if (t > x2) {
                x2 += 1;
            }
            s -= x2;
            x1 = x2;
            day++;
        }
        return(day);
    }
}
