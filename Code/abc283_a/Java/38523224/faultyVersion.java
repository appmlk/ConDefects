import java.util.*;

public class Main{
    public static void power1(int a, int b) {
        int pow=1;
        for(int i=1;i<=b;i++){
            pow*=a;      
        }
        System.out.println(pow);       
    }

    public static void power2(int a, int b){
        System.out.println(Math.pow(a,b));
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a;
        a = sc.nextInt();   
        int b;
        b = sc.nextInt();  

        power2(a,b);

        sc.close();
    }
}
