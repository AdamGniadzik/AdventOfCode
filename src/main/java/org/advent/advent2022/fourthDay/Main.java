package org.advent.advent2022.fourthDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        int score = 0;
        //First part
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/fourthDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Part 1
            while (br.ready()) {
                String[] elf = br.readLine().split(",");
                String firstElfNumbers[] = elf[0].split("-");
                String secondElfNumbers[] = elf[1].split("-");
                int firstElfBegin = Integer.parseInt(firstElfNumbers[0]);
                int firstElfEnd = Integer.parseInt(firstElfNumbers[1]);
                int secondElfBegin = Integer.parseInt(secondElfNumbers[0]);
                int secondElfEnd = Integer.parseInt(secondElfNumbers[1]);
                if(firstElfBegin <= secondElfBegin && firstElfEnd >= secondElfEnd){
                    score ++;
                }else if (secondElfBegin <= firstElfBegin && secondElfEnd >= firstElfEnd){

                    score ++;
                }
            }
            System.out.println(score);


            score=0;
            br.close();
            is = org.advent.advent2022.thirdDay.Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/fourthDay/input.txt");
            br = new BufferedReader(new InputStreamReader(is));

            //Part 2
            while (br.ready()) {
                String[] elf = br.readLine().split(",");
                String[] firstElfNumbers = elf[0].split("-");
                String[] secondElfNumbers = elf[1].split("-");
                int firstElfBegin = Integer.parseInt(firstElfNumbers[0]);
                int firstElfEnd = Integer.parseInt(firstElfNumbers[1]);
                int secondElfBegin = Integer.parseInt(secondElfNumbers[0]);
                int secondElfEnd = Integer.parseInt(secondElfNumbers[1]);
                if(firstElfEnd < secondElfBegin || firstElfBegin > secondElfEnd){

                }else{
                    score++;
                }
            }

            System.out.println(score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
