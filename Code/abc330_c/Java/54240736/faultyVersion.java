import java.util.*;

public class Main {
	public static void main(String[] args){
	    Scanner sc = new Scanner(System.in);
	    long D = sc.nextLong();
	    long k = (long)Math.sqrt(D);
	    long re = D;
	    for(long i =0;i<=k;i++) {
	    	long y = (long) Math.sqrt(D - i * i);
	    	long o = Math.abs(i * i + y * y - D);
	    	re = Math.min(re, o);
	    }
	    System.out.print(re);
	}
}
