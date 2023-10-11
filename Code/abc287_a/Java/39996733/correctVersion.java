import java.util.*;
import java.lang.*;

class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = (n + 1) / 2;
        for (int i = 0; i < n; i++) if(sc.next().equals("Against")) count--;
        if (count > 0) System.out.println("Yes");
        else System.out.println("No");
    }
}
