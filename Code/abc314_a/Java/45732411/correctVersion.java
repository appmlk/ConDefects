import java.util.*;

public class Main{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String pi = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";

        System.out.println(pi.substring(0, n+2));
        
    }

}