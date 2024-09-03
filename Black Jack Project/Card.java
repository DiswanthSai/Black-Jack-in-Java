import java.util.HashMap;
import java.util.Map;

public class Card {
    private Suit mySuit;
    private int myNumber;

    public Card(Suit aSuit, int aNumber) {
        this.mySuit = aSuit;
        if (aNumber >= 1 && aNumber <= 13) {
            this.myNumber = aNumber;
        } else {
            System.out.println(aNumber + "is not a valid card Number");
            System.exit(1);
        }
        this.myNumber = aNumber;
    }

    public int getNumber() {
        return myNumber;
    }

    public String toString() {
        String numStr = "Error";
        Map<Integer, String> numStringMap = new HashMap<>();
        numStringMap.put(1, "Ace");
        numStringMap.put(2, "Two");
        numStringMap.put(3, "Three");
        numStringMap.put(4, "Four");
        numStringMap.put(5, "Five");
        numStringMap.put(6, "Six");
        numStringMap.put(7, "Seven");
        numStringMap.put(8, "Eight");
        numStringMap.put(9, "Nine");
        numStringMap.put(10, "Ten");
        numStringMap.put(11, "Jack");
        numStringMap.put(12, "Queen");
        numStringMap.put(13, "King");

        if (numStringMap.containsKey(myNumber)) {
            numStr = numStringMap.get(myNumber);
        }

        return numStr + " of " + mySuit.toString();
    }
}