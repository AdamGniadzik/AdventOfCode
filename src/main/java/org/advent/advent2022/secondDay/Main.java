package org.advent.advent2022.secondDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int score = 0;
        Map<Character, Integer> pointMap = new HashMap<>() {
            {
                put('X', 1);
                put('Y', 2);
                put('Z', 3);
            }
        };
        Map<Character, Character> beatMap = new HashMap<>() {
            {
                put('X', 'Y');
                put('Y', 'Z');
                put('Z', 'X');
            }
        };
        Map<Character, Character> reverseBeatMap = new HashMap<>() {
            {
                put('Y', 'X');
                put('Z', 'Y');
                put('X', 'Z');
            }
        };
        //First part
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/secondDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                String line = br.readLine();
                int first =  line.charAt(0) + 23;
                int second = line.charAt(2);
                if(beatMap.get((char)first) == second){
                    score += (pointMap.get((char) second) +  6);
                }
                else if(first == second){
                    score += (pointMap.get((char)second) + 3);
                }
                else {
                    score += pointMap.get((char)second);
                }
            }
            System.out.println(score);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Second part
        score = 0;
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/secondDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                String line = br.readLine();
                int first =  line.charAt(0) + 23;
                int second = line.charAt(2);

                if(second == 'X'){
                    score += pointMap.get(reverseBeatMap.get((char)first));
                }
                else if(second == 'Y'){
                    score += pointMap.get((char) first) + 3;
                }
                else if(second == 'Z'){
                    score += pointMap.get(beatMap.get((char) first)) + 6;
                }
            }
            System.out.println(score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
