import java.util.*;
import java.io.*;
import java.math.*;
public class Main{
	public static void solve(){
		Scanner input=new Scanner(System.in);
		long num=input.nextLong();
		long ans1=-(long)Math.pow(2,31);
		long ans2=(long)Math.pow(2,31);
		if(num>=ans1&&num<=ans2-1){
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