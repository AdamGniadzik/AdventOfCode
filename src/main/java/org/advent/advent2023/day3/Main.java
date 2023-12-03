package org.advent.advent2023.day3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static List<String> input = new ArrayList<>();
    public static Map<String, List<Integer>> gearMap = new HashMap<>();
    public static void main(String[] args) {
        int sum = 0;
        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day3/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                input.add(br.readLine());
            }
            for (int i = 0; i < input.size(); i++) {
                int lengthOfNumber = 0;
                for (int j = 0; j < input.get(i).length(); j++) {
                    if(isNumeric(input.get(i).charAt(j))){
                        lengthOfNumber++;
                    } else {
                        sum+= getNumberToAdd(i, j, lengthOfNumber);
                        lengthOfNumber = 0;
                    }

                }
                sum+= getNumberToAdd( i, input.size(), lengthOfNumber);
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(gearMap.values().stream()
                .filter(list->list.size()==2)
                .map(list->list.stream()
                        .mapToInt(i->i)
                        .reduce(1, (a,b)->a*b))
                .mapToInt(i->i)
                .sum());

    }

    public static int getNumberToAdd(int iPosition, int jPosition, int lengthOfNumber) {
        if (lengthOfNumber > 0 && checkIfNeighbourAndProcessGears(iPosition, jPosition - 1, lengthOfNumber)) {
            return Integer.parseInt(input.get(iPosition).substring(jPosition - lengthOfNumber, jPosition));
        }
        return 0;
    }

    public static boolean isNumeric(char ch) {
        return ch >= '0' && ch <= '9';
    }
    public static boolean isSymbol (char ch){
        return !isNumeric(ch) && ch != '.';
    }

    public static boolean isGear (char ch){
        return ch =='*';
    }

    public static boolean checkIfNeighbourAndProcessGears(int iPosition, int jPosition, int lengthOfNumber) {
        boolean isNeighbour = false;
        for(int i= iPosition-1; i<=iPosition + 1; i++){
            for(int j = jPosition - lengthOfNumber; j<=jPosition  + 1; j++) {
                if(i>=0 && i < input.size() && j > 0 && j < input.get(i).length() && isSymbol(input.get(i).charAt(j))){
                    if(isGear(input.get(i).charAt(j))){
                        String mapKey = i+"-"+j;
                        int parsed = Integer.parseInt(input.get(iPosition).substring(jPosition - lengthOfNumber + 1, jPosition+1));
                        if(gearMap.containsKey(mapKey)){
                            gearMap.get(mapKey).add(parsed);
                        }else{
                            List<Integer> values = new ArrayList<>();
                            values.add(parsed);
                            gearMap.put(mapKey, values);
                        }
                    }
                    isNeighbour = true;
                }
            }
        }
        return isNeighbour;
    }
}
