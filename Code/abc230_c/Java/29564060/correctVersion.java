import java.util.Scanner;

public class Main {
    public static  void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
       long n,A,B,p,q,r,s;
       n=scanner.nextLong();
       A=scanner.nextLong();
       B=scanner.nextLong();
       p=scanner.nextLong();
       q=scanner.nextLong();
       r=scanner.nextLong();
       s=scanner.nextLong();

       long lowerbound1=Math.max(1-A,1-B);
       long upperbound1=Math.min(n-A,n-B);
       long lowerbound2=Math.max(1-A,B-n);
       long upperbound2=Math.min(n-A,B-1);
       for(long i=p;i<=q;i++){
           for(long j=r;j<=s;j++){
                if(j-i==B-A||j+i==A+B){
                    if(j-i==B-A){
                        if((i-A)>=lowerbound1&&(i-A)<=upperbound1) {
                            System.out.print("#");
                        }
                        else
                            System.out.print(".");
                    }
                    else if(j+i==A+B){
                        if((i-A)>=lowerbound2&&(i-A)<=upperbound2) {
                            System.out.print("#");
                        }
                        else
                           System.out.print(".");
                    }
                }
                else{
                    System.out.print(".");
                }
           }
           System.out.println();
       }
    }
}