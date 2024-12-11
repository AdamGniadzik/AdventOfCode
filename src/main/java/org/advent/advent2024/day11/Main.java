package org.advent.advent2024.day11;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day11/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Map<Long, Long> map = new HashMap<>();
            while(br.ready()){
                for(String number : br.readLine().split(" ")){
                    map.put(Long.parseLong(number), 1L);
                }
            }
            int counter = 0;
            while(counter <  75){
                Map<Long, Long> output = new HashMap<>();
                for(Long key: map.keySet()){
                    String stoneInString = Long.toString(key);
                    if(key == 0){
                        output.put(1L, map.get(key));
                    }else if(stoneInString.length() % 2 == 0){
                        long firstNumber = Integer.parseInt(stoneInString.substring(0, stoneInString.length() / 2));
                        long secondNumber = Integer.parseInt(stoneInString.substring(stoneInString.length() / 2));
                        output.put(firstNumber, output.getOrDefault(firstNumber, 0L) + map.get(key));
                        output.put(secondNumber, output.getOrDefault(secondNumber, 0L) + map.get(key));

                    }else{
                        output.put(key * 2024, map.get(key));
                    }
                }
                map = output;
                counter++;

            }
            long result = 0;
            for(long value : map.values()){
                result += value;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}