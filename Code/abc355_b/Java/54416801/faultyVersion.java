import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();

        for (int i = 0; i < N; i++)
            A.add(sc.nextInt());

        for (int i = 0; i < M; i++)
            B.add(sc.nextInt());

        C.addAll(A);
        C.addAll(B);
        C.sort(Comparator.naturalOrder());

        for (int i = 0; i < C.size() - 1; i++) {
            boolean b1 = A.contains(C.get(i));
            boolean b2 = A.contains(C.get(i) + 1);

            if (b1 && b2) {
                System.out.println("Yes");
                return;
            }
        }

        System.out.println("No");
    }
}