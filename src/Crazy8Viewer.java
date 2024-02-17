import javax.swing.*;
import java.awt.*;

/**
 * Tyler Hinkie
 * 02.16.2024
 * <p>
 * Crazy8Viewer class extends JFrame and represents the graphical user interface for the Crazy 8 game.
 */
public class Crazy8Viewer extends JFrame {
    // Constants defining window dimensions and card sizes
    public static final int WINDOW_WIDTH = 960,
                            WINDOW_HEIGHT = 540,
                            CARD_WIDTH = 100,
                            CARD_HEIGHT = 140,
                            NAME_X = 450,
                            NAME_Y = 55,
                            PILE_X = 500,
                            DECK_X = 360,
                            PILE_AND_DECK_Y = 100,
                            CARD_Y = 300;

    private Crazy8 c;
    private int cardSpacing;
    private int stage;
    private int index;
    private Image back;

    /**
     * Constructor for Crazy8Viewer.
     *
     * @param c The Crazy8 game backend.
     */
    public Crazy8Viewer(Crazy8 c) {
        this.c = c;
        stage = 0;
        cardSpacing = 0;
        index = 0;
        back = new ImageIcon("Resources/Cards/back.png").getImage();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle("Crazy8");
        this.setVisible(true);
        createBufferStrategy(2);
    }

    /**
     * Method to set the spacing between cards based on the window size and player's hand size.
     */
    public void setCardSpacing() {
        cardSpacing = (WINDOW_WIDTH - CARD_WIDTH * c.players[index].getHand().size()) /
                (c.players[index].getHand().size() + 2);
    }

    /**
     * Method to paint the graphics of the current game state.
     *
     * @param g The Graphics object used for painting.
     */
    public void paint(Graphics g) {
        switch (stage) {
            case 0:
                paintWelcome(g);
                return;
            case 1:
                paintTurn(g);
                return;
            case 2:
                paintWin(g);
                return;
            case 3:
                paintLoss(g);
        }
    }

    /**
     * Method to paint the graphics for the player's turn.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintTurn(Graphics g) {
        g.drawImage(new ImageIcon("Resources/GreenScreen.png").getImage(), 0, 0, WINDOW_WIDTH,
                WINDOW_HEIGHT, this);
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        g.drawString(c.players[index].getName() + "'s turn", NAME_X, NAME_Y);
        g.drawImage(c.pile.getImage(), PILE_X, PILE_AND_DECK_Y, CARD_WIDTH, CARD_HEIGHT, this);
        g.drawImage(back, DECK_X, PILE_AND_DECK_Y, CARD_WIDTH, CARD_HEIGHT, this);
        int size = c.players[index].getHand().size();
        for (int i = 0; i < size; i++) {
            g.drawImage(c.players[index].getHand().get(i).getImage(), (i + 1) * cardSpacing + CARD_WIDTH * i,
                    CARD_Y, CARD_WIDTH, CARD_HEIGHT, this);
        }
    }

    /**
     * Method to paint the welcome screen graphics.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintWelcome(Graphics g) {
        g.drawImage(new ImageIcon("Resources/WelcomeScreen.png").getImage(), 0, 0, WINDOW_WIDTH,
                WINDOW_HEIGHT, this);
        stage++;
    }

    /**
     * Method to increment the game stage.
     */
    public void addStage() {
        stage++;
    }

    /**
     * Method to set the current player index.
     *
     * @param i The index of the current player.
     */
    public void setIndex(int i) {
        index = i;
    }

    /**
     * Method to paint the graphics for the losing screen.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintLoss(Graphics g) {
        g.drawImage(new ImageIcon("Resources/LosingScreen.png").getImage(), 0, 0, WINDOW_WIDTH,
                WINDOW_HEIGHT, this);
    }

    /**
     * Method to paint the graphics for the winning screen.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintWin(Graphics g) {
        g.drawImage(new ImageIcon("Resources/WinningScreen.png").getImage(), 0, 0, WINDOW_WIDTH,
                WINDOW_HEIGHT, this);
    }
}
