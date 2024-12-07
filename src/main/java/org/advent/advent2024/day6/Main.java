package org.advent.advent2024.day6;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day6/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Character>> map = new ArrayList<>();
            int startRow = -1;
            int counter = 0;
            int startColumn = -1;
            while (br.ready()) {
                String input = br.readLine();
                int indexOf = input.indexOf("^");
                if (indexOf >= 0) {
                    startColumn = indexOf;
                    startRow = counter;
                }
                counter++;
                map.add(new ArrayList<>(input.chars().boxed().map(i -> (char) i.intValue()).toList()));
            }
            System.out.println(map);
            System.out.println(startColumn);
            System.out.println(startRow);
            int result = move2(map, startColumn, startRow);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int move(List<List<Character>> map, int x, int y) {
        int counter = 0;
        while (true) {
            if (map.get(y).get(x) == '<') {
                if (x - 1 >= 0) {
                    if (map.get(y).get(x - 1) == '#') {
                        map.get(y).set(x, '^');
                    } else {
                        if (map.get(y).get(x - 1) == '.') {
                            counter++;
                        }
                        map.get(y).set(x - 1, '<');
                        map.get(y).set(x, 'X');
                        x--;
                    }
                } else {
                    break;
                }
            }
            if (map.get(y).get(x) == '^') {
                if (y - 1 >= 0) {
                    if (map.get(y - 1).get(x) == '#') {
                        map.get(y).set(x, '>');
                    } else {
                        if (map.get(y - 1).get(x) == '.') {
                            counter++;
                        }
                        map.get(y - 1).set(x, '^');
                        map.get(y).set(x, 'X');
                        y--;
                    }
                } else {
                    break;
                }
            }
            if (map.get(y).get(x) == '>') {
                if (x + 1 < map.get(y).size()) {
                    if (map.get(y).get(x + 1) == '#') {
                        map.get(y).set(x, 'v');
                    } else {
                        if (map.get(y).get(x + 1) == '.') {
                            counter++;
                        }
                        map.get(y).set(x + 1, '>');
                        map.get(y).set(x, 'X');
                        x++;
                    }
                } else {
                    break;
                }
            }
            if (map.get(y).get(x) == 'v') {
                if (y + 1 < map.size()) {
                    if (map.get(y + 1).get(x) == '#') {
                        map.get(y).set(x, '<');
                    } else {
                        if (map.get(y + 1).get(x) == '.') {
                            counter++;
                        }
                        map.get(y + 1).set(x, 'v');
                        map.get(y).set(x, 'X');
                        y++;
                    }
                } else {
                    break;
                }
            }
        }
        return counter;
    }


    public static int move2(List<List<Character>> inputMap, int initialX, int initialY) {
        int obstaclesCounter = 0;
        for (int i = 0; i < inputMap.size(); i++) {
            for (int j = 0; j < inputMap.get(i).size(); j++) {
                if(inputMap.get(i).get(j) == '.') {
                    List<List<Character>> map = new ArrayList<>(inputMap.stream().map(ArrayList::new).toList());
                    map.get(i).set(j, '#');
                    int counter = 0;
                    int noChanges = 0;
                    int x = initialX;
                    int y = initialY;
                    while (true) {
                        int previousCounter = counter;
                        // That's a sad brute force, but works :D
                        if(noChanges == 1000){
                            obstaclesCounter++;
                            break;
                        }
                        if (map.get(y).get(x) == '<') {
                            if (x - 1 >= 0) {
                                if (map.get(y).get(x - 1) == '#') {
                                    map.get(y).set(x, '^');
                                } else {
                                    if (map.get(y).get(x - 1) == '.') {
                                        counter++;
                                    }
                                    map.get(y).set(x - 1, '<');
                                    map.get(y).set(x, 'X');
                                    x--;
                                }
                            } else {
                                break;
                            }
                        }
                        if (map.get(y).get(x) == '^') {
                            if (y - 1 >= 0) {
                                if (map.get(y - 1).get(x) == '#') {
                                    map.get(y).set(x, '>');
                                } else {
                                    if (map.get(y - 1).get(x) == '.') {
                                        counter++;
                                    }
                                    map.get(y - 1).set(x, '^');
                                    map.get(y).set(x, 'X');
                                    y--;
                                }
                            } else {
                                break;
                            }
                        }
                        if (map.get(y).get(x) == '>') {
                            if (x + 1 < map.get(y).size()) {
                                if (map.get(y).get(x + 1) == '#') {
                                    map.get(y).set(x, 'v');
                                } else {
                                    if (map.get(y).get(x + 1) == '.') {
                                        counter++;
                                    }
                                    map.get(y).set(x + 1, '>');
                                    map.get(y).set(x, 'X');
                                    x++;
                                }
                            } else {
                                break;
                            }
                        }
                        if (map.get(y).get(x) == 'v') {
                            if (y + 1 < map.size()) {
                                if (map.get(y + 1).get(x) == '#') {
                                    map.get(y).set(x, '<');
                                } else {
                                    if (map.get(y + 1).get(x) == '.') {
                                        counter++;
                                    }
                                    map.get(y + 1).set(x, 'v');
                                    map.get(y).set(x, 'X');
                                    y++;
                                }
                            } else {
                                break;
                            }
                        }
                        if(counter == previousCounter){
                            noChanges++;
                        }
                    }
                }
            }
        }
        return obstaclesCounter;
    }
}