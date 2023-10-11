import java.util.Scanner;

class Main{
	public static void main(String[] args){
    
    Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();
      String a = Integer.toHexString(N);
      a = a.toUpperCase();
      if(a.length() == 1){
      a= "0"+a;
      }
      System.out.println(a);
      
    
    }

}