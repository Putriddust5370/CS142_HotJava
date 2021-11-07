import java.util.Scanner;

public class Eights {

  private GalaxyBrain one;
  private BigBrain two;
  private Hand drawPile;
  private Hand discardPile;
  private Scanner in;

  public Eights() {    //Creates components for Crazy Eights.
    Deck deck = new Deck("Deck");
    deck.shuffle();

    int handSize = 5;

    one = new GalaxyBrain("Ryker");
    deck.deal(one.getHand(), handSize);

    two = new BigBrain("Lt.Co. Data");
    deck.deal(two.getHand(), handSize);

    discardPile = new Hand("Discards");
    deck.deal(discardPile, 1);

    drawPile = new Hand("Draw Pile");
    deck.dealAll(drawPile);

    in = new Scanner(System.in);
  }

  public boolean isDone() {   //Rules of C.E. say game is over when hand is empty.
    return one.getHand().empty() || two.getHand().empty();
  }

  public void reshuffle() {   //Selects top card of discard pile, transfers to drawPile, shuffle.
    Card prev = discardPile.popCard();
    discardPile.dealAll(drawPile);
    discardPile.addCard(prev);
    drawPile.shuffle();
  }

  public Card draw() {    //Draws top card from drawPile, reshuffling if empty.
    if (drawPile.empty()) {
      reshuffle();
    }
    return drawPile.popCard();
  }

  public Player nextPlayer(Player current) {  //Needs to be changed!!!
    if (current == one) {   //Currently changes between players, taking current and returning next.
      return two;
    } else {
      return one;
    }
  }

  public void displayState() {      //Displays current state of the game. Including both player hands,
    one.display();                  //discard pile, and remaining number of cards in drawPile.
    two.display();
    discardPile.display(3);
    System.out.println("Draw Pile:");
    System.out.println(drawPile.size() + " cards");
  }

  public String waitForUser() {
    return in.nextLine();
  }

  public void takeTurn(Player player) {   //Executes given player's turn.
    Card prev = discardPile.last();
    Card next = player.play(this, prev);    //Passes top card of discardPile to player's play method.
    discardPile.addCard(next);              //Adds chosen card to discardPile.

    System.out.println(player.getName() + " plays " + next);
    System.out.println();
  }

  public void playGame() {
    Player player = one;

    //Keep playing until there is a winner.
    while (!isDone()) {
      displayState();
      if (waitForUser().contains("exit")){
        break;
      }
      takeTurn(player);
      player = nextPlayer(player);
    }

    //Display final score.
    one.displayScore();
    two.displayScore();
  }

}