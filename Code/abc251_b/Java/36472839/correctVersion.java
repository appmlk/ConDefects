import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    static int N,W;
    static int[] A;
    static TreeSet<Integer> set = new TreeSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = sc.nextInt();
        A = new int[N];
        for(int i=0; i<N; i++) {
            A[i] = sc.nextInt();
            if(A[i]<W+1) set.add(A[i]);
        }
        sc.close();

        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                if(A[i]+A[j]<W+1) set.add(A[i]+A[j]);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                for(int k=j+1; k<N; k++){
                    if(A[i]+A[j]+A[k]<W+1) set.add(A[i]+A[j]+A[k]);
                }
            }
        }
        System.out.println(set.size());
    }
}