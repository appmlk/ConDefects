import java.util.*;

public class Main {
    public static void main(String[] args ) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            int A= sc.nextInt();
            int B= sc.nextInt();
            if(B-A==1&&(B!=4&&B!=7)){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }


                
        }
    }
}