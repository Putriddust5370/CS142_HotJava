import java.util.Arrays;

public class Card {

  public static final String[] RANKS = {
    null, "Ace", "2", "3", "4", "5", "6", "7",  //Values of all 13 cards in a suit.
    "8", "9", "10", "Jack", "Queen", "King"};

  public static final String[] SUITS = { 
    "Clubs", "Diamonds", "Hearts", "Spades"};   //All four suits in a standard deck of cards.

  private final int rank;
  private final int suit;

  public static Card[] makeDeck() {   //Constructs a standard deck of 52 cards.
    Card[] cards =  new Card[52];
    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
        cards[index] = new Card(rank, suit);
        index++;
      }
    }
    return cards;
  }

  public Card(int rank, int suit) {   //Builds a card with the appropriate rank and suit.
    this.rank = rank;
    this.suit = suit;
  }

  public int getRank() {    //Retrieves the number corresponding to the rank of the initialized card.
    return this.rank;
  }

  public int getSuit() {    //Same as above but for suit.
    return this.suit;
  }

  public int position() {   //Returns the position of the initialized card in a standard deck.
    return (this.suit * 13) + (this.rank - 1);
  }

  public String toString() {
    return RANKS[this.rank] + " of " + SUITS[this.suit];  //Organizes the card data in an easily read format.
  }

  public boolean equals(Card that) {
    return this.rank == that.rank && this.suit == that.suit;  //Retruns true if the cards are equal rank and suit.
  }

  public int compareTo(Card that) {
    if (this.suit < that.suit) {
      return -1; 
    }
    if (this.suit > that.suit) {    //first.compareTo(second), this method will return a 1 if first is larger
      return 1;                     //in either suit or rank (prioritized in that order). Will return 0 if cards 
    }                               //are identical.
    if (this.rank < that.rank) {
      return -1;
    }
    if (this.rank > that.rank) {
      return 1;
    }
    return 0;
  }
  
}