import java.util.*;

public class Main {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        int x = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) =='a') {
                x++;
            }else break;
        }   
        int y = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(s.length()-i-1) =='a') {
                y++;
            }else break;
        }
        if(x > y) {
            System.out.println("No");
            return ;
        }
        
        for(int i = x; i < (s.length()-y)/2; i++){
            // System.out.println(i);
            if(s.charAt(i) != s.charAt(x +s.length()-y-1-i)){
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
        
    }
}

/*
oj t -c "java Main.java" -d ./test/
acc submit Main.java -- -l 4005

[A-Z][1-9][0-9]{6}[A-z] --> S1000000A, A21234556Z
*/