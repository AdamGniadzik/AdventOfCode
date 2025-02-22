package org.advent.advent2016.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day2/input.txt"));
        Map<Character, List<Integer>> moves = Map.of('D', List.of(0, 1), 'L', List.of(-1, 0), 'U', List.of(0, -1), 'R', List.of(1, 0));
        List<String> input = new ArrayList<>();
        while (br.ready()) {
            input.add(br.readLine());
        }
        stage1(input,moves);
        System.out.println();
        stage2(input,moves);

    }

    public static void stage1(List<String> input, Map<Character, List<Integer>> moves) {
        List<List<Integer>> touchpad = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
        int x = 1;
        int y = 1;
        for (String inputLine : input) {
            for (char move : inputLine.toCharArray()) {
                x = x + moves.get(move).get(0);
                y = y + moves.get(move).get(1);
                x = x < 0 ? 0 : (Math.min(x, 2));
                y = y < 0 ? 0 : (Math.min(y, 2));
            }
            System.out.print(touchpad.get(y).get(x));
        }
    }

    public static void stage2(List<String> input, Map<Character, List<Integer>> moves) {
        List<List<Integer>> touchpad = List.of(List.of(-1, -1, 1, -1, -1), List.of(-1, 2, 3, 4, -1), List.of(5, 6, 7, 8, 9), List.of(-1, 10, 11, 12, -1), List.of(-1, -1, 13, -1, -1));
        int x = 0;
        int y = 2;
        for (String inputLine : input) {
            for (char move : inputLine.toCharArray()) {
                int newX = x + moves.get(move).get(0);
                newX  = newX < 0 ? 0 : (Math.min(newX, 4));
                int newY = y + moves.get(move).get(1);
                newY =  newY < 0 ? 0 : (Math.min(newY, 4));
                if(touchpad.get(newY).get(newX) != -1){
                    x = newX;
                    y = newY;
                }

            }
            System.out.print(touchpad.get(y).get(x) > 9 ? (char) ('A' + (touchpad.get(y).get(x) - 10)) : (char)('0' + touchpad.get(y).get(x)));
        }
    }
}
