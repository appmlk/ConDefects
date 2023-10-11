import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n =scan.nextInt();
        int a = scan.nextInt();
        if(Math.abs(a%10-n%10)<=1 || Math.abs(a-n)<=1) System.out.println("Yes");
        else System.out.println("No");
    }
}
