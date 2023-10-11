import java.util.*;

public class Main {
    public static void main (String[]args) {
        Scanner scan=new Scanner(System.in);
        int c= scan.nextInt();
        int s=c%100;
        if(s==0&&c>0){
        System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}