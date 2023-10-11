import java.util.*;


public class Main {

    static int N = 200010;
    static int [][] left = new int [N][3];
    static int [][] right = new int [N][3];
    static int [] a = new int [N];
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       for (int i = 0; i < n ; ++i) {
           a[i] = sc.nextInt();
       }
       String s = sc.next();
       for (int i = 0; i < n ; ++i) {
          if (i > 0) {
              for (int j = 0; j <= 2 ; ++j) {
                  left[i][j] = left[i - 1][j];
              }
          }
          if (s.charAt(i) == 'M') {
              left[i][a[i]]++;
          }
       }
       
       for (int i = n - 1; i >= 0; --i) {
           if (i < n - 1) {
               for (int j = 0; j <= 2 ; ++j) {
                   right[i][j] = right[i + 1][j];
               }
           }
           if (s.charAt(i) == 'X') {
               right[i][a[i]]++;
           }
       }
       
       long ret = 0;
       for (int i = 0; i < n ; ++i) {
           if (s.charAt(i) == 'E') {
               for (int j = 0; j <= 2 ; ++j) {
                   for (int k = 0; k <= 2 ; ++k) {
                       int mex = get(j, k, a[i]);
                       ret += 1L * mex * left[i][j] * right[i][k];
                   }
               }
           }
       }
       System.out.println(ret);
    }
    
    static int get (int a, int b, int c){
        boolean [] s = new boolean[4];
        s[a] = s[b] = s[c] = true;
        for (int i = 0; i <= 3 ; ++i) {
            if (!s[i]) return i;
        }
        return 0;
    }
}