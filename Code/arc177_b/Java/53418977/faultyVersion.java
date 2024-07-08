import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        int[] lumps = new int[N];
        String[] S = scn.next().split( "");
        for ( int i = 0; i < N; i++) {
            lumps[i] = Integer.parseInt( S[i]);
        }
        StringBuilder sb = new StringBuilder();
        int[] simulation = new int[N];
        for ( int j = N - 2; j >= 0; j--) {
            if ( lumps[j] == simulation[j]) {
                continue;
            }
            else if ( lumps[j] == 1) {
                for ( int k = 0; k <= j; k++) {
                    simulation[k] = 1;
                    sb.append( "A");
                }
            }
            else {
                for ( int k = 0; k <= j; k++) {
                    simulation[k] = 0;
                    sb.append( "B");
                }
            }
        }
        System.out.println( sb.length() + "\r" + sb);
    }
}