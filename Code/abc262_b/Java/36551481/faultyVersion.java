import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] U = new int[M];
        int[] V = new int[M];
        for(int i = 0; i < M; i++){
            U[i] = sc.nextInt();
            V[i] = sc.nextInt();
        }

        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            List<Integer> connected = new ArrayList<>();
            list.add(connected);
        }

        for(int i = 0; i < M; i++){
            list.get(U[i]-1).add(V[i]);
            list.get(V[i]-1).add(U[i]);
        }

        for(int i = 0; i < N; i++){
            Collections.sort(list.get(i)); 
        }

        System.out.println(list);

        int ans = 0;
        for(int a = 0; a < N; a++){
            List<Integer> connecta = list.get(a);
            for(int b : connecta){
                if(b > a){
                    List<Integer> connectb = list.get(b-1);
                    //System.out.println(b);
                    for(int c : connectb){
                        if(c > b && list.get(c-1).contains(a+1)){
                            //System.out.println(a + " " + b + " " + c);
                            ans++;
                        }
                    }
                }
            }
        }
        
        System.out.println(ans);

        sc.close();
    }
}