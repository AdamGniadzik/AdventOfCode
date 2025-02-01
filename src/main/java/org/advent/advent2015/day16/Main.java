package org.advent.advent2015.day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day16/input.txt"));
        List<Sue> aunts = new ArrayList<>();
        int counter =1 ;
        while (br.ready()) {
            String[] input = br.readLine().replace(":", "").replace(",", "").split(" ");
            Sue sue = new Sue();
            sue.id = counter;
            for (int i = 2; i < input.length; i+=2) {
                switch (input[i]) {
                    case "children" -> sue.children += Integer.parseInt(input[i + 1]);
                    case "cats" -> sue.cats += Integer.parseInt(input[i + 1]);
                    case "samoyeds" -> sue.samoyeds += Integer.parseInt(input[i + 1]);
                    case "pomeranians" -> sue.pomeranians += Integer.parseInt(input[i + 1]);
                    case "akitas" -> sue.akitas += Integer.parseInt(input[i + 1]);
                    case "vizslas" -> sue.vizslas += Integer.parseInt(input[i + 1]);
                    case "goldfish" -> sue.goldfish += Integer.parseInt(input[i + 1]);
                    case "trees" -> sue.trees += Integer.parseInt(input[i + 1]);
                    case "cars" -> sue.cars += Integer.parseInt(input[i + 1]);
                    case "perfumes" -> sue.perfumes += Integer.parseInt(input[i + 1]);
                }
            }
            counter++;
            aunts.add(sue);
        }
        Sue templateSue = new Sue();
        templateSue.children = 3;
        templateSue.cats = 7;
        templateSue.samoyeds = 2;
        templateSue.pomeranians = 3;
        templateSue.akitas = 0;
        templateSue.vizslas = 0;
        templateSue.goldfish = 5;
        templateSue.trees = 3;
        templateSue.cars = 2;
        templateSue.perfumes = 1;
        aunts.stream().filter(aunt ->
                (aunt.children == 0 || aunt.children == templateSue.children) &&
                        (aunt.cars == 0 || aunt.cars == templateSue.cars) &&
                        (aunt.cats == 0 || aunt.cats == templateSue.cats) &&
                        (aunt.samoyeds == 0 || aunt.samoyeds == templateSue.samoyeds) &&
                        (aunt.akitas == 0) &&
                        (aunt.pomeranians == 0 || aunt.pomeranians == templateSue.pomeranians) &&
                        (aunt.goldfish == 0 || aunt.goldfish == templateSue.goldfish) &&
                        (aunt.trees == 0 || aunt.trees == templateSue.trees) &&
                        (aunt.vizslas == 0) &&
                        (aunt.perfumes == 0 || aunt.perfumes == templateSue.perfumes)
        ).forEach(System.out::println);
        System.out.println();
        aunts.stream().filter(aunt ->
                (aunt.children == 0 || aunt.children == templateSue.children) &&
                        (aunt.cars == 0 || aunt.cars == templateSue.cars) &&
                        (aunt.cats == 0 || aunt.cats > templateSue.cats) &&
                        (aunt.samoyeds == 0 || aunt.samoyeds == templateSue.samoyeds) &&
                        (aunt.akitas == 0) &&
                        (aunt.pomeranians == 0 || aunt.pomeranians < templateSue.pomeranians) &&
                        (aunt.goldfish == 0 || aunt.goldfish < templateSue.goldfish) &&
                        (aunt.trees == 0 || aunt.trees > templateSue.trees) &&
                        (aunt.vizslas == 0) &&
                        (aunt.perfumes == 0 || aunt.perfumes == templateSue.perfumes)
        ).forEach(System.out::println);

    }

    public static class Sue {
        int id;
        int children;
        int cats;
        int samoyeds;
        int pomeranians;
        int akitas;
        int vizslas;
        int goldfish;
        int trees;
        int cars;
        int perfumes;

        @Override
        public String toString() {
            return "Sue{" +
                    "id=" + id +
                    ", children=" + children +
                    ", cats=" + cats +
                    ", samoyeds=" + samoyeds +
                    ", pomeranians=" + pomeranians +
                    ", akitas=" + akitas +
                    ", vizslas=" + vizslas +
                    ", goldfish=" + goldfish +
                    ", trees=" + trees +
                    ", cars=" + cars +
                    ", perfumes=" + perfumes +
                    '}';
        }
    }
}