import java.util.*;
 
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        boolean flag = false;
        if(s.length() == 8){
            if(s.charAt(0) >= 'A' && s.charAt(7) <= 'Z'){
                if(s.substring(1,7).matches("[+-]?\\d*(\\.\\d+)?")){
                    int tmp = Integer.valueOf(s.substring(1,7));
                    if(tmp >= 100000 && tmp <= 999999) flag = true;
                }
            }
        }
        if(flag) System.out.println("Yes");
        else System.out.println("No");
        sc.close();
    }
}