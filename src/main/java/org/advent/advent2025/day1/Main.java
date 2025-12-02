package org.advent.advent2025.day1;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2025/day1/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int start = 50;
            int counter = 0;
            int withTimes = 0;
            while (br.ready()) {
                boolean flag = start == 0;
                String input = br.readLine();
                int times = Integer.parseInt(input.substring(1)) / 100;
                int move = Integer.parseInt(input.substring(1)) % 100;
                if (input.charAt(0) == 'L') {
                    start -= move;
                    if (start < 0) {
                        if (!flag) {
                            withTimes++;
                            flag = true;
                        }
                        start = 100 + start;
                    }
                } else {
                    start += move;
                    if (start > 99) {
                        if (!flag) {
                            withTimes++;
                            flag = true;
                        }
                        start = start - 100;
                    }
                }
                withTimes += times;
                if (start == 0) {
                    withTimes += flag ? 0 : 1;
                    counter++;
                }
            }
            System.out.println(counter);
            System.out.println(withTimes);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
