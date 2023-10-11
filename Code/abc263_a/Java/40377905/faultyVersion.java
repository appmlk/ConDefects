import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Solution: 1m
 * Coding: 10m
 * Time: 11m
 * 
 * 这里可以不用考虑扩展性，数据很小，怎么快怎么写。
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new FileReader("atcoder_abc/input.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> lst = new ArrayList<>();

        for(int i = 0;i < 5;i++){
            lst.add(Integer.parseInt(st.nextToken()));
        }

        if((lst.get(0) == lst.get(2) && lst.get(3) == lst.get(4)) || (lst.get(0) == lst.get(1) && lst.get(2) == lst.get(4))){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        // HashMap<Integer, Integer> map = new HashMap<>();

        // for(int i = 0;i < 5;i++){
        //     int cur = Integer.parseInt(st.nextToken());
        //     if(!map.containsKey(cur)){
        //         map.put(cur, 0);
        //     }
        //     map.put(cur, map.get(cur) + 1);
        // }

        // if(map.size() != 2) {
        //     System.out.println("No");
        // } else {
        //     for(int i: map.values()){
        //         if(i > 3){
        //             System.out.println("No");
        //             return;
        //         }
        //     }
        //     System.out.println("Yes");
        // }
        br.close();
    }
}