import java.util.Random;

public class Deck {
    private Card[] myCards;
    private int numCards;

    public Deck() {
        this(1, false);
    }

    public Deck(int numDecks, boolean shuffle) {
        this.numCards = numDecks * 52;
        this.myCards = new Card[this.numCards];
        int c = 0;

        for (int d = 0; d < numDecks; d++) {
            for (Suit s : Suit.values()) {
                for (int n = 1; n <= 13; n++) {
                    this.myCards[c] = new Card(s, n);
                    c++;
                }
            }
        }
        if (shuffle) {
            this.shuffle();
        }
    }

    public void shuffle() {
        Random rng = new Random();
        for (int i = this.numCards - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            Card temp = this.myCards[i];
            this.myCards[i] = this.myCards[j];
            this.myCards[j] = temp;
        }
    }

    public Card dealNextCard() {
        Card top = this.myCards[this.numCards - 1];
        this.myCards[this.numCards - 1] = null;
        this.numCards--;
        return top;
    }

    public void printDeck(int numtoPrint) {
        for (int c = 0; c < numtoPrint; c++) {
            System.out.printf("%3d/%d %s\n", c + 1, this.numCards, this.myCards[this.numCards - numtoPrint + c].toString());
        }
        System.out.printf("\t[%d other]\n", this.numCards - numtoPrint);
    }
}