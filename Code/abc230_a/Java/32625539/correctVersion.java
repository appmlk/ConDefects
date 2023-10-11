import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N > 41){
            N ++;
        }
        System.out.println("AGC0" + String.format("%02d", N));
    }
}

