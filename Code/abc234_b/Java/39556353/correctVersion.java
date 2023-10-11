// temprate
import java.util.*;
import static java.lang.Math.*;
public class Main{
    public static void main(String[] args){
        var in = new Scanner(System.in);
        // ~~~

        int N = in.nextInt();
        var point = new ArrayList<List<Integer>>();

        for ( int i = 0 ; N > i ; i++ ){
            point.add( Arrays.asList( in.nextInt() , in.nextInt() ) );
        }

        double ans = 0;

        for ( int i = 0 ; N > i ; i++ ){
            for ( int j = N - 1 ; i < j ; j-- ){
                ans = max( ans ,   sqrt(  pow(   point.get( j ).get( 0 ) - point.get( i ).get( 0 )   , 2 ) + pow(   point.get( j ).get( 1 ) - point.get( i ).get( 1 )   , 2 )   )   );
            }
        }

        System.out.print(ans);

    }
}