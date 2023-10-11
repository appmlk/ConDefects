import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        long k=scan.nextLong();
        long x=scan.nextLong();
        long[] arr=new long[n];
        for(int i=0;i<arr.length;i++){
            arr[i]=scan.nextLong();
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i]>x){
                long p=Math.min(arr[i]/x, k);
                arr[i]=arr[i]-(p*x);
                k-=p;
            }
        }
        Arrays.sort(arr);
        long total=0;
        for(int i=0;i<n-k;i++){
            total+=arr[i];
        }
        System.out.println(total);
    }
}
