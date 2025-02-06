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
        System.out.println(stage2(Set.of(molecule), replacements, "e"));
    }
    public static Set<String> createMolecules(String input, List<Pair> replacements){
        Set<String> results = new LinkedHashSet<>();
        for(Pair pair : replacements){
            int indexOf = input.indexOf(pair.key);
            while(indexOf != -1){
                results.add(input.substring(0, indexOf) + input.substring(indexOf).replaceFirst(pair.key, pair.value));
                indexOf = input.indexOf(pair.key, indexOf+1);
            }
        }
        return results;
    }

    public static int stage2(Set<String> molecules, List<Pair> replacements, String desired) {
        replacements = replacements.stream().map(pair -> new Pair(pair.value, pair.key)).sorted((o1, o2) -> Integer.compare(o2.key.length(), o1.key.length())).toList();
        int steps = 0;
        Map<Character, Integer> desiredCountOfLetters = new HashMap<>();
        for(char ch : desired.toCharArray()){
            desiredCountOfLetters.put(ch, desiredCountOfLetters.getOrDefault(ch, 0) + 1);
        }
        while (!molecules.contains(desired)) {
            Set<String> level = new HashSet<>();
            int smallest = Integer.MAX_VALUE;
            for (String molecule : molecules) {
                int similarity = Integer.MAX_VALUE;
                LinkedHashSet<String> newMolecules = new LinkedHashSet<>();
                for(Pair pair : replacements){
                    int indexOf = molecule.indexOf(pair.key);
                    while(indexOf != -1){
                        newMolecules.add(molecule.substring(0, indexOf) + molecule.substring(indexOf).replaceFirst(pair.key, pair.value));
                        indexOf = molecule.indexOf(pair.key, indexOf+1);
                    }
                }
                for(String newMolecule : newMolecules){
                    if(newMolecule.length() < smallest){
                        smallest = newMolecule.length();
                        level.clear();
                        level.add(newMolecule);
                    }else if(newMolecule.length() == smallest){
                        int score = getScoreOfString(newMolecule, desiredCountOfLetters);
                        if(score < similarity){
                            similarity = score;
                            level.clear();
                            level.add(newMolecule);

                        }else if(score == similarity){
                            level.add(newMolecule);
                        }
                    }
                }
            }
            int finalSmallest = smallest;
            molecules = level.stream().filter(s -> s.length() == finalSmallest).collect(Collectors.toSet());
            steps++;
        }
        return steps;
    }

    private static int getScoreOfString(String newMolecule, Map<Character, Integer> desiredStringOccurences){
        Map<Character, Integer> countOfLetters = new HashMap<>();
        for(char ch : newMolecule.toCharArray()){
            countOfLetters.put(ch, countOfLetters.getOrDefault(ch, 0) + 1);
        }
        int score = 0;
        for(char key : desiredStringOccurences.keySet()){
            score += desiredStringOccurences.get(key) - countOfLetters.getOrDefault(key, 0);
        }
        return score;
    }

    public static record Pair(String key, String value) {
    }


}
//193 too low
//194 too low
// 205 too high