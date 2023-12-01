package org.advent.advent2022.day5;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        try {
            List<String> initialStacksConfig = new ArrayList<>();
            List<Stack<Character>> stacks = new ArrayList<>();
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/day5/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int numberOfLinesToSkip = 0;
            while (br.ready()) {
                String line = br.readLine();
                if (!line.equals("")) {
                    initialStacksConfig.add(line);
                    numberOfLinesToSkip++;
                } else {
                    break;
                }
            }
            for (int stackIndex = 0; stackIndex <= (initialStacksConfig.get(initialStacksConfig.size() - 1).length() - 2) / 4; stackIndex++) {
                Stack<Character> stack = new Stack<>();
                int indexInInput = (stackIndex * 4) + 1;
                for (int i = initialStacksConfig.size() - 2; i >= 0; i--) {
                        if (initialStacksConfig.get(i).charAt(indexInInput) != ' ') {
                            stack.push(initialStacksConfig.get(i).charAt(indexInInput));
                        }
                }
                stacks.add(stack);
            }
            br.close();
            is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/day5/input.txt");
            br = new BufferedReader(new InputStreamReader(is));
            System.out.println(numberOfLinesToSkip);
            for (int i = 0; i < numberOfLinesToSkip + 1; i++) {
                br.readLine();
            }

            //Part 1
//            while (br.ready()) {
//                String[] splittedLine = br.readLine().split(" ");
//                int howMany = Integer.parseInt(splittedLine[1]);
//                int from = Integer.parseInt(splittedLine[3]);
//                int to = Integer.parseInt(splittedLine[5]);
//                for(int i =0;i<howMany ;i++){
//                    stacks.get(to-1).push(stacks.get(from - 1).pop());
//                }
//            }

            //Part 2
            while (br.ready()) {
                String[] splittedLine = br.readLine().split(" ");
                int howMany = Integer.parseInt(splittedLine[1]);
                int from = Integer.parseInt(splittedLine[3]);
                int to = Integer.parseInt(splittedLine[5]);
                List<Character> charsToPush = new ArrayList<>();
                for (int i = 0; i < howMany; i++) {
                    charsToPush.add(stacks.get(from - 1).pop());
                }
                for (int i = 0; i < howMany; i++) {
                   stacks.get(to-1).push(charsToPush.get(howMany - 1 - i));
                }
            }


            StringBuilder result = new StringBuilder();
            for (Stack<Character> stack : stacks) {
                result.append(stack.pop());
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
