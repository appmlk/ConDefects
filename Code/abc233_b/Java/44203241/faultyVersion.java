import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        // 入力を受け取るためのオブジェクト
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String[] line_1 = line1.split(" ");
        int L = Integer.parseInt(line_1[0]);
        int R = Integer.parseInt(line_1[1]);
        System.out.println(L);
        System.out.println(R);
        String S = sc.nextLine();
        StringBuilder new_S = new StringBuilder(S);
        for(int i = 0; i<S.length();i++){
          if ((i>=L)&(i<=R)){
            //char t = S.charAt(i);
            new_S.setCharAt(i-1,S.charAt(R-i+L-1));
            new_S.setCharAt(R-i+L-1,S.charAt(i-1));
          }
        }
        System.out.println(new_S);
    }
}