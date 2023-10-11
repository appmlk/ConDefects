import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if(s.equals("Monday")){
            System.out.println(5);
        } else if(s.equals("Tuesday")){
            System.out.println(4);
        } else if(s.equals("Wednesday")){
            System.out.println(3);
        } else if(s.equals("Thursday")){
            System.out.println(2);
        } else if(s.equals("Friday")){
            System.out.println(1);
        }
        sc.close();
    }
}