import java.util.*;


public class Main {

    
    static int N = 300010;
    static int n, m,cnt;
    static long k;
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       n = sc.nextInt();
       m = sc.nextInt();
       k = sc.nextLong();
       String s = sc.next();
       s += s;
       int [] pre = new int [2 * n + 1];
       for (int i = 1; i <= 2 * n ; ++i) {
           char c = s.charAt(i - 1);
           pre[i] = pre[i - 1] + (c == 'x' ? 1 : 0);
       }
       long ret = k / pre[n] * n;
       int tmp = 0;
       m -= k / pre[n];
       k %= pre[n];
       for(int l = 0,r = 0; r <= 2 * n; ){
    		while(r <= 2 * n && pre[r] <= k + pre[l]) ++r;
    		l++;
    		if(m == 1)	tmp = Math.max(tmp,Math.min(r,n) - l);
    		else if(m > 1)	tmp = Math.max(tmp,r - l);
	   }
	   System.out.println(ret + tmp);
    }
  
}