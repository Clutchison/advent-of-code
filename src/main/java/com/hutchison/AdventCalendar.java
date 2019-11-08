package com.hutchison;

import com.hutchison.day.Day;
import com.hutchison.day.day1.Day1;
import com.hutchison.day.day2.Day2;
import com.hutchison.util.Console;
import lombok.Value;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Value
public class AdventCalendar {

    private static final Map<Integer, Class> days = new HashMap<Integer, Class>() {{
        put(1, Day1.class);
        put(2, Day2.class);
    }};

    public void run() {
        runPart(initDay());
    }

    private static void runPart(Day day) {
        String request = Console.getInstance().request("Enter the part that you would like to run: 1/2");
        long startTime = System.currentTimeMillis();
        switch (request) {
            case "1":
                day.part1();
                break;
            case "2":
                day.part2();
                break;
        }
        long endTime = System.currentTimeMillis();
        long total = endTime - startTime;
        Console.getInstance().print("Run time: " + total + " milliseconds.");
    }

    private static Day initDay() {
        String input = Console.getInstance().request("Enter the day that you would like to run" +
                "\n Valid days: " + days.keySet().toString());
        Class c = days.get(Integer.valueOf(input));
        if (c == null) throw new RuntimeException("No class for input: " + input);
        Constructor<?> ctor = null;
        try {
            ctor = c.getConstructor();
            return (Day) ctor.newInstance(new Object[]{});
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Error instantiating day class: " + e);
        }
    }
}
