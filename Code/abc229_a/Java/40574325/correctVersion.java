import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/*
 * Solution: 1m
 * Coding: 4m
 * Time: 5m
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("atcoder_abc/input.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        String s = br.readLine();
        s += br.readLine();
        for(char c: s.toCharArray()){
            if(c == '#'){
                count++;
            }
        }
        if(count == 2 && ((s.charAt(1) == '#' && s.charAt(2) == '#') ||(s.charAt(0) == '#' && s.charAt(3) == '#') )){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        br.close();
    }
}