// import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;
import java.io.*;
// import org.junit.jupiter.api.Test;

public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(System.in);
    
    int len = scan.nextInt();
    char[][] a = new char[len][len];
    char[][] b = new char[len][len];
    for(int i = 0; i < len; i++) {
      String s = scan.next();
      char[] arr = s.toCharArray();
      for(int j = 0; j < len; j++) {
        a[i][j] = arr[j];
      }
    }
    int index1 = 0;
    int index2 = 0;
    for(int i = 0; i < len; i++) {
      String s = scan.next();
      char[] arr = s.toCharArray();
      for(int j = 0; j < len; j++) {
        b[i][j] = arr[j];
      }
    }
    for(int i = 0; i < len; i++) {
      for(int j = 0; j < len; j++) {
        if(a[i][j] != b[i][j]) {
          index1 = i;
          index2 = j;
        }
      }
    }
    System.out.println(index1 + 1 + " " + (index2 + 1));
    
    scan.close();
  }
  

  // @Test
  // void addition() {
  //     assertEquals(2, 1 + 1);
  // }
}
