import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        int x;
        for(int i = 0; i<N; i++){
            x = Integer.parseInt(sc.next());
            if(x % 2 == 0){
                even.add(x);
            }
            else{
                odd.add(x);
            }
        }
        Collections.sort(odd);
        Collections.sort(even);
        int max = -1;
        if(odd.size() > 1 && even.size() > 1){
            max = Math.max(odd.get(odd.size() - 1) + odd.get(odd.size() -2), even.get(even.size()-1) + even.get(even.size() -2));
        }
        else if(odd.size() > 1 && even.size() <= 1){
            max = odd.get(odd.size() -1) + odd.get(odd.size() - 2);
        }
        else if(odd.size() <= 1 && even.size() > 1){
            max = even.get(even.size() -1) + even.get(even.size() -2);
        }
        else{
        }
        System.out.println(max);
    }
}
