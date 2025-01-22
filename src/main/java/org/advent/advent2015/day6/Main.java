package org.advent.advent2015.day6;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day6/input.txt"));
        int[][] lights = new int[1000][1000];
        int[][] lights2 = new int[1000][1000];
        while (br.ready()) {
            String[] input = br.readLine().split(" ");
            if (input.length == 5) {
                String[] firstCoordinates = input[2].split(",");
                String[] secondCoordinates = input[4].split(",");
                if (input[1].equals("off")) {
                    makeLight(lights, false, 0, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
                    makeLight2(lights2, false, -1, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
                } else {
                    makeLight(lights, false, 1, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
                    makeLight2(lights2, false, 1, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
                }
            } else {
                String[] firstCoordinates = input[1].split(",");
                String[] secondCoordinates = input[3].split(",");
                makeLight(lights, true, 0, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
                makeLight2(lights2, true, 0, Integer.parseInt(firstCoordinates[0]), Integer.parseInt(secondCoordinates[0]), Integer.parseInt(firstCoordinates[1]), Integer.parseInt(secondCoordinates[1]));
            }

        }
        int counter =0;
        int counter2 =0;
        for(int i =0;i<lights.length;i++){
            for(int j =0;j<lights.length;j++){
                if(lights[i][j] == 1){
                    counter++;
                }
                counter2+=lights2[i][j];
            }
        }
        System.out.println(counter);
        System.out.println(counter2);

    }

    private static void makeLight(int[][] lights, boolean toggle, int on, int iFrom, int iTo, int jFrom, int jTo) {
        for (int i = iFrom; i <= iTo; i++) {
            for (int j = jFrom; j <= jTo; j++) {
                if (toggle) {
                    lights[i][j] = lights[i][j] == 1 ? 0 : 1;
                } else lights[i][j] = on;
            }
        }
    }
    private static void makeLight2(int[][] lights, boolean toggle, int on, int iFrom, int iTo, int jFrom, int jTo) {
        for (int i = iFrom; i <= iTo; i++) {
            for (int j = jFrom; j <= jTo; j++) {
                if (toggle) {
                    lights[i][j]+=2;
                } else {
                    if(on == -1 && lights[i][j] == 0){
                        lights[i][j] = 0;
                    }else{
                        lights[i][j] += on;
                    }
                }
            }
        }
    }
}
