import java.io.*;
import java.util.*;

public class Main {
	 
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] car1 = new int[n];
		int[] car2 = new int[n]; // 범위 10억
		
		for(int i=0; i<n; i++) {
			car1[i] = Integer.parseInt(sc.next());
		}
		for(int i=0; i<n; i++) {
			car2[i] = Integer.parseInt(sc.next());
		}
		int cnt1 = 0;
		int cnt2 = 0;
		for(int i=0; i<n; i++) {
			if(car1[i]==car2[i]) cnt1++;
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(car1[i]==car2[j]) {
					cnt2++;
					break;
				}
			}
		}
		
		System.out.println(cnt1);
		System.out.println(cnt2-cnt1);
		
 	}// 메인 끝
	
}