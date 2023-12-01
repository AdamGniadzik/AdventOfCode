package org.advent.advent2022.day10;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/day10/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Set<Integer> set = Set.of(20, 60, 100, 140, 180, 220);
            int counter = 1;
            int state = 1;
            int sum = 0;
            //Part 1
//            while (br.ready()) {
//                String line = br.readLine();
//                counter++;
//                if (line.startsWith("addx")) {
//                    if (set.contains(counter)) {
//                        sum += state * counter;
//                    }
//                    state += Integer.parseInt(line.substring(5));
//                    counter++;
//                }
//                if (set.contains(counter)) {
//                    sum += state * counter;
//                }
//
//            }
//            System.out.println(sum);
            counter = 0;
            state = 1;
            while (br.ready()) {
                String line = br.readLine();
                if (counter == 40) {
                    System.out.print("\n");
                    counter = 0;
                }
                printPixel(counter, state);
                counter++;
                if (line.startsWith("addx")) {
                    if (counter == 40) {
                        counter = 0;
                        System.out.print("\n");
                    }
                    printPixel(counter, state);
                    state += Integer.parseInt(line.substring(5));
                    counter++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printPixel(int counter, int state){
        if(counter + 1 >= state && counter + 1 <= state + 2){
            System.out.print('#');
        }else{
            System.out.print('.');
        }
    }

}
