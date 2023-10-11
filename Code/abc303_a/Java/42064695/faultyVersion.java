import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String S = sc.nextLine();
        String T = sc.nextLine();
        boolean hantei = false;
        for(int i = 0; i < n; i++) {
          if(S.charAt(i) == T.charAt(i) || (S.charAt(i) == 'l' && T.charAt(i) == '1') || (S.charAt(i) == 'l' && T.charAt(i) == '1') || (S.charAt(i) == 'o' && T.charAt(i) == '0') || (S.charAt(i) == '0' && T.charAt(i) == 'o')) {
            continue;
          }
          else {
            hantei = true;
          }
        }
        if(hantei) System.out.println("No\n");
        else System.out.println("Yes\n");
                }
}