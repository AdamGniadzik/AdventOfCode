package org.advent.advent2022.day6;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            final int PART_ONE_CONDITION = 4;
            final int PART_TWO_CONDITION = 4;
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/day6/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Map<Character, Integer> markerMap = new HashMap<>();
            String input = br.readLine();
            int steps = 0;
            int subStep = 0;
            while (steps < input.length()) {
                Integer prevValue = markerMap.put(input.charAt(steps), subStep);
                if (prevValue != null) {
                    steps = steps - (subStep - prevValue);
                    subStep = 0;
                    markerMap.clear();
                }
                //Replace variable to complete both parts of challenge.
                if (subStep == PART_TWO_CONDITION) {
                    System.out.println(markerMap);
                    System.out.println(steps + 1);
                    break;
                }
                steps++;
                subStep++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
