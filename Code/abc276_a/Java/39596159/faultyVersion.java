import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int output = -1;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == 'a')output = i;
        }
        System.out.println(output);
        sc.close();
    }
}