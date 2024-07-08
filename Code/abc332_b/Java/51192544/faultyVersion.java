import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int a = 0;
        int b = 0;
        int g = sc.nextInt();
        int m = sc.nextInt();

        for(int i = 0; i < k; i++) {
            if (a == g) {
                a = 0;
            }else if (b == 0) {
                b = m;
            }else{
                if (g - a < b) {
                    a += (g - a);
                    b -= (g - a);
                }else{
                    a += b;
                    b = 0;
                }
            }
        }
        System.out.print(a);
        System.out.print(" ");
        System.out.print(b);
    }
}
