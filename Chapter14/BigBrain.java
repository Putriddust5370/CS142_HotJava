public class BigBrain extends Player {
  //Override play method from Player class.
  //Want to prioritize cards of the highest point value first.
  //8 > Face > Value.
  public BigBrain(String label) {
    super(label);
  }
  /**First, sort deck from highest to lowest value (ignoring suit), with smartySort().
   * 
   * Then run findTheBest, which prioiritze returning an 8, then a face || 10.
   *
   * if you can't findTheBest, then the hand will play the next available card, which
   * thanks to smartySort() will be the highest point value available.
   */
  public Card play(Eights eights, Card prev) {
    this.smartySort();
    Card card = findTheBest(prev);
    if (card == null) {
      card = drawForMatch(eights, prev);
    }
    return card;
  }

  //This method swaps cards in the deck so that they are filed highest to lowest.
  public void smartySort() {
    for (int i = 0; i < getHand().size(); i++) {
      int j = indexHighest(i, getHand().size());
      getHand().swapCards(i, j);
    }
  }
  //Returns the index of the highest value card, ignoring suit.
  public int indexHighest(int low, int high) {
    Card highest = getHand().getCard(low);
    int index = low;
    for (int i = low; i < high; i++) {
      Card check = getHand().getCard(i);
      if (check.getRank() > highest.getRank()) {
        highest = getHand().getCard(i);
        index = i;
      }
    }
    return index;
  }
  //The cornerstone of BigBrain, prioritizes playing 8's, then Face-10.
  public Card findTheBest(Card prev) {
    for (int i = 0; i < getHand().size(); i++) {
      Card card = getHand().getCard(i);
      if (card.getRank() == 8) {    
        return getHand().popCard(i);
      }
      if (card.getRank() >= 10 && cardMatches(card, prev)) {
        return getHand().popCard(i);
      }
    }
    return searchForMatch(prev);
  }
}