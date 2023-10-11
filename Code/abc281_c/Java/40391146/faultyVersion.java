import java.util.Scanner;

class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long T = sc.nextLong();
        int A[] = new int[N];
        int sum = 0;

        for (int i = 0; i < N; i++){
            A[i] = sc.nextInt();
            sum += A[i];
        }

        T %= sum;

        for (int i = 0; i < N; i++){
            if (T > A[i]){
                T -= A[i];
            } else{
                System.out.print(i + 1);
                System.out.print(" ");
                System.out.print(T);
                sc.close();
                System.exit(0);
            }
        }
    }
}