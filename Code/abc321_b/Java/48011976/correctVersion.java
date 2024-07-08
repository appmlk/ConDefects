import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Scanner sc = new Scanner(System.in);
    //A
    static boolean a(String s){
        if (s.length()==1) return true;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i)-'0'>=s.charAt(i-1)-'0')return false;
        }
        return true;
    }
    static int b(int[] a,int tar){
        Arrays.sort(a);
        int sum = Arrays.stream(a).sum();
        if(sum-a[a.length-1]>=tar) return 0;
        if (a[0]!=a[a.length-1]) sum -= a[0]+a[a.length-1];
        else sum -= a[0];
        int ans = tar - sum;
        if (ans<a[0]||ans>a[a.length-1]) return -1;
        if (ans>100) return -1;
        if (ans<0) return 0;
        return ans;
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        int tar = sc.nextInt();
        int[] a = new int[n-1];
        for (int i = 0; i < n-1; i++) a[i] = sc.nextInt();
        System.out.println(b(a,tar));
    }
}
