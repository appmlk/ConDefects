import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class Main  {
    static final Random random=new Random();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String str = in.next();
        int ans =0; int ptr =0;
        for (int i = 0; i <=n-3; i++) {
            if(str.substring(i,i+3).equals("ABC") && ptr==0){
                ptr=1;
                ans=i;
            }
        }
        if (ptr==1){
            System.out.println(ans);
        }
        else {
            System.out.println(-1);
        }
    }
    static void ruffleSort(int[] a) {
        int n=a.length;
        for (int i=0; i<n; i++) {
            int oi=random.nextInt(n); int temp=a[oi];
            a[oi]=a[i]; a[i]=temp;
        }
        Arrays.sort(a);
    }
    static int gcd (int  a , int b){
        if(b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    public static int bin1(ArrayList<Integer> arr , int val) {
        int st = 0;
        int len = arr.size()-1;
        int end = len;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if (arr.get(mid) <=val) {
                st = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return st;
    }


}







