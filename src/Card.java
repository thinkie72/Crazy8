import java.awt.*;

public class Card {
    private int point;
    private String suit;
    private String rank;
    private Image i;

    // Normal constructor
    public Card(int point, String suit, String rank, Image i){
        this.point = point;
        this.suit = suit;
        this.rank = rank;
        this.i = i;
    }

    // Constructor to make copies of cards
    public Card(Card card) {
        point = card.getPoint();
        suit = card.getSuit();
        rank = card.getRank();
        i = card.getImage();
    }

    // Accessor and mutator methods for each card
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Image getImage() {
        return i;
    }

    public void setImage(Image i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

