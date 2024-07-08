import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int testNum = sc.nextInt();
    int passingPoint = sc.nextInt();
    int passingPointNum = 0;

    for (int i = 0; i < testNum; i++) {
      int score = sc.nextInt();
      if(passingPoint < score) passingPointNum++;
    }

    // 出力
    System.out.println(passingPointNum);
  }
}