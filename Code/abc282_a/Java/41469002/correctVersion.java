import java.util.Scanner;
public class Main {
	public static void main(String[] args) { 
	    Scanner scanner=new Scanner(System.in);
	    int k=scanner.nextInt();
	    for(int i=0;i<k;i++){
	        System.out.print((char)('A'+i));
	    }
	}
}