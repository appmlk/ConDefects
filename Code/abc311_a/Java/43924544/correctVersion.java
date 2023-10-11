import java.util.*;

class Main {
    static public void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.next());
        String S = scan.next();

        int counter = 0;
        Boolean flagA = false;
        Boolean flagB = false;
        Boolean flagC = false;

        for (int i=0; i < N; i++) {
            if (S.charAt(i) == 'A') {
                flagA = true;
                counter++;
            } else if (S.charAt(i) == 'B') {
                flagB = true;
                counter++;
            } else if (S.charAt(i) == 'C') {
                flagC = true;
                counter++;
            }

            if (flagA == true && flagB == true && flagC == true) {
                System.out.println(counter);
                break;
            }
        }

        scan.close();
    }
}