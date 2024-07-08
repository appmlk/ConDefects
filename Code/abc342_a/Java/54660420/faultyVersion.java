// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String S = sc.next();
    if(S.charAt(0)!=S.charAt(1)){
      if(S.charAt(0)!=S.charAt(2)){
        System.out.println(1);
      }
      else{
        System.out.println(2);
      }
      return;
    }
    for(int i=1;i<S.length();i++){
      if(S.charAt(i)!=S.charAt(i+1)){
        System.out.println(i+1);
        return;
      }
    }
    // @Test
    // void addition() {
    // assertEquals(2, 1 + 1);
  }
}