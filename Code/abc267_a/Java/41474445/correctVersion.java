import java.util.Scanner;
public class Main {
	public static void main(String[] args) { 
	    Scanner scanner=new Scanner(System.in);
	    String s=scanner.next();
	    if(s.equals("Monday")){
	        System.out.println(5);
	    } else if(s.equals("Tuesday")){
	        System.out.println(4);
	    } else if(s.equals("Wednesday")){
	        System.out.println(3);
	    } else if(s.equals("Thursday")){
	        System.out.println(2);
	    } else{
	        System.out.println(1);
	    }
	}
}