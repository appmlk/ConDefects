import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        int end = 0;
        int db = 0;
        int a = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i<N; i++){
            a = Integer.parseInt(sc.next());
            if(set.contains(a)){
                db++;
                continue;
            }
            set.add(a);
        }
        set.add(0);
        while(db > 1){
            if(!(set.contains(end + 1))){
                db -= 2;
                set.add(end + 1);
            }
            set.remove(end);
            end++;
        }
        if(db == 1)set.add(1000000001);
        while(set.size() > 2){
            if(!(set.contains(end + 1))){
                set.pollLast();
                set.pollLast();
                set.add(end + 1);
            }
            set.remove(end);
            end++;
        }
        if(set.contains(end + 1)){
            end++;
        }
        System.out.println(end);
    }
}
