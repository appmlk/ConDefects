import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		System.out.println(solve(r,c));
	}

    public static String solve(int r, int c) {
    	int max = Math.max((8-r),(8-c));
        return max%2 == 1? "black":"white";
	}
}