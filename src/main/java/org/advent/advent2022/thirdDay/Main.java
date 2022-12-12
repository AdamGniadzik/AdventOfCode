package org.advent.advent2022.thirdDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        int score = 0;
        InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/thirdDay/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //Part 1
        while (br.ready()) {
            String line = br.readLine();
            Set<Character> firstPartSet = line.substring(0, line.length()/2 ).chars().mapToObj((e->(char)e)).collect(Collectors.toSet());
            Set<Character> secondPartSet = line.substring(line.length()/2).chars().mapToObj((e->(char)e)).collect(Collectors.toSet());
            for(Character ch : firstPartSet){
                if(secondPartSet.contains(ch)){
                    if(ch > 96){
                        score += ((int)ch - 96 );
                    }else{
                        score += ((int)ch - 38 );
                    }
                    break;
                }
            }
        }
        System.out.println("Sum of priority is equal to :"+score);
        br.close();

        is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/thirdDay/input.txt");
        br = new BufferedReader(new InputStreamReader(is));

        score = 0;
        //Part 2
        while (br.ready()) {
            Set<Character> firstElf = br.readLine().chars().mapToObj(e->(char)e).collect(Collectors.toSet());
            Set<Character> secondElf = br.readLine().chars().mapToObj(e->(char)e).collect(Collectors.toSet());
            Set<Character> thirdElf = br.readLine().chars().mapToObj(e->(char)e).collect(Collectors.toSet());
            for(Character ch : firstElf){
                if(secondElf.contains(ch) && thirdElf.contains(ch)){
                    if(ch > 96){
                        score += ((int)ch - 96 );
                    }else{
                        score += ((int)ch - 38 );
                    }
                    break;
                }
            }
        }
        System.out.println("Sum of grouped priority is equal to :"+score);
        br.close();
    }
}
