

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        String ans = "Yes";
        int[] d = new int[s.length()];
        for(int i=0;i<s.length();i++){
            d[i]=(int)s.charAt(i);
        }

        for (int j=0;j<s.length()-1 && s.length()!=1;j++){
            if (d[j] <= d[j+1]) {
                ans = "No";
                break;
        }
        }

        System.out.println(ans);
        sc.close();

        

    }

    
}
