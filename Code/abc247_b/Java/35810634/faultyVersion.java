import java.io.*;
import java.util.*;

public class Main {
	 
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
 
		int t = sc.nextInt();
		String[] sar = new String[t*2];
		
		for(int i=0; i<sar.length; i+=2) {
			sar[i] = sc.next();
			sar[i+1] = sc.next();
		}
		for(int i=0; i<sar.length; i++) {
			if(sar[i].equals("")) continue;
			String temp = sar[i];
			for(int j=i+2; j<sar.length; j++) {
				if(temp.equals(sar[j])) {
					sar[j] = "";
					sar[i] = "";
				}
			}
		}
		String answer = "Yes";
		for(int i=0; i<sar.length; i+=2) {
			if(sar[i].equals("") && sar[i+1].equals("")) {
              answer="no";
              break;
            }
		}

		System.out.println(answer);
 	}// 메인 끝
	
}