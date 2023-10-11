import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main( String args[]) {
        Scanner scn = new Scanner( System.in);
        int N = scn.nextInt();
        Set<String> arraySet = new HashSet<>();
        for ( int i = 0; i < N; i++) {
            arraySet.add( scn.nextLine());
        }
        System.out.println( arraySet.size());
    }
}
