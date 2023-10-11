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

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[2001];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0;i < n;i++){
            int cur = Integer.parseInt(st.nextToken());
            arr[cur] = 1;
        }
        br.close();
        for(int i = 0;i <= 2000;i++){
            if(arr[i] == 0){
                System.out.println(i);
                return;
            }
        }
        System.out.println(2001);

    }
}
