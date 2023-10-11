import java.util.Scanner;

public class Main {
    // int の 範囲 |2,147,483,647|
    private static final int FAILURE = -1;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int n = sc.nextInt();
        if (n < 2) {
            sc.close();
            System.out.println(FAILURE);
            return;
        }

        int d = sc.nextInt();
        int t1 = sc.nextInt();
        
        while(sc.hasNext()) {
            int t2 = sc.nextInt();
            if (t2 - t1 <= d) {
                sc.close();
                System.out.println(t2);
                return;
            }
            t1 = t2;
        }

        sc.close();
        System.out.println(FAILURE);
    }

}
