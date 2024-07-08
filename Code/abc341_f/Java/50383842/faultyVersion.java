import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Main{
  public static void main(String[] args){
    Scanner sc_i = new Scanner(System.in);
    int n = Integer.parseInt(sc_i.next());
    int m = Integer.parseInt(sc_i.next());
    int[][] uv = new int[m][2];
    for(int i_i = 0; i_i < m; i_i++){for(int j_i = 0; j_i < 2; j_i++){uv[i_i][j_i] = Integer.parseInt(sc_i.next());};};
    int[] w = new int[n]; for(int i_i = 0; i_i < n; i_i++){w[i_i] = Integer.parseInt(sc_i.next());};
    int[] a = new int[n]; for(int i_i = 0; i_i < n; i_i++){a[i_i] = Integer.parseInt(sc_i.next());};
    var edge = new ArrayList<ArrayList<Integer>>(n+10);
    for(int i = 0; i < n; i++){
      edge.add(new ArrayList<>());
    };
    for(int i = 0; i < m; i++){
      edge.get(uv[i][0]-1).add(uv[i][1]-1);
      edge.get(uv[i][1]-1).add(uv[i][0]-1);
    };
    var pq = new PriorityQueue<Integer>();
    var costMap = new HashMap<Integer, ArrayList<Integer>>();
    for(int i = 0; i < n; i++){
      if(! costMap.containsKey(w[i])){costMap.put(w[i], new ArrayList<>()); pq.add(w[i]);};
      costMap.get(w[i]).add(i);
    };
    int[] price = new int[n];
    while(! pq.isEmpty()){
      for(var vertex : costMap.get(pq.poll())){
        var map = new HashMap<Integer, Integer>();
        map.put(- w[vertex], 1);
        for(var trg : edge.get(vertex)){if(w[trg] <= w[vertex]){
          var newmap = new HashMap<Integer, Integer>();
          for(var en : map.entrySet()){
            int key = en.getKey(), value = en.getValue();
            newmap.put(key, newmap.containsKey(key) ? Math.max(value, newmap.get(key)) : value);
            if(key + w[trg] < 0){
              key += w[trg]; value += price[trg];
              newmap.put(key, newmap.containsKey(key) ? Math.max(value, newmap.get(key)) : value);
            };
          };
          map = newmap;
        };};
        int max = 0;
        for(var en : map.entrySet()){max = Math.max(max, en.getValue());}
        price[vertex] = max;
      };
    };
    long ans = 0;
    for(int i = 0; i < n; i++){
      ans += a[i] * price[i];
    };
    System.out.println(ans);
  }
}


