import java.util.*;

public class Main{

  public static void main (String args[]){

    Scanner sc=new Scanner(System.in);

    int t=sc.nextInt();

    while(t-->0){

      long n=sc.nextLong();

      long k=sc.nextLong();

long sum=0;

while(n-->0){

      

      sum+=n%3;

  n/=3;

  }

      if(sum<=k){

        if((k-sum)%2==0){

          System. out.println("Yes");

          }

        else{

          System. out.println("No");

          }}

        else{

          System.out.println("No");}

        

}

}}

        