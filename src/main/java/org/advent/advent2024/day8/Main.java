package org.advent.advent2024.day8;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static long solution = 0;
    public static void main(String[] args) {
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day8/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Character>> map = new ArrayList<>();
            List<Character> inputList = new ArrayList<>();
            int column = 0;
            int row = 0;
            Map<Character, List<Point>> antenas = new HashMap<>();
            while (br.ready()) {
               char sign =  (char)br.read();
               if(sign == '\r'){
                   continue;
               }
               if(sign != '\n'){
                   inputList.add(sign);
                   if(sign != '.'){
                       if(antenas.containsKey(sign)){
                           antenas.get(sign).add(new Point(column, row));
                       }else{
                           antenas.put(sign, new ArrayList<>(List.of(new Point(column, row))));
                       }
                   }
                   column++;
               }else{
                   map.add(inputList);
                   inputList = new ArrayList<>();
                   row++;
                   column = 0;
               }
            }
            map.add(inputList);
            int counter = 0;
            for(Character antena : antenas.keySet()){
                for(Point point1 : antenas.get(antena)){
                    for(Point point2 : antenas.get(antena)){
                        if(!point1.equals(point2)){
                            int diffX = (point1.x - point2.x);
                            int diffY = (point1.y - point2.y);
                            int newX = point1.x + (point1.x - point2.x);
                            int newY = point1.y + (point1.y - point2.y);
                            while(newX >= 0 && newX < map.get(0).size() && newY >= 0 && newY < map.size()){
                                if(map.get(newY).get(newX) != '#'){
                                    map.get(newY).set(newX, '#');
                                    counter++;
                                }
                                newX = newX + diffX;
                                newY = newY + diffY;
                            }
                        }
                    }
                }

            }
            for(Character antena : antenas.keySet()){
                for(Point p : antenas.get(antena)){
                    if(map.get(p.y).get(p.x) != '#'){
                        counter++;
                    }
                }
            }
            System.out.println(counter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static boolean checkIfPossible(long result, List<Long> values, long current, int currentIndex){
        if(currentIndex == values.size() - 1){
            if(current + values.get(currentIndex) == result || current * values.get(currentIndex) == result || Long.parseLong(Long.toString(current) + Long.toString(values.get(currentIndex))) == result){
                return true;
            }else{
                return false;
            }
        }
        else{
            boolean s1 = checkIfPossible(result, values, current + values.get(currentIndex), currentIndex + 1);
            boolean s2 = checkIfPossible(result, values, current * values.get(currentIndex), currentIndex + 1);
            boolean s3 = checkIfPossible(result, values, Long.parseLong(Long.toString(current) + Long.toString(values.get(currentIndex))), currentIndex + 1);
            if(s1){
                return s1;
            }
            if(s2){
                return s2;
            }
            if(s3){
                return s3;
            }
        }
        return false;
    }

}