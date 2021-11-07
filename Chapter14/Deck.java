public class Deck extends CardCollection {    //Deck inherits instance variables and methods
                                              //from CardCollection. 
  public Deck(String label) {
    super(label);         /*The super keyword refers to the superclass, which in this case
                            is CardCollection. When used like a method, as shown here, it
                            invokes the constructor of the superclass. */
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <=13; rank++) {
        addCard(new Card(rank, suit));
      }
    }
  }
}