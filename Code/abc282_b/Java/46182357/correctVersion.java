import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 参加者の人数
    int num = Integer.parseInt(scanner.next());
    // 問題数
    int problem = Integer.parseInt(scanner.next());
    String[] score = new String[num];
    
    for (int i = 0; i < num; i++) {
      score[i] = scanner.next();
    }
    
    int count = 0;
    for (int i = 0; i < num - 1; i++) {
      for (int j = 0; j < num; j++) {
        boolean flg = true;
        if (i >= j) {
          continue;
        }
        String[] numbers1 = score[i].split("");
        String[] numbers2 = score[j].split("");
        
        for (int k = 0; k < problem; k++) {
          if (numbers1[k].equals("o")) {
            continue;
          } else {
            if (numbers2[k].equals("o")) {
              continue;
            } else {
              flg = false;
              break;
            }
          }
        }
        if (flg) {
          count++;
        }
      }
    }
    System.out.println(count);
  }
}