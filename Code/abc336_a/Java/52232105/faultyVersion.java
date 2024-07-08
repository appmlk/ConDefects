import java.util.*;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String strL = "L";
    StringBuilder strO = new StringBuilder("o");
    String strN = "n";
    String strG = "g";

    // 整数の入力
    int inputNum = 2024;
    // スペース区切りの整数の入力
    for (int i = 1; i < inputNum; i++) {
      strO.append("o");
    }

    // 出力
    System.out.println(strL + strO + strN + strG);
  }
}