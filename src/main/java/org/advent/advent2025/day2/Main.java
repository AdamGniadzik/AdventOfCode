package org.advent.advent2025.day2;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2025/day2/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String input = br.readLine();
            long counterStage1 = 0;
            long counterStage2 = 0;
            for (String range : input.split(",")) {
                int dashIndex = range.indexOf("-");
                long start = Long.parseLong(range.substring(0, dashIndex));
                long end = Long.parseLong(range.substring(dashIndex + 1));
                for (long i = start; i <= end; i++) {
                    String number = Long.toString(i);
                        if (checkNumberStage1(number)) {
                            counterStage1 += i;
                            counterStage2 += i;
                        } else if (checkNumberStage2(number)) {
                            counterStage2 += i;
                        }
                }
            }
            System.out.println(counterStage1);
            System.out.println(counterStage2);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean checkNumberStage1(String number) {
        return number.substring(0, number.length() / 2).equals(number.substring(number.length() / 2));
    }

    public static boolean checkNumberStage2(String number) {
        for(int i =1;i<=number.length() / 2;i++){
            String part = number.substring(0, i);
            int currentStart = 0;
            while(currentStart < number.length() && part.length() + currentStart <= number.length()){
                String comparePart = number.substring(currentStart, part.length() + currentStart);
                if(comparePart.equals(part)){
                    currentStart  += part.length();
                }else{
                    break;
                }
            }
            if(currentStart >= number.length()){
                return true;
            }
        }
        return false;
    }
}