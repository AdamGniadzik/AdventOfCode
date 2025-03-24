package org.advent.advent2016.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day19/input.txt"));
        int input = Integer.parseInt(br.readLine());
        stage1(input);
        stage2(input);
        stage2BruteForce(input);

    }

    static void stage1(int numberOfElves) {
         boolean[] tab = new boolean[numberOfElves];
         int removedElves = 0;
         int pointer = 0;
         while(!(removedElves == numberOfElves - 1)){
             if(tab[pointer] == true){
                 pointer = (pointer + 1) % numberOfElves;
             }else{
                 int stealPointer = (pointer + 1) % numberOfElves;
                 while(true){
                     if(!tab[stealPointer]){
                         tab[stealPointer] = true;
                         removedElves++;
                         break;
                     }else{
                         stealPointer = (stealPointer + 1) % numberOfElves;
                     }
                 }
                 pointer = (pointer + 1) % numberOfElves;
             }
         }
         for(int i =0;i<numberOfElves;i++){
             if(!tab[i]){
                 System.out.println(i + 1);
                 break;
             }
         }
    }

    static void stage2BruteForce(int numberOfElves) {
        ArrayList<Elf> circle = new ArrayList<>();
        for(int i =0;i<numberOfElves;i++){
            circle.add(new Elf(i+1));
        }
        int pointer = 0;
        int circleSize = numberOfElves;
        int elfToDelete = 0;
        while(circleSize != 1){
            elfToDelete =  (( circleSize / 2 ) + pointer) % circleSize;
            circle.remove(elfToDelete);
            if(elfToDelete < circleSize / 2){
                pointer = pointer % circleSize;
            }else{
                pointer = (pointer + 1) % circleSize;
            }
            circleSize--;
        }
        System.out.println(circle.get(0).index);
        // System.out.println(Arrays.toString(tab));
    }

    static void stage2(int numberOfElves) {
        int closest = (int)(Math.log(numberOfElves) / Math.log(3));
        int closestPow = (int) Math.pow(3, closest);
        int nextPow = (int) Math.pow(3, closest + 1);
        int mid = (nextPow - closestPow) / 2;
        int result = 0;
        if(numberOfElves > closestPow + mid){
            result = ((numberOfElves - closestPow - mid) * closest) + mid;
        }else{
             result = (int) (numberOfElves - Math.pow(3, closest));
        }
        if(result == 0){
            System.out.println((int)Math.pow(3, closest));
        }else{
            System.out.println(result);
        }

    }


    public record Elf(int index){}


}
