package org.advent.advent2024.day13;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            long result = 0;
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day13/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            while (br.ready()) {
                boolean isPart2 = true;
                String Ainput = br.readLine();
                String Binput = br.readLine();
                String PRIZE = br.readLine();
                br.readLine();
                long addition = 10000000000000L;
                long x1 = Integer.parseInt(Ainput.substring(Ainput.indexOf("X+") + 2, Ainput.indexOf(",")));
                long y1 = Integer.parseInt(Ainput.substring(Ainput.indexOf("Y+") + 2));

                long x2 = Integer.parseInt(Binput.substring(Ainput.indexOf("X+") + 2, Binput.indexOf(",")));
                long y2 = Integer.parseInt(Binput.substring(Binput.indexOf("Y+") + 2));

                long rx = Integer.parseInt(PRIZE.substring(PRIZE.indexOf("X=") + 2, PRIZE.indexOf(",")));
                long ry = Integer.parseInt(PRIZE.substring(PRIZE.indexOf("Y=") + 2));
                if(isPart2){
                    rx = rx + addition;
                    ry = ry + addition;
                }
                double B = (double) ((rx * y1) - (ry * x1)) / ((x2*y1) - (y2*x1));
                if(B == Math.floor(B)){
                    double A = ( rx - (B * x2) ) / x1;
                    if(A == Math.floor(A)){
                        result += A * 3 + B*1;
                    }
                }
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}