import java.util.Scanner;

public class Main {

    public static void main( String[] args) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        int[] array = new int[N];
        for ( int i = 0; i < N; i++) {
            array[i] = scn.nextInt();
        }
        int[] difArray = new int[N - 1];
        for ( int j = 0; j < N - 1; j++) {
            difArray[j] = array[j + 1] - array[j];
        }
        int firstDif = difArray[0];
        for ( int dif : difArray) {
            while ( firstDif > 1) {
                if ( dif % firstDif == 0) {
                    break;
                }
                int tmp = dif % firstDif;
                dif = firstDif;
                firstDif = tmp;
            }
            if ( firstDif == 1) {
                System.out.println( 2);
                return;
            }
        }
        System.out.println( 1);
    }
}