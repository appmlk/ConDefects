import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N+1];
        int same = 0;
        for(int i = 1; i <= N; i++){
            A[i] = sc.nextInt();
            if(A[i] == i){
                same++;
            }
        }

        sc.close();

        long ans = same*(same-1)/2;

        for(int i = 1; i <= N; i++){
            if(A[i] > i){
                int j = A[i];
                if(A[j] == i){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}