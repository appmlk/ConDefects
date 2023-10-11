import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

      int n=sc.nextInt();
      int ns=n%5;
      
      if(ns==0 || ns==1 || ns==2){
        System.out.println(n-ns);
      }else
        System.out.println(n+(5-ns));
      
	}	
}



