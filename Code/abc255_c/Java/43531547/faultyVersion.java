import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        long X=sc.nextLong();
        long A=sc.nextLong();
        long D=sc.nextLong();
        long N=sc.nextLong();
        long fina=A+N*D;
        if(D==0){
            System.out.println(Math.abs(X-A));
        }else if(fina>=A){
            if(X<=fina && X>=A){
                long n=(X-A)/D;
                long n1=n+1;
                long min=Math.min(Math.abs(X-A-(n)*D), Math.abs(X-A-(n1)*D));
                System.out.println(min);
            }else if(X>fina){
                System.out.println(Math.abs(X-fina));
            }else if(X<A){
                System.out.println(Math.abs(A-X));
            }
        }else if(fina<A){
            if(X>=fina && X<=A){
                long n=(X-A)/D;
                long n1=n+1;
                long min=Math.min(Math.abs(X-A-(n)*D), Math.abs(X-A-(n1)*D));
                System.out.println(min);
            }else if(X<fina){
                System.out.println(Math.abs(fina-X));
            }else if(X>A){
                System.out.println(Math.abs(X-A));
            }
        }
/**         for(long i=A;i<=A+N*D;i=i+D){
            a++;
            if(min>Math.abs(X-i)){
                min1=min;
                min=Math.abs(X-i);
            }
            if(a==N){
                break;
            }
            System.out.println(min);
            if(min<Math.abs(X-i)){
                break;
            }

        }
        System.out.println(min);**/
    }
}