public class Hand extends CardCollection {

  public Hand(String label) {
    super(label);
  }

  public void display() {
    System.out.println(getLabel() + ": ");
    for (int i = 0; i < size(); i++) {
      System.out.println(getCard(i));
    }
    System.out.println();
  }

  public void display(int j) {
    if (size() <= j) {
      display();
    }
    if (size() > j) {
    System.out.println(getLabel() + ": ");
      for (int i = 1; i <= j; i++) {
        System.out.println(getCard(size() - i));
      }
    }
    System.out.println();
  }
}