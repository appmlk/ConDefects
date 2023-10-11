import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        sc.close();

        Queue<String> queue = new LinkedList<>();
        Map<String,Integer> map = new HashMap<>();
        queue.add(S);
        map.put(S,0);
        while(!queue.isEmpty()){
            String str = queue.poll();
            //System.out.println(str);
            int cur = map.get(str);
            if(str.equals("atcoder")){
                System.out.println(cur);
                return;
            }
            for(int j = 0; j < str.length()-1; j++){
                String children = change(str, j, j+1);
                if(!map.containsKey(children)){
                    map.put(children, cur+1);
                    queue.add(children);
                }
            }
        }
    }

    public static String change(String str, int a, int b){
        char[] c = str.toCharArray();
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
        str = String.valueOf(c);
        return str;
    }
}