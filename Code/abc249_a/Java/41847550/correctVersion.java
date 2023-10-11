import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();
        int x = sc.nextInt();
        sc.close();

        int takahashi = 0 , aoki = 0;

        for (int t = 0; t < x; t++){
            if (t % (a + c) < a) {
                takahashi += b;
            }
            if (t % (d + f) < d) {
                aoki += e;
            }
        }

        if (takahashi > aoki){
            System.out.println("Takahashi");
        }else if (takahashi < aoki){
            System.out.println("Aoki");
        }else{
            System.out.println("Draw");
        }

        
    }
}