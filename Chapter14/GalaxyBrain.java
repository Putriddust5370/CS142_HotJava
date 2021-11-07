public class GalaxyBrain extends Player {
  
  public GalaxyBrain(String label) {
    super(label);
  }
/**Oddly enough, I think this one is going to be simpler than BigBrain.
 * It's essentially the same, but instead of getting rid of 8's first,
 * in this one we're going to hoard the 8's for a run at the end. The
 * rest will be the same, sorted high->low, playing face10 first.
 */

  public Card play(Eights eights, Card prev) {
    this.sortIt();
    Card card = theBestAround(prev);
    if (card == null) {
      card = drawForMatch(eights, prev);
    }
    return card;
  }

  public Card theBestAround(Card p) {
    for (int i = 0; i < getHand().size(); i++) {
      Card c = getHand().getCard(i);
      boolean f = cardMatches(c, p);
      if (c.getRank() != 8 && f) {
        if (c.getRank() >= 10) {
          return getHand().popCard(i);
        }
        return getHand().popCard(i);
      }
    }
    return searchForMatch(p);
  }

  public void sortIt() {
    for (int i = 0; i < getHand().size(); i++) {
      int j = iHigh(i, getHand().size());
      getHand().swapCards(i, j);
    }
  }

  public int iHigh(int low, int high) {
    Card h = getHand().getCard(low);
    int i = low;
    for (int j = low; j < high; j++) {
      Card k = getHand().getCard(j);
      if (k.getRank() > h.getRank()) {
        h = getHand().getCard(j);
        i = j;
      }
    }
    return i;
  }

}