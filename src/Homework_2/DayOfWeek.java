package Homework_2;

public enum DayOfWeek {
    MONDAY(40), TUESDAY(32), WEDNESDAY(24), THURSDAY(16), FRIDAY(8), SATURDAY(0), SUNDAY(0);
    private int hours;

    DayOfWeek(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
}
