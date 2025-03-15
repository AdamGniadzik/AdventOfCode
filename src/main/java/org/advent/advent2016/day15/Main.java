package org.advent.advent2016.day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
// 249927 too high


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day15/input.txt"));
        List<Disc> input = new ArrayList<>();
        int index = 1;
        while (br.ready()) {
            String[] splitInput = br.readLine().split(" ");
            input.add(new Disc(Integer.parseInt(splitInput[3]), Integer.parseInt(splitInput[11].substring(0, splitInput[11].indexOf("."))), index));
            index++;
        }
        findSolution(input);
        input.add(new Disc(11, 0, input.size() + 1));
        findSolution(input);
    }

    public static void findSolution(List<Disc> input){
        System.out.println(input);
        int time = 0;
        while(true){
            boolean allAt0 = true;
            int firstPosition = (input.get(0).currentPosition + time + input.get(0).index) % input.get(0).positions;
            for(Disc disc : input){
                int position = (disc.currentPosition + time + disc.index) % disc.positions;
                if(position != firstPosition){
                    allAt0 = false;
                    break;
                }
            }
            if(allAt0){
                System.out.println(time);
                break;
            }
            time++;

        }
    }

    public static class Disc {
        int positions;
        int currentPosition;
        int index;

        @Override
        public String toString() {
            return "#" + index +" " +currentPosition + " / " + positions;
        }

        public Disc(int positions, int currentPosition, int index) {
            this.positions = positions;
            this.currentPosition = currentPosition;
            this.index = index;

        }
    }

}
