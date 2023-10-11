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

        HashMap<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        String[][] names = new String[n][2];

        for(int i = 0;i < n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String first = st.nextToken();
            String last = st.nextToken();
            names[i][0] = first;
            names[i][1] = last;
            if(!map.containsKey(first)){
                map.put(first, 0);
            }
            map.put(first, map.get(first) + 1);
            if(!map.containsKey(last)){
                map.put(last, 0);
            }
            map.put(last, map.get(last) + 1);
        }
        br.close();
        for(String[] ns: names){
            if(map.get(ns[0]) > 1 && map.get(ns[1]) > 1 ){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");


    }
}