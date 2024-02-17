import javax.swing.*;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck() {
        cards = new ArrayList<>();
        // Arrays to hold possible card info
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        // Fills all cards in deck with the info
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                String imageName = String.valueOf(j + 1 + suits.length * i) + ".png"; // Generate image name;
                cards.add(new Card(i + 1, suits[j], ranks[i],
                        new ImageIcon("Resources/Cards/" + imageName).getImage()));
            }
        }

        cardsLeft = cards.size();
    }

    // Checks if deck is empty
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // Gets the number of cards left in the deck
    public int getCardsLeft() {
        return cardsLeft;
    }

    // Deals cards to players
    public Card deal() {
        if (isEmpty())
            return null;
        return cards.remove(--cardsLeft);
    }

    // Shuffles the deck using algorithm on canvas
    public void shuffle() {
        int random;
        for (int i = cardsLeft - 1; i > 0; i--) {
            random = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, temp);
        }
    }
}

