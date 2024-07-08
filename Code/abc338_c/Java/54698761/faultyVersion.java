import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int MAXMAKE = 1000000; 
        int N = Integer.parseInt(sc.next());
        int[] Qi = new int[N];
        int[] Ai = new int[N];
        int[] Bi = new int[N];
        int ans = Integer.MIN_VALUE;
        int canY = Integer.MAX_VALUE;
        ArrayList<Integer> canMakeX = new ArrayList<>();
        boolean canMake;

        for (int i = 0; i < N; i++) {
            Qi[i]=Integer.parseInt(sc.next());
        }
        for (int i = 0; i < N; i++) {
            Ai[i]=Integer.parseInt(sc.next());
        }
        for (int i = 0; i < N; i++) {
            Bi[i]=Integer.parseInt(sc.next());      
        }


        for (int x = 0; x < MAXMAKE; x++) {
            canMake = true;
            for (int i = 0; i < N; i++) {
                if(Qi[i]-Ai[i]*x<0){
                    canMake = false;
                    break;
                }
            }            
            if(canMake==true) canMakeX.add(x);
            else break;
        }

        for (int x:canMakeX) {
            canY = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                if (Bi[i]==0) continue;
                canY = Math.min(canY,(Qi[i] - Ai[i]*x)/Bi[i]);
            }
            ans = Math.max(ans, x+canY);
        }
        System.out.println(ans);
    }
}
