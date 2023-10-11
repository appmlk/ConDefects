import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }

        System.out.println(solve(n, p));
    }

    static int solve(int n, int[] p) {
        int max = 0;
        for(int i=1; i<n; i++) {
            if(p[i] > max) {
                max = p[i];
            }
        }
        int result = max - p[0] + 1;
        if(result < 0) {
            return 0;
        } else {
            return result;
        }
    }
}
