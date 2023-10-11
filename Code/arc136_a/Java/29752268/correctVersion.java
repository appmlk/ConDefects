import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        char now;
        if(n > 1){
            now = s.charAt(1);
        }else{
            now = s.charAt(0);
        }
        char before = s.charAt(0);
        for(int i=1;i<n;i++){            
            //System.out.println(i);
            //System.out.println("before:"+before);
            //System.out.println("now:"+now);
            if(before == 'B'){
                if(now == 'A'){
                    sb.replace(i-1, i+1, "AB");
                    before = 'B';
                    if(i < sb.length()-1){
                        now = sb.charAt(i+1);
                    }
                }else if(now == 'B'){
                    sb.replace(i-1, i+1, "A");
                    before = 'A';
                    if(i < sb.length()){
                        now = sb.charAt(i);
                        i--;
                    }
                    
                }else{
                    before = 'C';
                    if(i < sb.length()-1){
                        now = sb.charAt(i+1);
                    }
                }
            }else{
                //System.out.println("im else now");
                before = now;
                if(i < sb.length()-1){
                    now = sb.charAt(i+1);
                }
            }
            //System.out.println("now:"+now);
            //System.out.println(sb.toString());
            if(i > sb.length() - 2){
                break;
            }
        }
        System.out.println(sb.toString());

    }
}