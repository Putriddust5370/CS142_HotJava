import java.util.ArrayList;
import java.util.Random;

public class CardCollection {

  private String label;
  private ArrayList<Card> cards;

  public CardCollection(String label) { //Constructs empty collection.
      this.label = label;
      this.cards = new ArrayList<Card>();
  }

  public void addCard(Card card) {  //Adds the given card to the ArrayList.
    cards.add(card);  //Instance variable accessed without 'this' keyword. 
  }

  public Card popCard(int i) {  //Returns selected card, as well as removing it 
    return cards.remove(i);     //from ArrayList and adjusting the rest of the
  }                             //elements.

  public Card popCard() {   //Overloading popCard to return last one in deck.
    int i = size() - 1;
    return popCard(i);
  }

  public int size() {
    return cards.size();  //Returns number of cards in collection.
  }

  public boolean empty() {
    return cards.size() == 0; //Returns true if size is 0.
  }

  public String getLabel() {    //Returns the label of corresponding CardCollection.
    return label;
  }

  public Card getCard(int i) {
    return cards.get(i);    //Accesses element of index i.
  }

  public Card last() {
    int i = size() - 1;     //Access last card in ArrayList. Does not remove.
    return cards.get(i);
  }

  public void swapCards(int i, int j) {
    Card temp = cards.get(i);
    cards.set(i, cards.get(j));   //Swaps cards between indexes i and j.
    cards.set(j, temp);
  }

  public String toString() {    //Overload method to display a CardCollection.
    return label + ": " + cards.toString();
  }

  public void dealAll(CardCollection that) {  //Deals all cards contained within invoked CC.
    int n = size();
    deal(that, n);
  }
  /* The above methods are wrapper methods. They invoke others without doing much themselves. */

  public void deal(CardCollection that, int n) {
    for (int i = 0; i < n; i++) {
      Card card = popCard();    //Removes n number of cards from the collection it's invoked on.
      that.addCard(card);     //Adds these to the collection in parameter.
    }
  }

  public void shuffle() {   //Randomly shuffles deck of cards.
    Random random = new Random();
    for (int i = size() - 1; i > 0; i--) {
      int j = random.nextInt(i);
      swapCards(i, j);
    }
  }



}