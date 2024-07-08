import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];
        TreeSet<Integer> hoge = new TreeSet<>();
        for(int i=0; i<N; i++){
            int a = sc.nextInt();
            A[i] = a;
            hoge.add(a);
        }
        Arrays.sort(A);
        
        HashMap<Integer, Integer> present = new HashMap<>();
        int count = 1;
        int tmp = A[0];
        present.put(A[0], count);
        for(int i=1; i<N; i++){
            if(tmp==A[i]){
                count++;
                present.put(A[i], count);
                tmp = A[i];
            }else{
                count = 1;
                present.put(A[i], count);
                tmp = A[i];
            }
        }
        
        Integer[] check = hoge.toArray(new Integer[0]);
        int l = 0;
        int r = 1;
        int sum = present.get(check[l]);
        int max = 0;
        
        //lを固定し、そこからM個の範囲内でとれるだけプレゼントを取得　→　lを1増やす(rはできる限り増やす)　...のループ
        while(l<check.length-1){
            while(r<check.length && check[r]-check[l]<=M-1){
                sum += present.get(check[r]);
                r++;
            }
            //System.out.println(check[l]+" から "+check[r-1]+" のプレゼント合計は "+sum);
            max = Math.max(sum, max);
            sum -= present.get(check[l]);
            l++;
        }
        
        System.out.println(max);
    }
}