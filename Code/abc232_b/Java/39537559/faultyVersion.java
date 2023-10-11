// temprate
import java.util.*;
public class Main{
    public static void main(String[] args){
        var in = new Scanner(System.in);
        // ~~~

        var kind = new HashSet<Integer>();
        char[] ch = in.next().toCharArray();
        char[] model = in.next().toCharArray();

        for ( int i = 0 ; ch.length > i ; i++ ){
            kind.add( model[ i ] - ch[ i ] ) ;
        }

        String ans = "No";
        if ( kind.size() == 1 ){
            ans = "Yes";
        }

        System.out.println(ans);

    }
}