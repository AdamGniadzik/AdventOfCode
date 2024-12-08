package org.advent.advent2024.day7;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static long solution = 0;
    public static void main(String[] args) {
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day7/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<Long> results = new ArrayList<>();
            List<List<Long>> parts = new ArrayList<>();
            while (br.ready()) {
                String input = br.readLine();
                int indexOf = input.indexOf(":");
                results.add(Long.parseLong(input.substring(0, indexOf)));
                parts.add(new ArrayList<>(Arrays.stream(input.substring(indexOf + 2).split(" ")).map(Long::parseLong).toList()));
            }
            for(int i = 0;i<results.size();i++){
              boolean s =  checkIfPossible(results.get(i), parts.get(i), parts.get(i).get(0), 1);
              if(s){
                  solution += results.get(i);
              }
            }

            System.out.println(solution);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkIfPossible(long result, List<Long> values, long current, int currentIndex){
        if(currentIndex == values.size() - 1){
            if(current + values.get(currentIndex) == result || current * values.get(currentIndex) == result || Long.parseLong(Long.toString(current) + Long.toString(values.get(currentIndex))) == result){
                return true;
            }else{
                return false;
            }
        }
        else{
            boolean s1 = checkIfPossible(result, values, current + values.get(currentIndex), currentIndex + 1);
            boolean s2 = checkIfPossible(result, values, current * values.get(currentIndex), currentIndex + 1);
            boolean s3 = checkIfPossible(result, values, Long.parseLong(Long.toString(current) + Long.toString(values.get(currentIndex))), currentIndex + 1);
            if(s1){
                return s1;
            }
            if(s2){
                return s2;
            }
            if(s3){
                return s3;
            }
        }
        return false;
    }

}