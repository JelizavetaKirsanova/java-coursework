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


    private List<Card.CardValue> values = new ArrayList<>();
    private void setValues(){
        values.clear();
        for (Card card : cards) {
            values.add(card.getValue());
        }
    }

    private List<Card.CardSuit> suits = new ArrayList<>();
    private void setSuits(){
        suits.clear();
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
    }
    private List<List<Card.CardValue>> groups = new ArrayList<>();
    private void setGroups(){
        groups.clear();
        for (Card.CardValue value : values) {
            boolean found = false;
            for (List<Card.CardValue> group : groups) {
                if (group.get(0).equals(value)) {
                    group.add(value);
                    found = true;
                    break;
                }}
            if (!found) {
                List<Card.CardValue> newGroup = new ArrayList<>();
                newGroup.add(value);
                groups.add(newGroup);
            }}
        groups.sort((a, b) -> Integer.compare(b.size(), a.size()));
    }

    private boolean isFlush(){
        boolean flush = true;
        for (Card.CardSuit suit : suits) {
            if (!suit.equals(suits.getFirst())) {
                flush = false;
                break;
            }}
        return flush;
    }

    private boolean isStraight(){

        boolean straight = false;
        if (groups.size() == 5){
            List<Integer> ordinals = new ArrayList<>();
            for (Card.CardValue value : values) {
                ordinals.add(value.ordinal());
            }
            Collections.sort(ordinals);
            if (ordinals.equals(List.of(0, 1, 2, 3, 12))) {
                return true;
            }

            for (int i = 1; i < ordinals.size(); i++) {
                if (ordinals.get(i) != ordinals.get(i - 1) + 1) {
                    return straight;
                }}
            straight = true;
        }

            return straight;
    }





    public HandType getHandType() {
        setValues();
        setSuits();
        setGroups();



        if (isStraight() && isFlush()) {
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
        if (isFlush()) {
            return HandType.FLUSH;
        }
        if (isStraight()) {
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