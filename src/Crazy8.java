import java.awt.*;
import java.util.Scanner;

/**
 * Tyler Hinkie
 * 02.16.2024
 */
public class Crazy8 {
    private Crazy8Viewer window;
    public Player[] players;
    public Deck deck;
    public Card pile;
    // Instance variable because I use it so much in my code
    private static final Scanner s = new Scanner(System.in);

    public Crazy8() {
        window = new Crazy8Viewer(this);
        String[] playerNames = playerNames();
        players = new Player[2];
        players[0] = new Player(playerNames[0]);
        players[1] = new Player(playerNames[1]);
        deck = new Deck();
    }

    // Goes through the steps of playing the game
    public void run() {
        printInstructions();
        // Shuffles deck
        deck.shuffle();

        // Turns over first card for the pile
        pile = new Card(deck.deal());
        // Deals the cards to the players
        deal();

        // Simulates all turns, until 100
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 2; j++) {
                System.out.println(players[j].getName() + "'s turn: ");
                window.setIndex(j);
                window.setCardSpacing();
                window.repaint();
                turn(players[j]);
                if (checkWin(players[j])) {
                    window.addStage();
                    window.repaint();
                    System.out.println("Press enter to see the losing player's screen: ");
                    s.nextLine();
                    window.addStage();
                    window.repaint();
                    return;
                }
            }
        System.out.println("Game has ended. More than 100 turns have been played!");
    }

    // Checks if given player has won the game, or has no cards left in their hand, and prints the victor's name
    public boolean checkWin(Player player) {
        if (player.getHand().isEmpty()) {
            System.out.println(player.getName());
            return true;
        }
        return false;
    }

    // Deals the cards to the players in the game
    public void deal() {
        for (Player player : players)
            for (int j = 0; j < 5; j++)
                player.addCard(deck.deal());
    }

    // Simulates a turn
    public void turn(Player player) {
        // Prints the pile
        printPile();
        // Prints the player's hand
        printHand(player);
        // Asks the player for which card they want to play
        int index = pickIndex(player);
        // Checks if the player wants to draw
        if (index == -1) {
            player.addCard(deck.deal());
            return;
        }
        // Checks if the card is a valid card to be played as per the rules
        else if (!check(player.getHand().get(index))) {
            System.out.println("You can't play that card, please input another card or draw with -1.");
            // Restarts the turn if a player has tried to play the improper card
            turn(player);
        }
        player.getHand().remove(index);
    }

    // Runs all of the checks for a valid card
    public boolean check(Card card) {
        // Asks for a suit if the desired card to be played is an 8
        if (card.getPoint() == 8) {
            pile.setSuit(ask8());
            pile.setRank("8");
            pile.setPoint(8);
            return true;
        }
        // Checks if the card is otherwise valid, either matching point or suit
        else if (checkCard(card)) {
            pile.setSuit(card.getSuit());
            pile.setRank(card.getRank());
            pile.setPoint(card.getPoint());
            pile.setImage(card.getImage());
            return true;
        }
        return false;
    }

    // Checks if card matches point or suit of the pile card to be played
    public boolean checkCard(Card card) {
        if (card.getSuit().equals(pile.getSuit())) return true;
        else return card.getPoint() == pile.getPoint();
    }

    // Asks for the suit if the desired card to be played is an 8
    public String ask8() {
        System.out.println("What suit would you like it to be?");
        return s.nextLine();
    }

    // Asks user for index of the card they want to play from their hand, or if they want to draw instead
    public int pickIndex(Player player) {
        System.out.println("Enter a card index or -1 to draw:");
        int index = s.nextInt();
        s.nextLine();
        return index;
    }

    // Prints the players hand
    public void printHand(Player player) {
        System.out.println(player.getHand());
    }

    // Prints the pile or top card in the pile
    public void printPile() {
        System.out.println(pile);
    }

    // Prints beginning of game instructions to explain the game
    public void printInstructions() {
        System.out.println("Hi. Welcome to Crazy 8");
        System.out.println("You will be dealt 5 cards and must match the suit or rank of the top facing card.\nBut, " + "there's a twist: all 8's are wild!\nThis mean that you can switch the suit at any time if you play " + "an 8!\nHave fun!");
        System.out.println("--------------------------------------------------------------------------------------------");
        window.repaint();
    }

    // Asks for the names of each player playing
    public String[] playerNames() {
        String[] playerNames = new String[2];
        System.out.println("What is Player 1's name?");
        playerNames[0] = s.nextLine();
        System.out.println("What is Player 2's name?");
        playerNames[1] = s.nextLine();
        return playerNames;
    }

    public static void main(String[] args) {
        // Creates the game
        Crazy8 c = new Crazy8();
        // Plays the game
        c.run();
    }
}

