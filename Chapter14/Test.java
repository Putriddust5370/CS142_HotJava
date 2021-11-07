public class Test extends Eights {
  public static void main(String[] args) {
    Eights first = new Eights();  
    first.playGame();
  }

  public void playGame(int n) { //This is the exact playGame method from Eights.class
  Player player = one;

  while (!isDone()) {
    displayState();
    if (waitForUser().contains("exit")){
      break
    }
    takeTurn(player);
    player = nextPlayer(player);
  }
  one.displayScore();
  two.displayScore();
  }
}