import java.util.*;

public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(2*n);
        List<Integer> a = new ArrayList<>();
        for (; n >= 4; n -= 4) a.add(4);
        if (n > 0) a.add(0, n);
        for (int x : a) {
            System.out.print(x+" ");
        }
        System.out.println();

    }

}
