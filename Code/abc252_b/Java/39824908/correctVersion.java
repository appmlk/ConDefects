
import java.util.*;
 
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        int max = 0;
        for(int i=0;i<n;i++){
            a[i] = sc.nextInt();
            max = Math.max(a[i], max);
        }

        Set<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++){
            if(a[i] == max){
                set.add(i);
            }
        }

        int[] b = new int[k];
        boolean flag = false;
        for(int i=0;i<k;i++){
            b[i] = sc.nextInt()-1;
            if(set.contains(b[i])) flag = true;
        }
        if(flag) System.out.println("Yes");
        else System.out.println("No");

        sc.close();
    }
}