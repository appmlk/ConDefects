import java.util.*;
import java.lang.*;
import java.io.*;
class Main{
    public static int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b, a % b);
    }
	public static void main (String[] args) throws java.lang.Exception{
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int g = Math.abs(arr[1] - arr[0]);
        for(int i = 2; i < n; i++){
            g = gcd(g, Math.abs(arr[i] - arr[i - 1]));
        }
        if(g == 1){
            System.out.println(2);
        }
        else{
            System.out.println(1);
        }
	}
}