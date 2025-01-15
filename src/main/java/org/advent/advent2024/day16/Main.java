package org.advent.advent2024.day16;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day16/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<Travel> listOfGlory = new ArrayList<>();
            List<List<Character>> map = new ArrayList<>();
            while (br.ready()) {
                map.add(new ArrayList<>(br.readLine().chars().mapToObj(i -> (char) i).toList()));
            }
            Travel first = new Travel(1, map.size() - 2, 2);
            LinkedList<Travel> travels = new LinkedList<>();
            travels.add(first);
            Set<Point> globalPath = new HashSet<>();
            while (!travels.isEmpty()) {
                Travel travel = travels.poll();
                boolean moved = false;
                List<Travel> newTravels = createIfPossible(travel, map);
                globalPath.addAll(travel.path);
                newTravels.stream().filter(n -> !globalPath.contains(new Point(n.x, n.y))).forEach(travels::add);

                if (travel.direction == 1) {
                    if (map.get(travel.y - 1).get(travel.x) != '#') {
                        travel.path.add(new Point(travel.x, travel.y));
                        travel.y--;
                        travel.points++;
                        moved = true;
                    }
                } else if (travel.direction == 2) {
                    if (map.get(travel.y).get(travel.x + 1) != '#') {
                        travel.path.add(new Point(travel.x, travel.y));
                        travel.x++;
                        travel.points++;
                        moved = true;
                    }
                } else if (travel.direction == 3) {
                    if (map.get(travel.y + 1).get(travel.x) != '#') {
                        travel.path.add(new Point(travel.x, travel.y));
                        travel.y++;
                        travel.points++;
                        moved = true;
                    }
                } else if (travel.direction == 4) {
                    if (map.get(travel.y).get(travel.x - 1) != '#') {
                        travel.path.add(new Point(travel.x, travel.y));
                        travel.x--;
                        travel.points++;
                        moved = true;
                    }
                }


                if (map.get(travel.y).get(travel.x) == 'E') {
                    listOfGlory.add(travel);
                } else if (moved) {
                    travels.addFirst(travel);
                }
            }
            int min = Integer.MAX_VALUE;
            Set<Point> allPoints = new HashSet<>();
            for (Travel travel : listOfGlory) {
                if (min > travel.points) {
                    min = travel.points;
                }
            }
            for (Travel travel : listOfGlory) {
                if (min == travel.points) {
                    allPoints.addAll(travel.path);
                }
            }
            System.out.println(allPoints.size() + 1);
            System.out.println(min);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Travel> createIfPossible(Travel travel, List<List<Character>> map) {
        List<Travel> newList = new ArrayList<>();
        if (travel.direction == 1 || travel.direction == 3) {
            if (map.get(travel.y).get(travel.x + 1) == '.') {
                Travel newTravel = new Travel(travel.x + 1, travel.y, 2);
                newTravel.path = new HashSet<>(travel.path);
                newTravel.path.add(new Point(travel.x, travel.y));
                newTravel.points = travel.points + 1001;
                newList.add(newTravel);
            }
            if (map.get(travel.y).get(travel.x - 1) == '.') {
                Travel newTravel = new Travel(travel.x - 1, travel.y, 4);
                newTravel.path = new HashSet<>(travel.path);
                newTravel.path.add(new Point(travel.x, travel.y));
                newTravel.points = travel.points + 1001;
                newList.add(newTravel);
            }
        } else if (travel.direction == 2 || travel.direction == 4) {
            if (map.get(travel.y + 1).get(travel.x) == '.') {
                Travel newTravel = new Travel(travel.x, travel.y + 1, 3);
                newTravel.path = new HashSet<>(travel.path);
                newTravel.path.add(new Point(travel.x, travel.y));
                newTravel.points = travel.points + 1001;
                newList.add(newTravel);
            }
            if (map.get(travel.y - 1).get(travel.x) == '.') {
                Travel newTravel = new Travel(travel.x, travel.y - 1, 1);
                newTravel.path = new HashSet<>(travel.path);
                newTravel.path.add(new Point(travel.x, travel.y));
                newTravel.points = travel.points + 1001;
                newList.add(newTravel);
            }
        }
        return newList;
    }


    public static class Point {
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

    public static class Travel {
        int x;
        int y;
        int points = 0;
        int direction;
        Set<Point> path = new HashSet<>();

        public Travel(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

}