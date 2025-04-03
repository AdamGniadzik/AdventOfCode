package org.advent.advent2016.day22;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day22/input.txt"));
        List<String> operations = new ArrayList<>();
        br.readLine();
        br.readLine();
        List<List<Node>> nodes = new ArrayList<>();
        for (int i = 0; i <= 28; i++) {
            nodes.add(new ArrayList<>(34));
        }
        while (br.ready()) {
            String[] input = br.readLine().replaceAll("\\s+", " ").split(" ");
            String[] name = input[0].split("-");
            int x = Integer.parseInt(name[1].substring(1));
            int y = Integer.parseInt(name[2].substring(1));
            int size = Integer.parseInt(input[1].replace("T", ""));
            int used = Integer.parseInt(input[2].replace("T", ""));
            int avail = Integer.parseInt(input[3].replace("T", ""));
            int percentage = Integer.parseInt(input[4].replace("%", ""));
            nodes.get(y).add(x, new Node(x, y, size, used, avail, percentage));
        }

        stage1(nodes);
        stage2(nodes);
    }

    static void stage1(List<List<Node>> nodes) {
        List<Node> flatNodes = nodes.stream().flatMap(Collection::stream).toList();
        int pairsCounter = 0;
        for (int i = 0; i < flatNodes.size(); i++) {
            for (int j = 0; j < flatNodes.size(); j++) {
                if (i == j) {
                    continue;
                }
                if(flatNodes.get(i).used != 0 && flatNodes.get(i).used <= flatNodes.get(j).available){
                    pairsCounter++;
                }

            }
        }
        System.out.println(pairsCounter);
    }
    static void stage2(List<List<Node>> nodes){
        int emptyY = 0;
        int emptyX = 0;
        int counter = 0;
        System.out.print("  ");
        for(int i =0;i<nodes.get(0).size();i++){
            System.out.print(i%10 +" ");
        }
        System.out.println();
        for(List<Node> list: nodes){
            System.out.print(counter%10 + " ");
            for(Node node : list){
                if(node.used == 0){
                    System.out.print("_ ");
                    emptyY = node.y;
                    emptyX = node.x;
                }else{

                    System.out.print(node.used > 100  ? "# " :  ". ");
                }
            }
            counter++;
            System.out.println();
        }
        int steps = 0;
        while(nodes.get(emptyY-1).get(emptyX).used < 100){
            emptyY--;
            steps++;
        }
        while(nodes.get(emptyY - 1).get(emptyX).used > 100){
            emptyX--;
            steps++;
        }
        while(emptyY != 0){
            emptyY--;
            steps++;
        }
        while(emptyX != nodes.get(0).size() - 2){
            emptyX++;
            steps++;
        }
        System.out.println(steps + ((nodes.get(0).size() - 2) * 5 ) + 1) ;

        // Move _ manually to one left from top-right corner, then caulcuate
        // Moves to one left spot from top-right corner * (rowSize - 1) * 5 +1 -> 26 + (33*5) + 1 -> 192
    }



    public record Node(int x, int y, int size, int used, int available, int percentage) {

    }
}
