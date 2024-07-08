import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String[] S_N = S.split("C");
        int num = Integer.parseInt(S_N[1]);
        
        if (num > 1 && num < 350 && num != 316) {
          System.out.println("Yes");
        } else {
          System.out.println("No");
        }
    }
}