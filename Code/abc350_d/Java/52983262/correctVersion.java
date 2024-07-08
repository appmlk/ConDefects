import java.util.*;
 
public class Main {
    static Scanner s = new Scanner(System.in);
    static int n, m, p[], size[], a, b;
    public static void main(String[] args) {
       n = s.nextInt();
       m = s.nextInt();
       int k = m;
       p = new int[n + 1];
       size = new int[n + 1];
       for(int i = 1; i <= n; i++) {
    	   p[i] = i;
    	   size[i] = 1;
       }
       while(m -- >0) {
    	   a = s.nextInt();
    	   b = s.nextInt();
    	   if(find(a)!=find(b)) {
    		   size[find(b)] += size[find(a)];
    		   p[find(a)] = find(b);
    	   }
       }
       long res = 0;
       Set<Integer> set = new HashSet<>();
       for(int i = 1; i<=n; i++) {
    	   if(!set.contains(find(i))) {
    		   set.add(find(i));
    		   int num = size[find(i)];
    		   res += 1l*(num)*(num-1)/2;
    	   }
       }
       System.out.print(res - k);
    }
	private static int find(int x) {
		if(p[x] != x) p[x] = find(p[x]);
		return p[x];
	}
}