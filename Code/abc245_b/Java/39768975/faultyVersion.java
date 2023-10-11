// temprate
import java.util.*;
import static java.lang.Math.*;
public class Main{
    public static void main(String[] args){
        var in = new Scanner(System.in);
        // ~~~
	
	int N = in.nextInt();

	var A = new HashSet<Integer>();

	for ( int i = 0 ; N > i ; i++ ){
		A.add( in.nextInt() );
	}
	
	int ans = 0;

	for ( int i = 0 ; N > i ; i++ ){
		if ( !( A.contains( i ) ) ){
			ans = i;
			break;
		}
	}

	System.out.println( ans );

    }
}