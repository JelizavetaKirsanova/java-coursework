package collections.simulator;

import java.util.*;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {

        return cards.toString();
    }

    public HandType getHandType() {
        List<Card.CardValue> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getValue());
        }

        List<Card.CardSuit> suits = new ArrayList<>();
        for (Card card : cards){
            suits.add(card.getSuit());
        }

        List<List<Card.CardValue>> groups = new ArrayList<>();

        for (Card.CardValue value : values) {
            boolean found = false;
            for (List<Card.CardValue> group : groups) {
                if (group.get(0).equals(value)) {
                    group.add(value);
                    found = true;
                    break;
                }
            }
            if (!found) {
                List<Card.CardValue> newGroup = new ArrayList<>();
                newGroup.add(value);
                groups.add(newGroup);
            }}


        groups.sort((a, b) -> Integer.compare(b.size(), a.size()));
        System.out.println(groups);



        boolean flush = true;
        for (Card.CardSuit suit : suits) {
            if (!suit.equals(suits.getFirst())) {
                flush = false;
                break;
            }}


        boolean straight = false;
        if (values.size() == 5) {
            List<Integer> ordinals = new ArrayList<>();
            for (Card.CardValue value : values) {
                ordinals.add(value.ordinal());
            }
            Collections.sort(ordinals);

            boolean normalStraight = true;
            for (int i = 1; i < ordinals.size(); i++) {
                if (ordinals.get(i) != ordinals.get(i - 1) + 1) {
                    normalStraight = false;
                    break;
                }}
            boolean wheelStraight = values.contains(Card.CardValue.A) && values.contains(Card.CardValue.S2) && values.contains(Card.CardValue.S3) && values.contains(Card.CardValue.S4) && values.contains(Card.CardValue.S5);
            boolean broadwayStraight = values.contains(Card.CardValue.S10) && values.contains(Card.CardValue.J) && values.contains(Card.CardValue.Q) && values.contains(Card.CardValue.K) && values.contains(Card.CardValue.A);
            straight = normalStraight || wheelStraight || broadwayStraight;
        }

        if (straight && flush) {
                return HandType.STRAIGHT_FLUSH;
            }



            for(List<Card.CardValue> group : groups){
                if (group.size() == 4){
                    return HandType.FOUR_OF_A_KIND;
                }

            }
            if (groups.size()== 2 && groups.getFirst().size() == 3 && groups.getLast().size() == 2 ){
                return HandType.FULL_HOUSE;
            }

        for (List<Card.CardValue> group : groups) {
            if (group.size() == 3) {
                return HandType.TRIPS;
            }}
        if (flush) {
            return HandType.FLUSH;
        }

        if (straight) {
            return HandType.STRAIGHT;
        }


        int pairs = 0;
        for (List<Card.CardValue> group : groups) {
            if (group.size() == 2) {
                pairs++;
            }
        }
        if (pairs == 2) {
            return HandType.TWO_PAIRS;
        }

        if (pairs == 1){
            return HandType.ONE_PAIR;
        }


            System.out.println(groups);

        return HandType.FLUSH;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}