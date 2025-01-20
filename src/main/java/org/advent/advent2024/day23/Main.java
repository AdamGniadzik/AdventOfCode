package org.advent.advent2024.day23;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2024/day23/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Map<String, Set<String>> connectionMap = new HashMap<>();

        while (br.ready()) {
            String[] names  = br.readLine().split("-");
            if(connectionMap.containsKey(names[0])){
                connectionMap.get(names[0]).add(names[1]);
            }else{
                connectionMap.put(names[0], new HashSet<>());
                connectionMap.get(names[0]).add(names[1]);
            }

            if(connectionMap.containsKey(names[1])){
                connectionMap.get(names[1]).add(names[0]);
            }else{
                connectionMap.put(names[1], new HashSet<>());
                connectionMap.get(names[1]).add(names[0]);
            }
        }

        Set<Set<String>> triplets = new HashSet<>();
        connectionMap.entrySet().stream().filter(e->e.getKey().startsWith("t") && e.getValue().size() > 2).forEach(computer ->{
            for(String n1 : computer.getValue()){
                for(String n2 : computer.getValue()){
                    if(!n1.equals(n2) && connectionMap.get(n1).contains(n2)){
                        triplets.add(Set.of(n1, n2, computer.getKey()));
                    }
                }
            }
        });
        System.out.println(triplets.size());
        int max = 0;
        Set<String> result = new HashSet<>();
        for(Map.Entry<String, Set<String>> entry : connectionMap.entrySet()){
            Set<String> output = checkConnections(entry.getKey(), new HashSet<>(), connectionMap);
            if(output.size() > max){
                max = output.size();
                result = new HashSet<>(output);
            }
        }
        System.out.println(result.stream().sorted().collect(Collectors.joining(",")));
    }

    static Set<String> checkConnections(String key, Set<String> previousNodes, Map<String, Set<String>> map){
        if(map.get(key).containsAll(previousNodes)){
            previousNodes.add(key);
            for(String nextKey : map.get(key)){
                checkConnections(nextKey, previousNodes, map);
            }
        }else{
           return previousNodes;
        }
        return previousNodes;
    }
}