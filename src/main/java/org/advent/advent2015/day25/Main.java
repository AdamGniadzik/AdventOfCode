package org.advent.advent2015.day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day25/input.txt"));
        int row = Integer.parseInt(br.readLine());
        int column = Integer.parseInt(br.readLine());
        int requiredRow = row + column - 1;
        long operations = 1;
        int counter = 1;
        for(int i = 1; i <requiredRow;i++){
            operations += counter;
            counter++;
        }
        long start = 20151125;
        for(int i = 0;i<operations - 1 + (requiredRow - row); i++){
            start = ( start * 252533 ) % 33554393;
        }
        System.out.println(start);
    }


}
