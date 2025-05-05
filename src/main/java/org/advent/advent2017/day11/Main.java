package org.advent.advent2017.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day11/input.txt"));
        String input = br.readLine();
        String[] inputList = input.split(",");
        solution(inputList);

    }

    public static void solution(String[] input) {
        int x =0;
        int y= 0;
        int maxDistance = Integer.MIN_VALUE;
        int distance = 0;
        for(String inp : input){

            if(inp.equals("n")){
                y--;
            }else if(inp.equals("s")){
                y++;
            }
            else if(inp.equals("se")){
                x++;
            }
            else if(inp.equals("sw")){
                y++;
                x--;
            }
            else if(inp.equals("ne")){
                y--;
                x++;
            }else if(inp.equals("nw")){
                x--;
            }
            distance = Math.abs(y) + Math.abs(x);
            if(distance > maxDistance){
                maxDistance = distance;
            }
        }
        System.out.println(distance);;
        System.out.println(maxDistance);
    }
}
