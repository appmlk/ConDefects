import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int Y = sc.nextInt();

        if(-2 <= X-Y && X-Y <= 3){
            System.out.print("Yes");
        }else{
            System.out.print("No");
        }

    }
}