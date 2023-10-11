// temprate
import java.util.*;
import static java.lang.Math.*;
public class Main{
    public static void main(String[] args){
        var in = new Scanner(System.in);
        // ~~~

        int n = in.nextInt();
        int ans = 4 * n - 2 ;
        var A = new ArrayList<String>();
        for ( int i = 0 ; 4 * n - 1 > i ; i++ ){
            A.add( in.next() );
        }

        Collections.sort(A);

        for ( int i = 0 ; 4 * ( n - 1) - 1 > i ; i += 4){
            if ( !(A.get( i ).equals( A.get( i + 3 ) )) ){
                ans = i + 3;
                break;
            }
        }

        System.out.println( A.get( ans ) );

    }
}