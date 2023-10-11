import java.util.*;
 
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);  
        double x = sc.nextLong();
        double k = sc.nextLong();
	    double result = 0;	
      
      for(int i=0;i<k;i++){
         double j = Math.pow(10, i+1);
		 if(result == 0){
           result =  (Math.round(x/j))*j;
         }else{
           result =  (Math.round(result/j))*j;
         }
      }
      System.out.println((int)result);
	}
}