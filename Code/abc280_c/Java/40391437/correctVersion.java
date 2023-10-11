import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        String T = sc.nextLine();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != T.charAt(i)){
                System.out.println(i + 1);
                sc.close();
                System.exit(0);
            }
        }

        System.out.println(T.length());
        sc.close();
    }
    
}