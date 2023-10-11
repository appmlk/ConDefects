import java.util.*;
import java.io.*;
import java.math.*;
public class Main{
	public static void solve(){
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		int b=input.nextInt();
		int c=input.nextInt();
		if(((a/2)+(c/2))==b){
			System.out.println("Yes");
		}
		else{
			System.out.println("No");
		}
	}
	    public static void input_output() {
        try {
            System.setIn(new FileInputStream("input.txt"));
            System.setOut(new PrintStream("output.txt"));
        } catch (Exception e) {
            //System.out.println("Error");
        }
    }
	public static void main(String[]args){
		if (System.getProperty("ONLINE_JUDGE") == null) {
            input_output();
            solve();
            } 
            else {
                solve();
            }
	}
}