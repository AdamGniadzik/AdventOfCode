package org.advent.advent2015.day22;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
      solution(false);
      solution(true);
    }

    public static void solution(boolean stage2){
        int minManaSpent = Integer.MAX_VALUE;
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        while (!games.isEmpty()) {
            List<Game> newGames = new ArrayList<>();
            for (Game game : games) {
                if(stage2){
                    game.playerHp--;
                    if(game.playerHp <= 0){
                        continue;
                    }
                }
                processPlayerBuffsAndDebuffs(game);
                List<Spell> allPossibleSpells = getAllPossibleSpellsForGame(game);
                for (Spell spell : allPossibleSpells) {
                    Game newGame = new Game(game);
                    playGame(spell, newGame);
                    if(newGame.playerHp > 0 && newGame.bossHp <=0 && newGame.manaSpent <= minManaSpent){
                        minManaSpent = newGame.manaSpent;
                    }
                    processPlayerBuffsAndDebuffs(newGame);
                    if(newGame.playerHp > 0 && newGame.bossHp <=0 && newGame.manaSpent <= minManaSpent){
                        minManaSpent = newGame.manaSpent;
                    }
                    bossTurn(newGame);
                    if(newGame.playerHp > 0 && newGame.bossHp > 0 && newGame.manaSpent <= minManaSpent){
                        newGames.add(newGame);
                    }
                }
            }
            games = new ArrayList<>(newGames);
        }
        System.out.println(minManaSpent);
    }

    public static void playGame(Spell spell, Game game) {
        switch (spell) {
            case DRAIN -> {
                game.bossHp -= 2;
                game.playerHp += 2;
            }
            case POISON -> {
                game.poisonTimer = 6;
            }
            case SHIELD -> {
                game.shieldTimer = 6;
            }
            case MISSILE -> {
                game.bossHp -= 4;
            }
            case RECHARGE -> {
                game.rechargeTimer = 5;
            }
        }
        game.playerMana -= spell.mana;
        game.manaSpent += spell.mana;
    }

    public static void bossTurn(Game game) {
        if (game.shieldTimer > 0) {
            game.playerHp -=  (game.bossDamage - Game.SHIELD_VALUE);
        } else {
            game.playerHp -= game.bossDamage;
        }
    }

    public static void processPlayerBuffsAndDebuffs(Game game) {
        if (game.rechargeTimer > 0) {
            game.rechargeTimer--;
            game.playerMana += 101;
        }
        if (game.shieldTimer > 0) {
            game.shieldTimer--;
        }
        if (game.poisonTimer > 0) {
            game.poisonTimer--;
            game.bossHp -= 3;
        }
    }

    static List<Spell> getAllPossibleSpellsForGame(Game game) {
        List<Spell> spells = new ArrayList<>(5);
        if (game.poisonTimer == 0 && game.playerMana >= Spell.POISON.mana) {
            spells.add(Spell.POISON);
        }
        if (game.rechargeTimer == 0 && game.playerMana >= Spell.RECHARGE.mana) {
            spells.add(Spell.RECHARGE);
        }
        if (game.shieldTimer == 0 && game.playerMana >= Spell.SHIELD.mana) {
            spells.add(Spell.SHIELD);
        }
        if (game.playerMana >= Spell.MISSILE.mana) {
            spells.add(Spell.MISSILE);
        }
        if (game.playerMana >= Spell.DRAIN.mana) {
            spells.add(Spell.DRAIN);
        }
        return spells;
    }

    enum Spell {
        MISSILE(53),
        SHIELD(113),
        RECHARGE(229),
        DRAIN(73),
        POISON(173);

        int mana;

        Spell(int mana) {
            this.mana = mana;
        }
    }

    static class Game {
        static final int SHIELD_VALUE = 7;
        int playerHp;
        int playerMana;
        int shieldTimer;
        int rechargeTimer;
        int poisonTimer;
        int bossHp;
        int bossDamage;
        int manaSpent;

        public Game() {
            playerHp = 50;
            playerMana = 500;
            bossHp = 51;
            bossDamage = 9;
        }

        public Game(Game game) {
            this.playerHp = game.playerHp;
            this.playerMana = game.playerMana;
            this.shieldTimer = game.shieldTimer;
            this.rechargeTimer = game.rechargeTimer;
            this.poisonTimer = game.poisonTimer;
            this.bossHp = game.bossHp;
            this.bossDamage = game.bossDamage;
            this.manaSpent = game.manaSpent;

        }
    }
}
