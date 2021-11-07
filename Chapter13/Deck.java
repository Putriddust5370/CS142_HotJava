import java.util.Arrays;
import java.util.Random;

public class Deck {
  
  private Card[] cards;
  private Random rando = new Random();

  public static void main(String[] args) {
    Deck hTest = new Deck(10);
    hTest.Hand(10);
    hTest.print();
    System.out.println("Above is the first deck, since we know shuffle works, ideally next will be the merged deck.");
    hTest.shuffle();
    hTest = hTest.goodMergeSort();
    System.out.println(hTest);
  }

 /* public void split(Deck d) {
  //  System.out.println(d.cards.length);
    Deck d1 = d.subdeck(0, d.cards.length / 2);
    Deck d2 = d.subdeck(((d.cards.length / 2) + 1), d.cards.length - 1);

    d1.selectionSort();
    d2.selectionSort();

    Deck combine = merge(d1, d2);
    combine.print();
  } */

  public Deck Hand(int n) { //Creates a small deck named Hand with n number of randomized cards.
    Deck hand = new Deck(n);
    for (int i = 0; i < n; i++) {
      this.cards[i] = new Card(randomInt(1, 13), randomInt(0, 3)); //Semi-busted since it can duplicate cards.
    }
    return hand;
  }

  public Deck() {
    this.cards = new Card[52];
    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
        cards[index] = new Card(rank, suit);
        index++;
      }
    } //Creates a standard deck of 52 cards, in order. Suits arranged CDHS
  }

  public Deck(int n) {  //Creates a deck n cards long, all null type.
    this.cards = new Card[n];
  }

  public Card[] getCards() {
    return this.cards;
  }

  public void print() {
    for (int i = 0; i < this.cards.length; i++) {
      System.out.println(this.cards[i]);
    }//Prints the cards in given deck, line by line.
  }

  public String toString() {
    StringBuilder hand = new StringBuilder();
    for (int i = 0; i < this.cards.length; i++) {
      hand.append(cards[i]);
      hand.append('\n');
    }
    return hand.toString();
  }

  public String cheatToString() {
    Card[] that = this.getCards();
    return Arrays.toString(that);
  }

  public int randomInt(int low, int high) { //Gives a random integer in range of parameters (inclusive).
    return (low + rando.nextInt(high - low + 1));
  }

  public void swapCards(int i, int j) {
    Card placeholder = cards[i];
    cards[i] = cards[j];
    cards[j] = placeholder; //Swaps the cards at indexes i and j.
  }

  public void shuffle() {
    /* For each index i, choose a random number between i and length - 1
      and swap the ith card with the randomly-chosen card. */
  for (int i = 0; i < this.cards.length; i++) { //Loop that traverses the Deck
    int swapIndex = randomInt(i, this.cards.length - 1);  //Generate random index to end of deck.
    swapCards(i, swapIndex);  //Swap cards between two indexes. 
  }
  }

  public int indexLowest(int low, int high) { //Use compareCard to find lowest card in between indexes low and high.
  Card lowest = cards[low]; //Assigns card and index at low int as
  int index = low;          // lowest values to start.
  for (int i = low; i < high; i++) {
    if (cards[i].compareTo(lowest) < 1) {
      lowest = cards[i];    //Traverse array up to high int, keeping index of lowest card found.
      index = i;
    }
  }
  return index;
  }

  public void selectionSort() {
    // For each index i, find the lowest card to at or to the right of i
      // swap it and the ith card.
    for (int i = 0; i < this.cards.length; i++) {
      this.swapCards(i, this.indexLowest(i, this.cards.length));
    }  
  }

  public Deck subdeck(int low, int high) { //Create a smaller deck within the indexes of the int range.
    if (this.cards.length <= 1) {
      return this;
    }
    Deck sub = new Deck(high - low + 1);
    for (int i = 0; i < sub.cards.length; i++) {
      sub.cards[i] = this.cards[low + i];
    }
    return sub;
  }

  public static Deck goodMerge(Deck d1, Deck d2) { //Combines two previously sorted subdecks.
    Card[] c1 = d1.getCards();
    Card[] c2 = d2.getCards();
    int l1 = c1.length;
    int l2 = c2.length;

    Card[] sorted = new Card[l1 + l2];

    int i = 0;
    int j = 0;

    if (l1 == 0) {
      sorted = c2;
    } else if (l2 == 0) {
      sorted = c1;
    } else {
      for (int k = 0; k < sorted.length; k++) {
        if (j >= c2.length || i < c1.length && c1[i].compareTo(c2[j]) <= 0) {
          sorted[k] = c1[i];
          i++;
        } else {
          sorted[k] = c2[j];
          j++;
        }
      }
    }
    Deck deck = new Deck(l1 + l2);
    deck.cards = sorted;
    return deck;
  }

  public Deck goodMergeSort() { //goodMerge needs to be fully operational before implementing this.
    int len = cards.length;
    if (len == 0 || len == 1) { //otherwise, it's elegant.
      return this;
    } else {
      int mid = len / 2;
      Deck d1 = subdeck(0, mid - 1).goodMergeSort();
      Deck d2 = subdeck(mid, len - 1).goodMergeSort();
      return goodMerge(d1, d2);
    }
  }
/*
  public static Deck merge(Deck d1, Deck d2) {  //Merge two unsorted decks together, using selectionSort() to order
    Deck d3 = new Deck(d1.cards.length + d2.cards.length);
    //System.out.println(d3.cards.length);
    int i = 0;   //i to keep track of d1 position
    int j = 0;   //j to keep track of d2 position
    for (int k = 0; k < d3.cards.length; k++) {
      if (i == d1.cards.length) {
        //d2 wins if d1 is empty
        d3.cards[k] = d2.cards[j];
        break;
      }
      if (j == d2.cards.length) {
        //d1 winds if d2 is empty
        d3.cards[k] = d1.cards[i]; 
        break;
      }
      if (d1.cards[i].compareTo(d2.cards[j]) < 1) {
        d3.cards[k] = d1.cards[i];
        i++;
      } else if (d2.cards[j].compareTo(d1.cards[i]) < 1) {
        d3.cards[k] = d2.cards[j];
        j++;
      }
      //Compare two cards, add winner to d3 at index k
      //increment winning deck.
    }
    return d3;
  }

  public Deck mergeSort() {
    if (this.cards.length == 0) {
      return this;    //If deck is 0 or 1 cards long, return it.
    }
    if (this.cards.length == 1) {
      return this;
    }

    Deck d1 = this.subdeck(0, this.cards.length / 2); //Split deck into two subdecks.
    Deck d2 = this.subdeck(((this.cards.length / 2) + 1), this.cards.length - 1);


    d1 = d1.mergeSort();
    d2 = d2.mergeSort();  //Sort subdecks.


    Deck that = merge(d1, d2);  //Merge subdecks and return them.

    return that;
  }

  int l = h.length;
    for (int j = 1; j < l; j++) {
      int k = h[j];
      int i = j - 1;
      while((i > -1) && (h[i] > k )) {
        h[i + 1] = h[i];
        i--;
      }
      h[i + 1] = k;
    }
    Card[] hand = this.getCards();
    int hL = hand.length;
    for (int i = 1; i > hL; i++) {  //Start at second index, compare to first.
      if (hand[i].compareTo(hand[i - 1]) > 0) { 
        i++;
      } else if (hand[i].compareTo(hand[i - 1]) < 0) {
        hand[i].swapCards(hand[i - 1]);
      }
    }
  */

  public void insertionSort() {
    for (int i = 1; i < cards.length; i++) {
      for ( int j = 1; j > 0; j--) {
        if (cards[j-1].compareTo(cards[j]) == 1) {
          swapCards(j-1, j);
        } else {
          break;
        }
      }
    }
  }
}