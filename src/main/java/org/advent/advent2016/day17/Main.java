package org.advent.advent2016.day17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
// 249927 too high

// 523 too high
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day17/input.txt"));
        String hashKey = br.readLine();
        Traveler traveler = new Traveler(0, 0);
        Queue<Traveler> travelerQueue = new LinkedList<>();
        travelerQueue.add(traveler);
        Set<Character> openSigns = Set.of('b', 'c', 'd', 'e', 'f');
        MessageDigest md = MessageDigest.getInstance("MD5");
        String shortestPath = null;
        String longestPath = "";
        while(!travelerQueue.isEmpty()){
            Traveler current = travelerQueue.poll();
            if(current.x == 3 && current.y == 3){
                if(shortestPath == null){
                    shortestPath = current.pathString.substring(0, current.pathString.length() - 1);
                }
                if(current.pathString.length() > longestPath.length()){
                    longestPath = current.pathString;
                }
                continue;
            }
            String map = toHex(hashKey + current.pathString, md).substring(0, 4);
            if(openSigns.contains(map.charAt(0)) && current.y > 0){
                travelerQueue.add(new Traveler(current.x, current.y - 1, current.pathString + "U"));
            }
            if(openSigns.contains(map.charAt(1)) && (current.y < 3 )){
                travelerQueue.add(new Traveler(current.x, current.y + 1, current.pathString + "D"));
            }

            if(openSigns.contains(map.charAt(2)) && current.x > 0 ){
                travelerQueue.add(new Traveler(current.x - 1, current.y, current.pathString + "L"));
            }

            if(openSigns.contains(map.charAt(3)) && current.x < 3){
                travelerQueue.add(new Traveler(current.x + 1, current.y, current.pathString + "R"));
            }

        }
        System.out.println(shortestPath);
        System.out.println(longestPath.length());

    }

    public static class Traveler{
        int x;
        int y;
        String pathString = "";

        public Traveler(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Traveler(int x, int y, String pathString) {
            this.x = x;
            this.y = y;
            this.pathString = pathString;
        }
    }

    public static String toHex(String input, MessageDigest md) {
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }
}
