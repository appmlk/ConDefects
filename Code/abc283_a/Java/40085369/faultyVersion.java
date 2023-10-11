import java.util.*;
public class Main{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        double A = in.nextInt();
        double B = in.nextInt();

        double answer = Math.pow(A, B);
        
        System.out.println(answer);
    }
}