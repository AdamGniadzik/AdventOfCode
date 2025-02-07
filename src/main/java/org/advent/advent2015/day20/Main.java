package org.advent.advent2015.day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static Map<Long, Long> memo = new HashMap<>();
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day19/input.txt"));
        long start = 1;
        long desired = 34000000L;
        while(true){
            long result = getNumberOfPresents(start);
            if(result < desired){
                start ++;
            }
            else {
                System.out.println( start + "  " + result);
                break;
            }
        }
        while(true){
            long result = getNumberOfPresentsStage2(start);
            if(result < desired){
                start ++;
            }
            else {
                System.out.println( start + "  " + result);
                break;
            }
        }
    }

    public static long getNumberOfPresents(long house){
        if(house% 2 == 0 && memo.containsKey(house / 2)){
            return memo.get(house / 2) + house * 10;
        }
        long result = 0;
        for(int i =1;i<=house;i++){
            if(house % i == 0){
                result += (long)(i * 10L);
            }
        }
        memo.put(house, result);
        return result;
    }

    public static long getNumberOfPresentsStage2(long house){
        long result = 0;
        for(int i =1;i<=house;i++){
            if(house % i == 0 && house / i < 50){
                result += (long)(i * 11L);
            }
        }
        return result;
    }


}
