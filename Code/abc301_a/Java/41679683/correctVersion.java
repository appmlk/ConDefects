import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    String s = sc.next();
	    int t = 0;
	    int a = 0;
	    int i;
	    for(i = 0; i < n; i++) {
	    	if(s.charAt(i) == 'T') {
	    		t++;
	    	} else {
	    		a++;
	    	}
	    }
	    if(t > a) {
	    	System.out.println("T");
	    } else if(a > t) {
	    	System.out.println("A");
	    } else if(s.charAt(i-1) == 'A') {
	    	System.out.println("T");
	    } else {
	    	System.out.println("A");
	    }
	    sc.close();
	}
}