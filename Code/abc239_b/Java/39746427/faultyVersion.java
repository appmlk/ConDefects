// temprate
import java.util.*;
import static java.lang.Math.*;
public class Main{
    public static void main(String[] args){
        var in = new Scanner(System.in);
        // ~~~
	
	long x = in.nextLong();

	long ans = x / 10;

	if ( x < 0 ){
		ans = ans - 1;
	}

	System.out.println( ans );

    }
}
