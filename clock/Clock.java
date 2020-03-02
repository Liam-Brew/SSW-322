package clock;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Clock {
    // Data fields
    protected Calendar calendar;
    private Calendar alarmCalendar;
    private Radio radio;
    private String timeLabel;

    // Constructor
    public Clock(int hour, int minute, String timeLabel, int alarmHour, int alarmMin, double frequency) {
        // Date setting
        this.calendar = Calendar.getInstance();

        this.calendar.set(Calendar.HOUR, hour);
        this.calendar.set(Calendar.MINUTE, minute);

        this.alarmCalendar = Calendar.getInstance();
        this.alarmCalendar.set(Calendar.HOUR, alarmHour);
        this.alarmCalendar.set(Calendar.MINUTE, alarmMin);

        this.timeLabel = timeLabel;

        // Radio setting
        this.radio = new Radio();
        this.radio.setStation(frequency);
    }

    // Methods
    public void tickIncrement() {
        this.calendar.add(Calendar.SECOND, 1);
    }

    public String getTime() {
        String time;
        if (this.calendar.get(Calendar.MINUTE) < 10.0) {
            time = String.valueOf(this.calendar.get(Calendar.HOUR)) + ":0"
                    + String.valueOf(this.calendar.get(Calendar.MINUTE) + " " + timeLabel);
        } else {
            time = String.valueOf(this.calendar.get(Calendar.HOUR)) + ":"
                    + String.valueOf(this.calendar.get(Calendar.MINUTE) + " " + timeLabel);

        }
        return time;
    }

    public void showTime() {
        System.out.println(getTime());
    }

    public boolean alarmActivate() {
        return (this.alarmCalendar.get(Calendar.HOUR) == this.calendar.get(Calendar.HOUR))
                && (this.alarmCalendar.get(Calendar.MINUTE) == this.calendar.get(Calendar.MINUTE));
    }

    public void alarm() {
        System.out.println("Buzz Buzz Buzz");
    }

    public void snooze() {
        if (radio.getActive()) {
            this.alarmCalendar.add(Calendar.MINUTE, 9);
            System.out.println("Snooze was pressed.");
        }
    }

    public void setAlarm(int hour, int minute) {
        this.alarmCalendar.add(Calendar.HOUR, hour);
        this.alarmCalendar.add(Calendar.MINUTE, minute);
    }

    public void deleteAlarm() {
        if (this.alarmCalendar == null)
            throw new IllegalStateException("Error: alarm already deleted!");
        this.alarmCalendar = null;
        System.out.println("The alarm was turned off.");
    }

    public static void main(String[] args) throws InterruptedException {

        // 1 minute alarm
        Clock clock = new Clock(8, 0, "AM", 8, 1, 1060.1);

        System.out.print("Initial Time: ");
        clock.showTime();

        clock.radio.turnOn();

        String initial = clock.getTime();

        while (clock.calendar.get(Calendar.MINUTE) < 30) {
            if (!initial.equals(clock.getTime())) {
                clock.showTime();
                initial = clock.getTime();
            }

            if (clock.alarmActivate()) {
                clock.alarm();
                clock.snooze();
            }

            clock.tickIncrement();
            TimeUnit.SECONDS.sleep(1);

        }

        clock.deleteAlarm();

    }

}