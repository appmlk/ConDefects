import java.util.*;
import java.io.*;
public class Main  {
    static final Random random=new Random();

    public static void main(String args[])  {
        Scanner ans = new Scanner(System.in);
        int n = ans.nextInt(); int k = ans.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i <n; i++) {
            arr[i] = ans.nextInt();
        }
        int kco = k; int sum = 0; int ans1 =0;
        for (int i = 0; i < n; i++) {
            kco = kco -arr[i];
            if(kco<0){
                ans1 = ans1+1;
                kco = k-arr[i];
                if(kco==0 || i ==n-1){
                    ans1 = ans1+1;
                    kco = k;
                }
            }
            else if (kco==0 || i ==n-1) {
                kco = k;
                ans1 = ans1+1;
            }
        }
        System.out.println(ans1);
    }

    static void ruffleSort(int[] a) {
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n), temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }
}















