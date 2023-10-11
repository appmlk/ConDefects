
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
            if(a*2==b || (a*2)+1==b ){
                System.out.println("Yes");
            }else {
                System.out.println("No");
            }

    }
}