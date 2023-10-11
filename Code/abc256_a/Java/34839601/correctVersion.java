import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        int N=Integer.parseInt(scan.next());
        int ans=2;
        for(int i=1;i<N;i++){
            ans*=2;
        }
        if(N==0){
            ans=1;
        }
        System.out.println(ans);
    }
}