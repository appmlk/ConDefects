import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int K = -1;
//        int Q = -1;

        int R1 = -1;
        int R2 = -1;

        int B1 = -1;
        int B2 = -1;

        // int N1 = -1;
        // int N2 = -1;

        for (int i = 0; i < 8; i++) {
            char ich = input.charAt(i);
            if (ich == 'K') {
                K = i;
            // } else if (ich == 'Q') {
            //     Q = i;
            } else if (ich == 'R' && R1 == -1) {
                R1 = i;
            } else if (ich == 'R') {
                R2 = i;
            } else if (ich == 'B' && B1 == -1) {
                B1 = i;
            } else if (ich == 'B') {
                B2 = i;
            // } else if (ich == 'N' && N1 == -1) {
            //     N1 = i;
            // } else if (ich == 'N') {
            //     N2 = i;
            }
        }


        // Q1
        int res = 0;
        if (B1 != -1 && B2 != -1 && (B1 == 0 || (B2%B1)%2 != 0)) {
            res += 1;
        }

        // Q2
        if (R1 < K && K < R2) {
            res += 1;
        }


        System.out.println(res == 2 ? "Yes" : "No");
    }
}