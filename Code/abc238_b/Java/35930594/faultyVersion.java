import java.io.*;
import java.util.*;

public class Main {
	 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<arr.length; i++) {
			arr[i] = arr[i] + arr[i-1];
			if(arr[i] > 360) arr[i] = arr[i]-360; 
		}
		Arrays.sort(arr);
		
		int max = 0;
		for(int i=0; i<arr.length-1; i++) {
			if(max < arr[i+1]-arr[i]) max = arr[i+1]-arr[i];
		}
		int first = arr[0];
		int last = 360 - arr[0];
		System.out.println(Math.max(max, Math.max(first, last)));
		
 	}// 메인 끝
	
}