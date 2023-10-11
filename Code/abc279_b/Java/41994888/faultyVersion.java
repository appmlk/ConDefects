import java.util.Scanner;
 
public class Main{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		String t = scan.next();
        if(s.matches(".*t.*$")){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}