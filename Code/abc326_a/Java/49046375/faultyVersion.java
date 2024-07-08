import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int Y = sc.nextInt();

        if(-3 <= X-Y && X-Y <= 2){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }

    }
}