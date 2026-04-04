package collections.simulator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class Simulator {


    private final int iterations;

    public Simulator(double iterations) {
        this.iterations = (int) iterations;

    }

    public Map<HandType, Double> calculateProbabilities() {
        Map<HandType, Double> results = new EnumMap<>(HandType.class);

        for (HandType type : HandType.values()) {
            results.put(type, 0.0);
        }

        for (int i = 0; i < iterations; i++) {
            Deck deck = new Deck();
            deck.shuffle();

            Hand hand = new Hand();
            for (int j = 0; j < 5; j++) {
                hand.addCard(deck.drawCard());
            }

            HandType type = hand.getHandType();
            results.put(type, results.get(type) + 1.0);
        }

        for (HandType type : HandType.values()) {
            results.put(type, results.get(type)*100 / iterations);
        }

        return results;

    }

    public double getWinningOdds(Hand player1hand, Hand player2hand) {

        int player1Wins = 0;
        int ties = 0;

        for (int i = 0; i < iterations; i++) {
            int comparison = player1hand.compareTo(player2hand);
            System.out.println(comparison);

            if (comparison > 0) {
                player1Wins++;
            } else if (comparison == 0) {
                ties++;
            }
        }

        return ((player1Wins + ties * 0.5) / iterations)*100.0;

    }

}
