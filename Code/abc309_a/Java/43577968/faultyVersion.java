import java.util.*;

public class Main {
    public static void main(String[] args ) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            int A= sc.nextInt();
            int B= sc.nextInt();
            if(A==B-1 ||A==B+1||A==B-3 ||A==B+3){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
    }
}