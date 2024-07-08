// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long X = sc.nextLong();
    long result;
    if (X > 0) {
      result = ((X-1)/ 10) + 1;
    } else {
      result = (X/ 10);
    }

    System.out.println(result);
    // @Test
    // void addition() {
    // assertEquals(2, 1 + 1);
  }
}