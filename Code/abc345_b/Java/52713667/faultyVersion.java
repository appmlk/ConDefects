import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Your code here!
        Scanner scan=new Scanner(System.in);
        long input = scan.nextLong();
        if(input + 9 < 0 && (input + 9) != 0){
            long result = (long)((input + 9) / 10) - 1;
            System.out.println(result);
        }else{
            long result = (long)((input + 9) / 10);
            System.out.println(result);
        }
        //System.out.println(result);
    }
}
