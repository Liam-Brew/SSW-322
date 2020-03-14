package clock;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

class Clock {
    // Data fields
    protected Calendar calendar;
    private Calendar alarmCalendar;
    private Radio radio;
    private String timeLabel;
    private boolean alarmSet;
    private int snoozes = 0;

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
        this.alarmSet = true;

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

    public void setTime(int h, int m, String label) {
        if (h > 12 || m > 59 || label.equals("AM") || label.equals("PM")) {
            throw new IllegalArgumentException("Error: invalid time");
        }
        this.calendar.set(Calendar.HOUR, h);
        this.calendar.set(Calendar.MINUTE, m);
        this.timeLabel = label;
        System.out.println(getTime());
    }

    public boolean alarmActivate() {
        return (this.alarmCalendar.get(Calendar.HOUR) == this.calendar.get(Calendar.HOUR))
                && (this.alarmCalendar.get(Calendar.MINUTE) == this.calendar.get(Calendar.MINUTE)) && alarmSet;
    }

    public void alarm() {
        System.out.println("Buzz Buzz Buzz");
    }

    public void snooze() {
        if (radio.getActive() && snoozes <= 2) {
            this.alarmCalendar.add(Calendar.MINUTE, 9);
            System.out.println("Snooze was pressed.");
            snoozes++;
        } else if (snoozes > 2) {
            System.out.println("Error: snooze was already pressed twice. Time to wake up!");
        }
    }

    public void setAlarm(int hour, int minute) {
        this.alarmSet = true;
        this.alarmCalendar.add(Calendar.HOUR, hour);
        this.alarmCalendar.add(Calendar.MINUTE, minute);
    }

    public void deleteAlarm() {
        if (this.alarmCalendar == null)
            throw new IllegalStateException("Error: alarm already deleted!");
        this.alarmCalendar = null;
        System.out.println("The alarm was turned off.");
    }

    public void stopAlarm() {
        this.alarmSet = false;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        // Hardcoded initial alarm ... can be changed
        Clock clock = new Clock(8, 0, "AM", 8, 5, 1060.1);

        System.out.print("Initial Time: ");
        clock.showTime();

        clock.radio.turnOn();

        String initial = clock.getTime();

        String menuOption = "blank";

        while (true) {

            if (!initial.equals(clock.getTime())) {
                clock.showTime();
                initial = clock.getTime();
            }

            if (clock.alarmActivate()) {
                clock.alarm();
                clock.snooze();
            }

            if (menuOption.equals("blank")) {
                System.out.print("Display one-time only setup menu? (y/n): ");
                menuOption = input.nextLine().toLowerCase();
            }

            if (menuOption.equals("y")) {

                menuOption = null;

                System.out.print("\n#Welcome to the Alarm Clock Feature Menu#\nIt is now: ");
                clock.showTime();
                System.out.println("Please enter the letter corresponding to your feature.");
                System.out.println("a: Set Alarm");
                System.out.println("b: Remove Alarm");
                System.out.println("c: Set Time");
                System.out.println("d: Turn Radio On");
                System.out.println("e: Turn Radio Off");
                System.out.println("f: Set Station");
                System.out.println("g: Set Volume");
                System.out.println("h: Show Time");
                System.out.println("i: Snooze");
                System.out.println("x: Exit System");

                String choice = input.nextLine().toLowerCase();

                featureLoop: switch (choice) {
                    case "a":
                        System.out.println("Selected: Set Alarm");
                        System.out.print("Enter hour: ");
                        int ha = Integer.valueOf(input.nextLine());
                        System.out.print("Enter minute: ");
                        int ma = Integer.valueOf(input.nextLine());
                        clock.setAlarm(ha, ma);
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "b":
                        System.out.println("Selected: Remove Alarm");
                        clock.deleteAlarm();
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "c":
                        System.out.println("Selected: Set Time");
                        System.out.print("Enter hour: ");
                        int ht = Integer.valueOf(input.nextLine());
                        System.out.print("Enter minute: ");
                        int mt = Integer.valueOf(input.nextLine());
                        System.out.print("AM or PM: ");
                        String lab = input.nextLine();
                        clock.setTime(ht, mt, lab);
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "d":
                        System.out.println("Selected: Turn Radio On");
                        clock.radio.turnOn();
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "e":
                        System.out.println("Selected: Turn Radio Off");
                        clock.radio.turnOff();
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "f":
                        System.out.println("Selected: Set Station");
                        System.out.print("Enter FM or AM station: ");
                        double stat = Double.valueOf(input.nextLine());
                        clock.radio.setStation(stat);
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "g":
                        System.out.println("Selected: Set Volume");
                        System.out.print("Enter volume level (1 - 10): ");
                        int vol = Integer.valueOf(input.nextLine());
                        clock.radio.setVolume(vol);
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "h":
                        System.out.println("Selected: Show Time");
                        clock.showTime();
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "i":
                        System.out.println("Selected: Snooze");
                        clock.snooze();
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;

                    case "x":
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break featureLoop;

                    default:
                        System.out.println("Error: invalid choice!");
                        clock.tickIncrement();
                        TimeUnit.SECONDS.sleep(1);
                        break;
                }
            }
            clock.tickIncrement();
            TimeUnit.SECONDS.sleep(1);

        }

    }

}