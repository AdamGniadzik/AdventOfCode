package org.advent.advent2015.day21;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day21/input.txt"));
        List<Item> weapons = new ArrayList<>();
        List<Item> armors = new ArrayList<>();
        List<Item> rings = new ArrayList<>();
        String input = br.readLine();
        while (!input.isEmpty()) {
            String[] split = input.replaceAll("\s+", " ").split(" ");
            weapons.add(new Item(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[1])));
            input = br.readLine();
        }
        input = br.readLine();
        while (!input.isEmpty()) {
            String[] split = input.replaceAll("\s+", " ").split(" ");
            armors.add(new Item(Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[1])));
            input = br.readLine();
        }
        input = br.readLine();
        while (!input.isEmpty()) {
            String[] split = input.replaceAll("\s+", " ").split(" ");
            rings.add(new Item(Integer.parseInt(split[3]), Integer.parseInt(split[4]), Integer.parseInt(split[2])));
            input = br.readLine();
        }
        int bossHealth = Integer.parseInt(br.readLine().substring(12));
        int bossDamage = Integer.parseInt(br.readLine().substring(8));
        int bossArmor = Integer.parseInt(br.readLine().substring(7));
        int playerHealth = 100;

        List<Player> players = new ArrayList<>();
        for (Item weapon : weapons) {
            players.add(new Player(weapon.damage, 0, weapon.cost));

            for (Item armor : armors) {
                players.add(new Player(weapon.damage, armor.armor, weapon.cost + armor.cost));

                for (int i = 0; i < rings.size(); i++) {
                    players.add(new Player(weapon.damage + rings.get(i).damage,
                            armor.armor + rings.get(i).armor,
                            weapon.cost + armor.cost + rings.get(i).cost));

                    players.add(new Player(weapon.damage + rings.get(i).damage,
                            rings.get(i).armor,
                            weapon.cost + rings.get(i).cost));

                    for (int j = i+1; j < rings.size(); j++) {
                            players.add(new Player(weapon.damage + rings.get(i).damage + rings.get(j).damage,
                                    armor.armor + rings.get(i).armor + rings.get(j).armor,
                                    weapon.cost + armor.cost + rings.get(i).cost + rings.get(j).cost));
                            players.add(new Player(weapon.damage + rings.get(i).damage + rings.get(j).damage,
                                    rings.get(i).armor + rings.get(j).armor,
                                    weapon.cost + rings.get(i).cost + rings.get(j).cost));

                    }
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        int maxCost = Integer.MIN_VALUE;
        for (Player player : players) {
            double turnsToDefeatPlayer =  Math.ceil(playerHealth / Math.max(bossDamage - player.armor, 1.0));
            double turnsToDefeatBoss =  Math.ceil(bossHealth / Math.max(player.damage - bossArmor, 1.0));
            if (turnsToDefeatBoss <= turnsToDefeatPlayer) {
                if (player.cost < minCost) {
                    minCost = player.cost;
                }
            } else {
                if (player.cost > maxCost) {
                    maxCost = player.cost;
                }
            }
        }
        System.out.println(minCost);
        System.out.println(maxCost);

    }

    record Player(int damage, int armor, int cost) {
    }

    record Item(int damage, int armor, int cost) {
    }
}
