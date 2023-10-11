
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int Q = sc.nextInt();
        ArrayList<Long> A = new ArrayList<Long>(N);
        ArrayList<Long> partialSumA = new ArrayList<Long>(N);
        Long X;
        int idx;
        long ans;
        for(int i=0;i<N;i++){
            A.add(Long.parseLong(sc.next()));
        }
        Collections.sort(A);
        partialSumA.add(A.get(0));
        for(int i=1; i<N; i++){
            partialSumA.add(partialSumA.get(i-1) + A.get(i));
        }
        // System.out.println(A.toString());
        // System.out.println(partialSumA.toString());
        for(int j=0;j<Q;j++){
            X = Long.parseLong(sc.next());
            idx = Collections.binarySearch(A, X);

            idx = (idx >= 0) ? idx : ~idx -1;
			
            if(idx == -1){
                ans = partialSumA.get(N-1)-X*N;
            }
            else if(idx == N){
                ans = X*N - partialSumA.get(N-1);
            }
            else{
                ans = (partialSumA.get(N-1) - partialSumA.get(idx)) - X*(N-idx-1) + ( X*(idx+1) - partialSumA.get(idx));
            }
            System.out.println(ans);
        }

    }
}
