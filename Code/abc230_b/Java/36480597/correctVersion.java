import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        String s=scan.next();
        String answer="Yes";

        for(int i=0;i<=s.length()-2;i++){
            if(s.charAt(i)=='o'){
                if(i<(s.length()-1)){
                    if(s.charAt(i+1)=='x'){
                        if((i+1)<(s.length()-1)) {
                            if(s.charAt(i+2)=='x')continue;
                            else answer="No";
                        }
                    }
                    else
                        answer="No";
                }
            }
        }

        int sum=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='x')
                sum++;
            if(s.charAt(i)=='o')
                sum=0;
        }
        if(sum>=3)
            answer="No";
        System.out.println(answer);
    }
}
