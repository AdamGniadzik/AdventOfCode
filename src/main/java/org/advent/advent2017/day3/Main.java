package org.advent.advent2017.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day3/input.txt"));
        int input = Integer.parseInt(br.readLine());
        stage1(input);
        stage2(input);

    }

    public static void stage1(int input) {
        int borderSize = 3;
        int counter = 0;
        int val = 1;
        while (val < input) {
            val += (borderSize * 4) - 4;
            counter++;
            borderSize += 2;
        }
        while (val > input) {
            val -= (borderSize - 1);
        }
        int x = Math.abs(input - val - (borderSize / 2) - 1);
        System.out.println(x + counter);


    }

    public static void stage2(int input) {
        int borderSize = 3;
        int val = 1;
        while (val < input) {
            val += (borderSize * 4) - 4;
            borderSize += 2;
        }
        int[][] tab = new int[borderSize][borderSize];
        int center = (borderSize / 2);
        tab[center][center] = 1;
        int y = center + 1;
        int x = center + 1;
        borderSize = 3;
        while (true) {
            for (int i = 0; i < borderSize - 1; i++) {
                y--;
                tab[y][x] = calculate(tab, y, x);
                if(tab[y][x] > input){
                    System.out.println(tab[y][x]);
                    return;
                }
            }
            for (int i = 0; i < borderSize - 1; i++) {
                x--;
                tab[y][x] = calculate(tab, y, x);
                if(tab[y][x] > input){
                    System.out.println(tab[y][x]);
                    return;
                }
            }
            for (int i = 0; i < borderSize - 1; i++) {
                y++;
                tab[y][x] = calculate(tab, y, x);
                if(tab[y][x] > input){
                    System.out.println(tab[y][x]);
                    return;
                }
            }
            for (int i = 0; i < borderSize - 1; i++) {
                x++;
                tab[y][x] = calculate(tab, y, x);
                if(tab[y][x] > input){
                    System.out.println(tab[y][x]);
                    return;
                }


            }
            borderSize += 2;
            x++;
            y++;
        }

    }

    public static int calculate(int[][] tab, int y, int x) {
        return tab[y - 1][x - 1] + tab[y - 1][x] + tab[y - 1][x + 1] + tab[y][x + 1] + tab[y][x - 1] + tab[y + 1][x + 1] + tab[y + 1][x - 1] + tab[y + 1][x];

    }


}
