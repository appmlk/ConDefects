import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

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

        int x = Integer.parseInt(br.readLine());

        if(x % 100 == 0){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        br.close();
    }
}