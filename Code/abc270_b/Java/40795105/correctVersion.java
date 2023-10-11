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

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        if(x > 0 && y > 0){
            if(x < y){
                System.out.println(x);
            } else if(z < y) {
                if(z < 0){
                    System.out.println(2 * Math.abs(z) + Math.abs(x));
                } else {
                    System.out.println(Math.abs(x));
                }
            } else {
                System.out.println(-1);
            }
        } else if (x < 0 && y < 0){
            if(x > y){
                System.out.println(Math.abs(x));
            } else if (z > y) {
                if(z < 0){
                    System.out.println(Math.abs(x));
                } else {
                    System.out.println(2 * Math.abs(z) + Math.abs(x));
                } 
            } else {
                System.out.println(-1);
            }
        } else if (x * y < 0){
            System.out.println(Math.abs(x));
        } else {
            System.out.println(-1);
        }
        br.close();
    }
}
