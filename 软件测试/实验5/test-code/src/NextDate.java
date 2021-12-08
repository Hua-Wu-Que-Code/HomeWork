public class NextDate {
    private Integer InitDay = 1;
    private Integer InitMonth = 1;


    public String nextDate(String date) {
        String[] dates = date.split("-");
        Integer year = Integer.parseInt(dates[0]);
        Integer month = Integer.parseInt(dates[1]);
        Integer day = Integer.parseInt(dates[2]);
        if (month == 2 ) {
            if (ifLeapYear(year)) {
                if (day == 29) {
                    month++;
                    day = InitDay;
                } else {
                    day++;
                }
            } else {
                if (day == 28) {
                    month++;
                    day = InitDay;
                } else {
                    day++;
                }
            }
        } else if (month == 12) {
            if (day == 31) {
                year++;
                month = InitMonth;
                day = InitDay;
            } else {
                day++;
            }
        } else if (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10) {
            if (day == 31) {
                month++;
                day = InitDay;
            } else {
                day++;
            }
        } else {
            if (day == 30) {
                month++;
                day = InitDay;
            } else {
                day++;
            }
        }
        return year+"-"+month+"-"+day;
    }

    private boolean ifLeapYear(Integer year) {
        if ((year%4==0&&year%100!=0)||year%400==0 ) {
            return true;
        }
        return false;
    }
}
