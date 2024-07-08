import java.util.*;
import java.math.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        long X = sc.nextLong();
        if(X > 0) {
            System.out.print(X / 10 + 1);
        } else {
            System.out.print(X / 10);
        } 
    } 
}