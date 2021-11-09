import java.util.Arrays;

public class Card {

  public static final String[] RANKS = {
    null, "Ace", "2", "3", "4", "5", "6", "7",
    "8", "9", "10", "Jack", "Queen", "King"};
  
  public static final String[] SUITS = {
    "Clubs", "Diamonds", "Hearts", "Spades"};

  private final int rank;
  private final int suit;

    public static void main(String[] args) {
      Card[] hand = makeHand(5);
/*      hand[0] = new Card(1, 1);
      hand[1] = new Card(2, 1);
      hand[2] = new Card(3, 1);
      hand[3] = new Card(4, 1);
      hand[4] = new Card(5, 1); */
      System.out.println(Arrays.toString(hand));
    }

  public static Card[] makeHand(int n) {
    Card[] hand = new Card[n];
      for (int i = 0; i < n; i++) {
        hand[i] = new Card(i + n, 3);
      }
    return hand;
  }

  public static Card[] makeDeck() {
    Card[] cards = new Card[52];
    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
        cards[index] = new Card(rank, suit);
        index++;
      }
    }
    //System.out.println(Arrays.toString(cards));
    return cards;
  }

  public static boolean hasFlush(Card[] hand) {
    int[] count = suitHist(hand);
    for (int cards : count) {
      if (cards >= 5) {
        return true;
      }
    }
    return false;
  }

  //Make a histogram to detect how many cards of each suit there are.
  //One traversal.
  public static int[] suitHist(Card [] hand) {
    int[] suitTracker = new int[4];
    for (Card cards : hand) {
      suitTracker[cards.suit]++;
    }
    return suitTracker;
  }

  public Card(int rank, int suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public int getRank() {
    return this.rank;
  }

  public int getSuit() {
    return this.suit;
  }

  public int position() {
    return this.suit * 13 +
      this.rank - 1;
  }

  public String toString() {
    return RANKS[this.rank] + " of " + SUITS[this.suit];
  }

  public boolean equals(Card that) {
    return this.rank == that.rank
     && this.suit == that.suit;
  }

  public int compareTo(Card that) {
    if (this.suit < that.suit) {
      return -1;		// This is bigger than that == 1.
    }
    if (this.suit > that.suit) {
      return 1;
    }
//    if (this.rank == 2 && that.rank != 2) {
//      return 1;
//    }
//    if (that.rank == 2 && this.rank != 2) {
 //     return -1;
  //  }

    if (this.rank < that.rank) {
      return -1;
    }
    if (this.rank > that.rank) {
      return 1;
    }
    return 0;
  }
}
