import java.util.*;
public class Main {
    public static void main(String[] args){        
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        if(str.substring(str.length()-2).equals("er")) System.out.println("er");
        else System.out.println("ist");
    }
}