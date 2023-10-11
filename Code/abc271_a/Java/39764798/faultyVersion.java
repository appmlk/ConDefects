import java.util.*;

public class Main {

    static boolean next_permutation(String[] p) {
        for(int a = p.length - 2; a >= 0; --a) {
            if(p[a].compareTo(p[a+1]) < 0) {
                for(int b = p.length - 1;; --b) {
                    if(p[b].compareTo(p[a]) > 0) {
                        String t = p[a];
                        p[a] = p[b];
                        p[b] = t;
                        for(++a, b=p.length-1; a < b; ++a, --b) {
                            t = p[a];
                            p[a] = p[b];
                            p[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = Integer.toHexString(n);
        String zero = "O";
        if(s.length() == 1) {
            s = zero.concat(s);
        }
        System.out.println(s.toUpperCase());
    }
}