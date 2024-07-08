import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc;
    static PrintWriter out;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        out = new PrintWriter(System.out);
        new Main().solve();
        out.flush();
    }

    public void solve() {
        int n = sc.nextInt();
        char[] s = sc.next().toCharArray();
        if(s[0] == 'B') {
            s = new String(s)
                    .replaceAll("A", "C")
                    .replaceAll("B", "A")
                    .replaceAll("C", "B").toCharArray();
        }
        String t = new String(s);
        int cur = 0;
        int res = 1;
        int[] l = new int[n];

//        String prev = "";
//        for(int i=0; i<=n-1; i++) {
//            String str = t.substring(1, n-i) + "A" + t.substring(n-i, n);
//            out.println(str);
//            StringBuilder sb = new StringBuilder();
//            int h= 0;
//            int[] ll = new int[n];
//            for(int j=0; j<n; j++) {
//                if(str.charAt(j) == 'A') {
//                    h++;
//                } else {
//                    h--;
//                }
//                sb.append(h>0 ? '+' : h <0 ? '-' : '0');
//                ll[j] = h;
//            }
//            out.println(sb.toString());
//            if(!sb.toString().equals(prev)) {
//                out.println("DIFF!!!" + ll[n-1-i-1]  );
//            }
//            prev = sb.toString();
//        }



        for(int i=1; i<n; i++) {
            if(s[i] == 'A') {
                cur++;
            } else {
                cur--;
            }
            l[i] = cur;
            if(s[i] == 'B' && (l[i]==-1 || l[i]==0) || s[i]=='A' && (l[i]==-1)) {
                res++;
            }

        }


        out.println(res);
    }

}
