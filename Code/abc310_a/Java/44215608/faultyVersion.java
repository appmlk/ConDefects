import java.util.*;
 
class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = sc.nextInt();
    int q = sc.nextInt();
    
    int minMenu = sc.nextInt();

    for(int i=4; i<args.length; i++){
    	int a = sc.nextInt();
	      if(minMenu > a){
	        minMenu = a;
	      }
	    }
	    
	    int pay = q + minMenu;
	    int result = p;
	    
	    if(pay < p){
	      result = pay;
	    }
	    
	    System.out.println(result);
  }
}