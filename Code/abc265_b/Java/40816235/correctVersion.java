import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
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

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n - 1];
        for(int i = 0;i < n - 1;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0;i < m;i++){
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }
        br.close();
        for(int i = 0;i < n - 1;i++){
            if(map.containsKey(i)){
                t += map.get(i);
            }
            t -= arr[i];
            if(t <= 0){
                System.out.println("No");
                return;
            } 
            // System.out.println(t);
        }

        System.out.println("Yes");

    }
}
