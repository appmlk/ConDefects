import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        String s=input.next();
        String t=input.next();
        boolean prefix = true;
        for(int i=0;i<n;++i){
            if(s.charAt(i)!=t.charAt(i)){
                prefix=false;
                break;
            }
        }
        boolean suffix = true;
        for(int i=0;i<n;++i){
            if(s.charAt(i)!=t.charAt(m-n+i)){
                suffix=false;
                break;
            }
        }
        System.out.println(prefix&&suffix?0:(prefix?1:(suffix?2:3)));
        input.close();
    }
}