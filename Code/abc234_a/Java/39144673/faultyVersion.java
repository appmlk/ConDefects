import java.util.Scanner;
public class Main {
    public static int F(int X) {
        return X^2+2*X+3;
    }
    public static void main(String[] arg){
        Scanner scanner =new Scanner(System.in);
        int T = scanner.nextInt();
        int H = F(F(F(T)+T)+F(F(T)));
    	System.out.println(H);
    }
}