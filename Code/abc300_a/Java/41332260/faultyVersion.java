public final class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int N = scanner.nextInt();
        int A = scanner.nextInt();
        int B = scanner.nextInt();
        int[] C = new int[N];
        for(int i = 0; i < N; i++) {
            C[i] = scanner.nextInt();
        }

        int sum = A + B;
        
        for(int i = 0; i < N; i++) {
            if (C[i] == sum) {
                System.out.println(i);
                break;
            }
        }
    }
}