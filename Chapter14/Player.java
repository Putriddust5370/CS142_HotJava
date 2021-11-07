public class Player {

  private String name;  
  private Hand hand;

  public String getName() {
    return name;
  }

  public Hand getHand() {
    return hand;
  }

  public void display() {
    hand.display();
  }

  public void displayScore() {
    System.out.println(name + " has " + score() + " points");
  }

  public Player(String name) {    //Takes players name, saves as instance varaible and constructs a
    this.name = name;             // hand with the corresponding label.
    this.hand = new Hand(name);
  }

  public Card play(Eights eights, Card prev) {    //Determines which card to pop out of Player's Hand
    Card card = searchForMatch(prev);             //according to rules of Crazy Eights.
    if (card == null) {
      card = drawForMatch(eights, prev);
    }
    return card;
  }

  public Card searchForMatch(Card prev) {
    for (int i = 0; i < hand.size(); i++) {   //Loops through the player hand.
      Card card = hand.getCard(i);
      if (cardMatches(card, prev)) {    //Compares cards, returning a match if found.
        return hand.popCard(i);
      }
    }
    return null;
  }

  public Card drawForMatch(Eights eights, Card prev) {
    while (true) {
      Card card = eights.draw();        //Continues to draw cards until it finds one that matches Eights criteria.
      System.out.println(name + " draws " + card);
      if (cardMatches(card, prev)) {
        return card;
      }
      hand.addCard(card);       //If not match, add to players hand.
    }
  }

  public static boolean cardMatches(Card c1, Card c2) {   //Compare two cards.
    if (c1.getSuit() == c2.getSuit()) {
      return true;
    }
    if (c1.getRank() == c2.getRank()) {
      return true;
    }
    if (c1.getRank() == 8) {      //This section is specific to rules of CrazyEights.
      return true;
    }
    return false;
  }

  public int score() {        //Tallies up score for invoked player, computing according to C.8's rulset.
    int sum = 0;
    for (int i = 0; i < hand.size(); i++) {
      Card card = hand.getCard(i);
      int rank = card.getRank();
      if (rank == 8) {
        sum -= 20;
      } else if (rank > 10) {
        sum -= 10;
      } else {
        sum -= rank;
      }
    }
    return sum;
  }
}