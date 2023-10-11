import java.util.Scanner;
public class Main {
	public static void main(String[] args) { 
	    Scanner scanner=new Scanner(System.in);
	    String s=scanner.nextLine();
	    for(int i=s.length()-1;i>=0;i--){
	        if(s.charAt(i)=='a'){
	            System.out.println(i+1);
	            break;
	        } else if(!(s.charAt(i)=='a')&&i==0){
	            System.out.println(-1);
	        }
	    }
	}
}