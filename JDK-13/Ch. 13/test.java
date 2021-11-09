import java.util.Random;

public class test {

    public static Random rando = new Random();
    public static int tester = randomInt(1, 5);

  public static void main(String[] args) {
    System.out.println(tester);
  }
  public static int randomInt(int low, int high) {
    int result = low + rando.nextInt(high - low + 1);
    return result;  // Change.
  }
}
