import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        short a = in.nextShort();
       short b = in.nextShort();
       if(Math.abs(a%10-b%10)==1||Math.abs(a%10-b%10)==9 )System.out.println("Yes");
       else System.out.println("No");
        }
    }