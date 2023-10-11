
import java.util.Scanner;

class Main{
    public static void main(String[] args) {

    String s = "atcoder";
    Scanner sc = new Scanner(System.in);
    int l = sc.nextInt()-1;
    int r = sc.nextInt();
     String ans = s.substring(l,r);
        System.out.println(ans);

    }
}