import java.util.ArrayList;

/**
 * An implementation of a Blackjack player
 */
public class Player {
    /**
     * The player's name
     */
    private String name;
    /**
     * The cards in the player's current hand
     */
    private ArrayList<Card> hand;
    /**
     * The sum of the cards in the player's current hand.
     */
    private int handSum;

    /**
     * Player Constructor
     */
    public Player(String aName) {
        name = aName;
        hand = new ArrayList<>();
        handSum = 0;
    }

    public void emptyHand() {
        hand.clear();
        handSum = 0;
    }

    public boolean addCard(Card aCard) {
        if (hand.size() >= 10) {
            return false;
        }

        hand.add(aCard);
        handSum = getHandSum();

        return (handSum <= 21);
    }

    public int getHandSum() {
        int handSum = 0;
        int numAces = 0;

        for (Card card : hand) {
            int cardNum = card.getNumber();
            switch (cardNum) {
                case 1:
                    numAces++;
                    handSum += 11;
                    break;
                case 11:
                case 12:
                case 13:
                    handSum += 10;
                    break;
                default:
                    handSum += cardNum;
                    break;
            }
        }

        while (handSum > 21 && numAces > 0) {
            handSum -= 10;
            numAces--;
        }

        return handSum;
    }

    public void printHand(boolean showFirstCard) {
        System.out.printf("%s's cards:\n", name);
        for (int c = 0; c < hand.size(); c++) {
            if (c == 0 && !showFirstCard) {
                System.out.println("  [hidden]");
            } else {
                System.out.printf("   %s\n", hand.get(c).toString());
            }
        }
    }
}