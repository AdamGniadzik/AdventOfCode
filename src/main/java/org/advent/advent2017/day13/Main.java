package org.advent.advent2017.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day13/input.txt"));
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        while (br.ready()) {
            String[] line = br.readLine().split(": ");
            int first = Integer.parseInt(line[0]);
            int second = Integer.parseInt(line[1]);
            max = Math.max(max, first);
            map.put(first, second - 1);
        }
        System.out.println(stage1(map, max, false, 0));
        System.out.println(stage2(map, max));



    }
    public static int stage1(Map<Integer, Integer> map, int max, boolean isStage2, int delay){
        int result = 0;
        int current = 0;
        int seconds = -1 + delay;
        while(current <= max){
            if(map.getOrDefault(current, 0) == 0){
                current++;
                seconds++;
                continue;
            }
            seconds++;
            int range = map.get(current);
            int move = seconds / range;
            if(move % 2 == 0){
                move = seconds % range;
            }else if(move > 0){
                move = seconds - (move * range) - (range);
            }
            if(0 == move){
                if(isStage2){
                    return Integer.MAX_VALUE;
                }
                result += current * (range + 1);
            }
            current++;
        }

        return result;
    }

    public static int stage2(Map<Integer, Integer> map, int max){
        int delay = 0;
        while(stage1(map, max, true, delay) != 0){
            delay++;
        }
       return delay;
    }
}
