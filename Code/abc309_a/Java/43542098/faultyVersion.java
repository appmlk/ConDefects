import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        int a3 = a%3;
        int b3 = b%3;

        if(a/3 == b/3 &&(b3 - a3 == 1 || a3-b3==2)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }


    }
}