import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class week6 {
    public static class Card {
        private int value;
        private String name;

        public Card(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void describe() {
            System.out.println(name);
        }
    }

    public static class Deck {
        private List<Card> cards;

        public Deck() {
            cards = new ArrayList<Card>();
            for (int value = 2; value <= 14; value++) {
                for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
                    String name = getValueName(value) + " of " + suit;
                    cards.add(new Card(value, name));
                }
            }
        }

        public void shuffle() {
            Collections.shuffle(cards);
        }

        public Card draw() {
            if (!cards.isEmpty()) {
                return cards.remove(0);
            }
            return null;
        }

        private String getValueName(int value) {
            if (value >= 11 && value <= 14) {
                switch (value) {
                    case 11:
                        return "Jack";
                    case 12:
                        return "Queen";
                    case 13:
                        return "King";
                    case 14:
                        return "Ace";
                }
            }
            return Integer.toString(value);
        }
    }

    public static class Player {
        private List<Card> hand;
        private int score;
        private String name;

        public Player(String name) {
            this.hand = new ArrayList<Card>();
            this.score = 0;
            this.name = name;
        }

        public void describe() {
            System.out.println("Player: " + name);
            for (Card card : hand) {
                card.describe();
            }
        }

        public Card flip() {
            if (!hand.isEmpty()) {
                return hand.remove(0);
            }
            return null;
        }

        public void draw(Deck deck) {
            Card card = deck.draw();
            if (card != null) {
                hand.add(card);
            }
        }

        public void incrementScore() {
            score++;
        }

        public int getScore() {
            return score;
        }
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        deck.shuffle();

        for (int i = 0; i < 52; i++) {
            player1.draw(deck);
            player2.draw(deck);
        }

        for (int i = 0; i < 26; i++) {
            Card card1 = player1.flip();
            Card card2 = player2.flip();

            card1.describe();
            card2.describe();

            if (card1.getValue() > card2.getValue()) {
                player1.incrementScore();
                System.out.println("Player 1 receives a point.");
            } else if (card1.getValue() < card2.getValue()) {
                player2.incrementScore();
                System.out.println("Player 2 receives a point.");
            } else {
                System.out.println("It's a tie. No point awarded.");
            }
        }

        int score1 = player1.getScore();
        int score2 = player2.getScore();

        System.out.println("Final Score - Player 1: " + score1 + ", Player 2: " + score2);
        if (score1 > score2) {
            System.out.println("Player 1 wins!");
        } else if (score1 < score2) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}
