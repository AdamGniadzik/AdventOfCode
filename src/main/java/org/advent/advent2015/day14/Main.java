package org.advent.advent2015.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day14/input.txt"));
        int maxTime = 2503;
        int maxResult = 0;
        List<Reindeer> reindeers = new ArrayList<>();
        //Stage 1
        while (br.ready()) {
            String[] input = br.readLine().split(" ");
            int speed = Integer.parseInt(input[3]);
            int travelTime = Integer.parseInt(input[6]);
            int rest = Integer.parseInt(input[13]);
            reindeers.add(new Reindeer(speed, travelTime, rest));
            int time = 0;
            int result = 0;
            while (time < maxTime) {
                int realTravelTime = Math.min(travelTime, maxTime - time);
                time += realTravelTime;
                result += realTravelTime * speed;
                time += rest;
            }
            maxResult = Math.max(result, maxResult);
        }
        //Stage 2
        Set<Integer> leadIndexes = new HashSet<>();
        int currentLeadPosition = 0;
        int[] points = new int[reindeers.size()];
        for (int i = 0; i < maxTime; i++) {
            for (int reindeerIndex = 0; reindeerIndex < reindeers.size(); reindeerIndex++) {
                Reindeer currentReindeer = reindeers.get(reindeerIndex);
                if (currentReindeer.remainingTravelTime > 0) {
                    currentReindeer.position += currentReindeer.speed;
                    currentReindeer.remainingTravelTime--;
                }else if(currentReindeer.remainingTimeToRest > 0){
                    currentReindeer.remainingTimeToRest--;
                }else if(currentReindeer.remainingTimeToRest == 0){
                    currentReindeer.remainingTimeToRest = currentReindeer.timeToRest;
                    currentReindeer.remainingTravelTime = currentReindeer.travelTime;
                    reindeerIndex--;
                    continue;
                }
                if (currentReindeer.position > currentLeadPosition) {
                    currentLeadPosition = currentReindeer.position;
                    leadIndexes.clear();
                    leadIndexes.add(reindeerIndex);
                }if(currentReindeer.position == currentLeadPosition){
                    leadIndexes.add(reindeerIndex);
                }
            }
            for(int index : leadIndexes){
                points[index]++;
            }
        }
        System.out.println(maxResult);
        System.out.println(Arrays.stream(points).max().getAsInt());

    }

    public static class Reindeer {
        int speed;
        int travelTime;
        int timeToRest;
        int remainingTravelTime;
        int remainingTimeToRest;
        int position = 0;

        public Reindeer(int speed, int travelTime, int timeToRest) {
            this.speed = speed;
            this.travelTime = travelTime;
            this.timeToRest = timeToRest;
            remainingTravelTime = travelTime;
            remainingTimeToRest = timeToRest;
        }
    }
}
