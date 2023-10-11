import java.util.*;
public class Main {
    public static void main (String[]args) {
        Scanner scan = new Scanner(System.in);
        int a=scan.nextInt();
        int b=scan.nextInt();
        scan.close();

        if((b-a)==1){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}