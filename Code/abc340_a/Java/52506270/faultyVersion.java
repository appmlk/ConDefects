import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner An = new Scanner(System.in);
        int A = An.nextInt();
        int B = An.nextInt();
        int D = An.nextInt();
        for(int C = A; C < B; C += D){
            System.out.println(C);
        }
    }
}