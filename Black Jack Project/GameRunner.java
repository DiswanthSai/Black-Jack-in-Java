import java.util.Scanner;

public class GameRunner {
    private static final int MIN_DEALER_HAND_SUM = 17;
    private static final int MAX_HAND_SUM = 21;

    public static void main(String[] args) {

        Deck theDeck = new Deck(1, true);

        Scanner in = new Scanner(System.in);

        String asciiArt =
                " /$$$$$$$  /$$                           /$$          /$$$$$                    /$$      \n" +
                        "| $$__  $$| $$                          | $$         |__  $$                   | $$      \n" +
                        "| $$  \\ $$| $$        /$$$$$$   /$$$$$$$| $$   /$$      | $$ /$$$$$$   /$$$$$$$| $$   /$$\n" +
                        "| $$$$$$$ | $$       |____  $$ /$$____/| $$  /$$/      | $$|___  $$ /$$_____/| $$  /$$/\n" +
                        "| $$__  $$| $$        /$$$$$$$| $$      | $$$$$$/  /$$  | $$ /$$$$$$$| $$      | $$$$$$/ \n" +
                        "| $$  \\ $$| $$       /$$__  $$| $$      | $$_  $$ | $$  | $$/$$__  $$| $$      | $$_  $$ \n" +
                        "| $$$$$$$/| $$$$$$$$|  $$$$$$$|  $$$$$$$| $$ \\  $$|  $$$$$$/  $$$$$$$|  $$$$$$$| $$ \\  $$\n" +
                        "|_______/ |________/ \\_______/ \\_______/|__/  \\__/ \\______/ \\_______/ \\_______/|__/  \\__/\n";

        System.out.println("\n\n\n\n");
        System.out.println(asciiArt);

        System.out.println("\n\t\t\t\t\tWelcome to Blackjack!\n\n");
        System.out.println("Please enter your name:");
        String P_Name = in.nextLine();

        System.out.println(P_Name + ", let's start the game!\n");

        Player me = new Player(P_Name);
        Player dealer = new Player("Dealer");

        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());
        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());

        System.out.println("Cards are dealt\n");
        me.printHand(true);
        dealer.printHand(false);
        System.out.println();

        boolean meDone = false;
        boolean dealerDone = false;
        String ans;
        try (Scanner sc = new Scanner(System.in)) {
            while (!meDone || !dealerDone) {
                if (!meDone) {
                    System.out.print("These are your options: ");
                    System.out.println("\n\n1 - Hit\n2 - Stand\n");
                    ans = sc.next();
                    System.out.println();
                    if (ans.equalsIgnoreCase("1")) {
                        meDone = !me.addCard(theDeck.dealNextCard());
                        me.printHand(true);
                        dealer.printHand(false);
                    } else if(ans.equalsIgnoreCase("2")){
                        meDone = true;
                    }else{
                        System.out.println("Invalid input!The input Should be either 1 or 2");
                    }
                } else if (!dealerDone && dealer.getHandSum() < MIN_DEALER_HAND_SUM) {
                    System.out.println("The Dealer hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(false);
                } else {
                    System.out.println("The Dealer Stands\n");
                    dealerDone = true;
                }
                System.out.println();
            }
            System.out.println("These are the final cards\n\n");
            me.printHand(true);
            dealer.printHand(true);

            int mySum = me.getHandSum();
            int dealerSum = dealer.getHandSum();

            if(mySum>21){
                System.out.println("\nYour Sum : "+ mySum );
                System.out.println("You are BUSTED");
                System.out.println("\nThe Dealer's Sum :"+ dealerSum);
            }else if(dealerSum>21){
                System.out.println("\nYour Sum : "+ mySum );
                System.out.println("\nThe Dealer's Sum :"+ dealerSum);
                System.out.println("\nThe Dealer is  BUSTED");
            } else if (mySum == 21||dealerSum == 21) {
                System.out.println("\nYour Sum : "+ mySum );
                System.out.println("\nThe Dealer's Sum :"+ dealerSum);

                System.out.print("  ____  _                _        _            _    \n");
                System.out.print(" |  _ \\| |              | |      | |          | |   \n");
                System.out.print(" | |_) | |     __ _  ___| | __   | | __ _  ___| | __\n");
                System.out.print(" |  _ <| |    / _` |/ __| |/ /   | |/ _` |/ __| |/ /\n");
                System.out.print(" | |_) | |___| (_| | (__|   < |__| | (_| | (__|   < \n");
                System.out.print(" |____/|______\\__,_|\\___|_|\\_\\____/ \\__,_|\\___|_|\\_\\\n");
                System.out.print("                                                    \n");
                System.out.print("                                                    \n");

            }else{
                System.out.println("\nYour Sum : "+ mySum );
                System.out.println("\nThe Dealer's Sum :"+ dealerSum);
            }

            if (mySum > dealerSum && mySum <= MAX_HAND_SUM || dealerSum > MAX_HAND_SUM) {
                System.out.println(P_Name +" WINS!");
            } else {
                System.out.println("DEALER WINS!");
            }
        }
    }
}