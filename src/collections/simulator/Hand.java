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

    private final List<Card.CardValue> values = new ArrayList<>();
    private final List<Card.CardSuit> suits = new ArrayList<>();
    private final List<List<Card.CardValue>> groups = new ArrayList<>();
    private final List<Integer> ordinals = new ArrayList<>();
    private void prepareData() {
        values.clear();
        suits.clear();
        groups.clear();
        ordinals.clear();


        for (Card card : cards) {
            values.add(card.getValue());
            suits.add(card.getSuit());
        }
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

        for (Card.CardValue value : values) {
            ordinals.add(value.ordinal());
        }
        Collections.sort(ordinals);

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

    private Boolean isFourOfAKind(){
        for(List<Card.CardValue> group : groups){
            if (group.size() == 4){
                return true;
            }
        }
        return false;
    }
    private boolean isFullHouse(){
        if (groups.size()== 2 && groups.getFirst().size() == 3 && groups.getLast().size() == 2 ){
            return true;
        }
        return false;
    }
    private boolean isTrips(){
        for (List<Card.CardValue> group : groups) {
            if (group.size() == 3) {
                return true;
            }}
        return false;
    }
    private int pairs() {
        int pairs = 0;
        for (List<Card.CardValue> group : groups) {
            if (group.size() == 2) {
                pairs++;
            }
        }
        return pairs;
    }




    public HandType getHandType() {
        prepareData();

        if(isStraight()&&isFlush()){
            return HandType.STRAIGHT_FLUSH;
        }
        if(isFourOfAKind()){
            return HandType.FOUR_OF_A_KIND;
        }
        if(isFullHouse()){
            return HandType.FULL_HOUSE;
        }
        if(isFlush()){
            return HandType.FLUSH;
        }
        if(isStraight()){
            return HandType.STRAIGHT;
        }
        if(isTrips()){
            return HandType.TRIPS;
        }
        if (pairs()!=0 ){
            if (pairs()==1){
                return HandType.ONE_PAIR;

            }
            return HandType.TWO_PAIRS;
        }
        return HandType.HIGH_CARD;


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

    private int getHandRating(){
        prepareData();
        if(isStraight()&&isFlush()){
            return 9;
        }
        if(isFourOfAKind()){
            return 8;
        }
        if(isFullHouse()){
            return 7;
        }
        if(isFlush()){
            return 6;
        }
        if(isStraight()){
            return 5;
        }
        if(isTrips()){
            return 4;
        }
        if (pairs()!=0 ){
            if (pairs()==1){
                return 2;

            }
            return 3;
        }
        return 1;
    }




    private List<Integer> getTieBreakers() {
        prepareData();

        HandType type = getHandType();
        List<Integer> result = new ArrayList<>();

        switch (type) {
            case STRAIGHT_FLUSH:
            case STRAIGHT:

                break;

            case FOUR_OF_A_KIND:
                result.add(groups.get(0).get(0).ordinal());
                result.add(groups.get(1).get(0).ordinal());
                break;

            case FULL_HOUSE:
                result.add(groups.get(0).get(0).ordinal());
                result.add(groups.get(1).get(0).ordinal());
                break;

            case FLUSH:
            case HIGH_CARD:
                Collections.reverse(ordinals);
                result.addAll(ordinals);
                break;

            case TRIPS:
                result.add(groups.get(0).get(0).ordinal());

                List<Integer> tripsKickers = new ArrayList<>();
                for (int i = 1; i < groups.size(); i++) {
                    tripsKickers.add(groups.get(i).get(0).ordinal());
                }
                tripsKickers.sort(Collections.reverseOrder());
                result.addAll(tripsKickers);
                break;

            case TWO_PAIRS:
                List<Integer> pairValues = new ArrayList<>();
                int kicker = -1;

                for (List<Card.CardValue> group : groups) {
                    if (group.size() == 2) {
                        pairValues.add(group.get(0).ordinal());
                    } else {
                        kicker = group.get(0).ordinal();
                    }
                }

                pairValues.sort(Collections.reverseOrder());
                result.addAll(pairValues);
                result.add(kicker);
                break;

            case ONE_PAIR:
                result.add(groups.get(0).get(0).ordinal());

                List<Integer> pairKickers = new ArrayList<>();
                for (int i = 1; i < groups.size(); i++) {
                    pairKickers.add(groups.get(i).get(0).ordinal());
                }
                pairKickers.sort(Collections.reverseOrder());
                result.addAll(pairKickers);
                break;

            default:
                break;
        }

        return result;
    }




    @Override
    public int compareTo(Hand other) {
        int thisRating = getHandRating();
        int otherRating = other.getHandRating();

        if (thisRating > otherRating) {
            return 1;
        }
        if (thisRating < otherRating) {
            return -1;
        }

        List<Integer> thisTieBreakers = getTieBreakers();
        List<Integer> otherTieBreakers = other.getTieBreakers();

        for (int i = 0; i < thisTieBreakers.size(); i++) {
            int compare = Integer.compare(
                    thisTieBreakers.get(i),
                    otherTieBreakers.get(i)
            );

            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }


}