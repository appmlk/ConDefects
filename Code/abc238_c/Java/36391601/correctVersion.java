import java.math.BigInteger;
import java.util.*;
public class Main {
	public static void main (String [] args)
    {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
		long lower = 1;
		long answer = 0;
		long upper = 1;

		for(int i = 1; i <= 18 && lower < n; i++)
		{
			if(i == 18)
			{
				upper = n;
			}
			else
			{
				upper = lower * 10;
				upper = Math.min(n, upper - 1);
			}
			
			long numofnums = upper - lower + 1;
			answer += (((numofnums + 1)% 998244353)* (numofnums % 998244353) /2);
			answer %= 998244353;
			lower *= 10;
			
		}
		answer %= 998244353;
        double epsilon = 0.00001;
        double min = 10000000;
        for (int i = 0; i <= 18; i++) {
            min = Math.min(min, Math.abs(n - (long)Math.pow(10, i)));
        }

		if(n == 1)
			System.out.print(1);
		else if(min < epsilon) 
			System.out.print(answer + 1);
		else
			System.out.print(answer);
    }
}
      
 