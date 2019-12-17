package com.hutchison.calendar;

import com.hutchison.calendar.days.y2019.day1.Day1;
import com.hutchison.calendar.days.y2019.day2.Day2;
import com.hutchison.util.Console;
import lombok.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Value
public class AdventCalendar {

    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_PART = 2;

    // 2018
//    private static final Map<Integer, Class> days = new HashMap<Integer, Class>() {{
//        put(1, Day1Year18.class);
//        put(2, Day2Year18.class);
//        put(3, Day3Year18.class);
//    }};

    private static final Map<Integer, Class> days = new HashMap<Integer, Class>() {{
        put(1, Day1.class);
        put(2, Day2.class);
    }};

    public void run() {
        do {
            String input = Console.getInstance().request("Enter the day that you would like to run" +
                    "\n Valid days: " + days.keySet().toString());
            runPart(initDay(input));
        } while (Console.getInstance().request("Again? (y/n)").equals("y"));
    }

    private static void runPart(Day day) {
        String request = Console.getInstance().request("Enter the part that you would like to run: 1/2");
        if (request.equals("")) request = String.valueOf(DEFAULT_PART);
        long startTime = System.currentTimeMillis();
        switch (request) {
            case "1":
                day.part1();
                break;
            case "2":
                day.part2();
                break;
            default:
                throw new RuntimeException("Invalid part");
        }
        long endTime = System.currentTimeMillis();
        long total = endTime - startTime;
        Console.getInstance().print("Run time: " + total + " milliseconds.");
    }

    private static Day initDay(String dayNum) {
        Class c = dayNum.equals("") ? days.get(DEFAULT_DAY) : days.get(Integer.valueOf(dayNum));
        if (c == null) throw new RuntimeException("No class for input: " + dayNum);
        Constructor<?> ctor = null;
        try {
            ctor = c.getConstructor();
            return (Day) ctor.newInstance(new Object[]{});
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Error instantiating day class: " + e);
        }
    }
}
