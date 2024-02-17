import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    // unused in this game
    private int points;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        points = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = new ArrayList<>();
        points = 0;
        this.hand.addAll(hand);
    }

    // accessor methods for instance variables
    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int num) {
        points += num;
    }

    // adds a card to the players hand, key for dealing and drawing
    public void addCard(Card c) {
        hand.add(c);
    }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
