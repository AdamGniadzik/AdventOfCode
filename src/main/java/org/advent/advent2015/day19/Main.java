package org.advent.advent2015.day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day19/input.txt"));
        List<Pair> replacements = new ArrayList<>();
        Set<String> results = new HashSet<>();
        String molecule = "";
        while (br.ready()) {
            String input = br.readLine();
            while (!input.isEmpty()) {
                String[] split = input.split(" => ");
                replacements.add(new Pair(split[0], split[1]));
                input = br.readLine();
            }
            molecule = br.readLine();
        }
        System.out.println(createMolecules(molecule, replacements).size());
    }
    public static Set<String> createMolecules(String input, List<Pair> replacements){
        Set<String> results = new HashSet<>();
        for(Pair pair : replacements){
            int indexOf = input.indexOf(pair.key);
            while(indexOf != -1){
                results.add(input.substring(0, indexOf) + input.substring(indexOf).replaceFirst(pair.key, pair.value));
                indexOf = input.indexOf(pair.key, indexOf+1);
            }
        }
        return results;
    }

    public static record Pair(String key, String value) {
    }

}