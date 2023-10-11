import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[] ) throws Exception {
        //Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //int n = sc.nextInt();
        int n = Integer.parseInt(br.readLine());
      	StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        Arrays.setAll(a, i->Integer.parseInt(st.nextToken()));

        Map<Integer, Integer> freq = new TreeMap<>();
        for(int x:a)
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        
        long ans = n * (n - 1L) / 2L * (n - 2L) / 3L, copy = 0L;
        int l = freq.size();
        for(int x:freq.values()) {
            copy += x * (x - 1L) / 2L * (n - x);
            if(x >= 3)
                copy += x * (x - 1L) / 2L * (x - 2L) / 3L;
        }

        // System.out.println(freq + " " + ans + " " + copy + " " + l);
        System.out.println(ans - copy);
    }
}
