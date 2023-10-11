import java.util.Scanner;

public class Main {

    public static void main( String args[]) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        int[] days = new int[N];
        int[] ans = new int[N];
        for ( int i = 0; i < N; i++) {
            days[i] = scn.nextInt();
        }

        for ( int j = 0; j < N - 1; j++) {
            if ( days[j] > days[j + 1]) {
                ans[j] = ans[j] == 1 ? 0 : 1;
                ans[j + 1] = 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for ( int k = 0; k < N; k++) {
            sb.append( ans[k] + " ");
        }
        sb.deleteCharAt( sb.length() - 1);
        System.out.println( sb.toString());
    }
}
