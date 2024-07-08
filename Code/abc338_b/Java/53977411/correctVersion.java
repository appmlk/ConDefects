

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author YC
 * @version 1.0
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PII[] a = new PII[27];
    public static void main(String[] args) throws IOException {
        String s =  br.readLine();
        for(int i = 1; i <= 26 ; i ++) {
            a[i] = new PII(i, 0);
        }

        for(int i = 0 ; i < s.length(); i ++) {
            int index = s.charAt(i) - 'a' + 1;
            a[index] = new PII(index, a[index].cnt + 1);
        }

        Arrays.sort(a,1, 27);
        System.out.println((char) (a[1].id + 'a' - 1));
    }

    public static class PII implements Comparable<PII> {
        int id;
        int cnt;

        public PII(int id, int cnt) {
            this.id = id;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(PII o) {
            if(this.cnt == o.cnt) return Integer.compare(this.id,o.id);
            return Integer.compare(o.cnt,this.cnt);
        }
    }
}
