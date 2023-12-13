package org.advent.advent2023.day11;


import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day11/input.txt");
        Scanner scanner = new Scanner(is);
        Set<Integer> columnsWithHash = new TreeSet<>();
        Set<Integer> rowWithoutHash = new TreeSet<>();
        int counter = 0;
                List<String> space = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int column = line.indexOf("#");
            space.add(line);
            if (column == -1) {
                rowWithoutHash.add(counter);
            }
            while (column >= 0) {
                columnsWithHash.add(column);
                column = line.indexOf("#", column + 1);
            }
            counter++;
        }
        Set<Integer> columnsWithoutHash = IntStream.rangeClosed(0, space.get(0).length()).boxed().filter(s -> !columnsWithHash.contains(s)).collect(Collectors.toSet());
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Galaxy> galaxies = new ArrayList<>();
        for (int i = 0; i < space.size(); i++) {
            int column = space.get(i).indexOf("#");
            while (column >= 0) {
                galaxies.add(new Galaxy(column, i));
                column = space.get(i).indexOf("#", column + 1);
            }
        }
        BigInteger sum = BigInteger.ZERO;
        for(int i =0;i<galaxies.size();i++){
            for(int j =i+1;j<galaxies.size();j++){
                int finalI = i;
                int finalJ = j;
                long xDistance = Math.abs(galaxies.get(i).x - galaxies.get(j).x) + columnsWithoutHash.stream().filter(s->{
                    if(galaxies.get(finalI).x > galaxies.get(finalJ).x){
                        return s > galaxies.get(finalJ).x && s<galaxies.get(finalI).x;
                    }else{
                        return s > galaxies.get(finalI).x && s<galaxies.get(finalJ).x;
                    }
                }).count()*999999L;
                long yDistance = Math.abs(galaxies.get(i).y - galaxies.get(j).y) + rowWithoutHash.stream().filter(s->{
                    if(galaxies.get(finalI).y > galaxies.get(finalJ).y){
                        return s > galaxies.get(finalJ).y && s<galaxies.get(finalI).y;
                    }else{
                        return s > galaxies.get(finalI).y && s<galaxies.get(finalJ).y;
                    }
                }).count() * 999999L ;
                long result =  yDistance + xDistance;
                System.out.println(result);
                sum = sum.add(BigInteger.valueOf(result));
            }
        }
        System.out.println(sum);


    }


}

class Galaxy {
    int x;
    int y;

    public Galaxy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x+":"+y;
    }
}