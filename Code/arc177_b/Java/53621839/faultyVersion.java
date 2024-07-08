// 60  150 400 500

//700 250 160

import java.util.*;

public class Main {
	public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        int state[] = new int[n];
        String s = scanner.next();
        int maxIndex=0;
        for (int i = 0; i < n; i++) {
            state[i] = s.charAt(i) == '0' ? 0 : 1;
            if(state[i]==1) {
              maxIndex = i;
            }
        }
        System.out.println("maxIndex:"+maxIndex);
        
        String result = "";
        int[] ms = new int[n];
        for(int i=0;i<=maxIndex ;i++) {
          result+="A";
          ms[i] = 1;
        }
        
        for(int i=maxIndex;i>=0 ;i--) {
          if(state[i] == 0 && ms[i] == 1) {
            for(int j=i;j>=0;j--) {
              result+="B";
              ms[j]=0;
            }
          } else if(state[i] == 1 && ms[i] == 0) {
            for(int j=i;j>=0;j--) {
              result+="A";
              ms[j]=1;
            }
          }
        }
        
        System.out.println(result.length());
        System.out.println(result);
	}
}