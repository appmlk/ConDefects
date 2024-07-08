import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(buf.readLine());
        PriorityQueue<String>pq = new PriorityQueue<>(Collections.reverseOrder());
        long total = 0;
        for(int i = 0 ; i < t ; i++) {
            String[] s = buf.readLine().trim().split(" ");
            String name = s[0];
            int rating = Integer.parseInt(s[1]);
            total += rating;
            pq.add(name);
        }
        total %= t;
        while(total--> 0){
            pq.poll();
        }
        System.out.println(pq.peek());
    }
}
