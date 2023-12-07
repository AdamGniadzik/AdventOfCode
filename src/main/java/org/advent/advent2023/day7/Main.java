package org.advent.advent2023.day7;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;


enum Type {
    HIGH_CARD(0),
    ONE_PAIR(1),
    TWO_PAIR(2),
    THREE_OF_KIND(3),
    FULL_HOUSE(4),
    FOUR_OF_KIND(5),
    FIVE_OF_KIND(6);

    int strength;

    Type(int strength) {
        this.strength = strength;
    }
}

public class Main {


    public static boolean STAGE_1_FLAG = false;
    public static void main(String[] args) {


        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day7/input.txt");

            Scanner scanner = new Scanner(is);
            Map<Type, List<Hand>> handMap = new HashMap<>();
            for (Type type : Type.values()) {
                handMap.put(type, new ArrayList<>());
            }

            while (scanner.hasNext()) {
                String card = scanner.next();
                Long bid = scanner.nextLong();
                Type type = rateCard(card);
                type = STAGE_1_FLAG ? type : rateCardAgainWithWildcard(type, card);
                handMap.get(type).add(new Hand(card, bid));
            }
            handMap.values().forEach(Collections::sort);
            long sum = 0;
            int rank = 1;
            for (Type type : Type.values()) {
                Collections.sort(handMap.get(type));
                for (Hand hand : handMap.get(type)) {
                    sum += hand.bid * rank;
                    rank++;
                }
            }
            System.out.println(sum);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Type rateCard(String cardSet) {
        List<Integer> chars = cardSet.chars().boxed().toList();
        List<Integer> distinctChars = chars.stream().distinct().toList();
        if (distinctChars.size() == 1) {
            return Type.FIVE_OF_KIND;
        }
        if (distinctChars.size() == 2) {
            long count = chars.stream().filter(i -> i.equals(distinctChars.get(0))).count();
            if (count == 2 || count == 3) {
                return Type.FULL_HOUSE;
            } else {
                return Type.FOUR_OF_KIND;
            }
        }
        if (distinctChars.size() == 3) {
            boolean isAnyTriplet =
                    chars.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting())).values().stream()
                            .anyMatch(i -> i == 3);
            if (isAnyTriplet) {
                return Type.THREE_OF_KIND;
            } else {
                return Type.TWO_PAIR;
            }
        }
        if (distinctChars.size() == 4) {
            return Type.ONE_PAIR;

        }
        return Type.HIGH_CARD;
    }

    public static Type rateCardAgainWithWildcard(Type type, String cardSet) {
        long countOfJokers = cardSet.chars().filter(i -> i == 'J').count();
        if(countOfJokers ==  0){
            return type;
        }
        if(type == Type.FULL_HOUSE){
            return Type.FIVE_OF_KIND;
        }
        if(type == Type.FOUR_OF_KIND){
            return Type.FIVE_OF_KIND;
        }
        if(type == Type.THREE_OF_KIND){
            if(countOfJokers == 3 || countOfJokers == 1){
                return Type.FOUR_OF_KIND;
            }
        }
        if(type == Type.TWO_PAIR){
            if(countOfJokers == 2){
                return Type.FOUR_OF_KIND;
            }
            if(countOfJokers == 1){
                return Type.FULL_HOUSE;
            }
        }
        if(type == Type.ONE_PAIR){
            if(countOfJokers == 1 || countOfJokers == 2){
                return Type.THREE_OF_KIND;
            }
        }
        if(type == Type.HIGH_CARD){
            return Type.ONE_PAIR;
        }

        return type;
    }
}

class Hand implements Comparable {

    static Map<Character, Integer> orderOfSigns = new HashMap() {{
        put('A', 12);
        put('K', 11);
        put('Q', 10);
        put('J', Main.STAGE_1_FLAG ? 9 : -1);
        put('T', 8);
        put('9', 7);
        put('8', 6);
        put('7', 5);
        put('6', 4);
        put('5', 3);
        put('4', 2);
        put('3', 1);
        put('2', 0);
    }};
    String cards;
    Long bid;

    public Hand(String cards, Long bid) {
        this.cards = cards;
        this.bid = bid;
    }

    @Override
    public int compareTo(Object o) {
        Hand other = (Hand) o;
        for (int i = 0; i < cards.length(); i++) {
            int result = orderOfSigns.get(cards.charAt(i)).compareTo(orderOfSigns.get(other.cards.charAt(i)));
            if (result != 0) {
                return result;
            }

        }
        return 0;
    }

    @Override
    public String toString() {
        return cards + ":" + bid;
    }
}

