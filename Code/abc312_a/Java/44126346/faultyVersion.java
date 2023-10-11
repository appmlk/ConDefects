import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 2023/7/31
 * A - Chord
 */
public class Main {

    public static void main( String[] args ) {

        // 入力値
        Scanner sc = new Scanner( System.in );
        List<String> list = Arrays.asList( "ACE", "BDF", "CEG", "DFA", "EGB", "FAC", "GB" );
        System.out.println( list.contains( sc.next() ) ? "Yes" : "No" );
        sc.close();
    }
}
