import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int a = n / 16;
        int b = n % 16;

        if(a >= 0){
            System.out.print(a);
        }
        else{
            henkan(a);
        }

        if(b < 10){
            System.out.println(b);  
        }
        else{
            henkan(b);
        }
    }
    public static void henkan(int num){
        if(num == 10){
            System.out.println("A");
        }
        else if(num == 11){
            System.out.print("B");
        }
        else if(num == 12){
            System.out.print("C");
        }
        else if(num == 13){
            System.out.print("D");
        }
        else if(num == 14){
            System.out.print("E");
        }
        else if(num == 15){
            System.out.print("F");
        }
    }
}