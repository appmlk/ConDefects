import java.util.*;

public class Main {
	public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        int sLength = S.length();
        for (int i=0;i<sLength;i++) {
            char ch = S.charAt(i);
            int cnt = 0;
            for (int j=0;j<sLength;j++){
                char ch2 = S.charAt(j);
                if (i != j && ch != ch2){
                    cnt++;
                    if (cnt == 2) {
                        System.out.println(ch);
                        return;
                    } 
                }
            }
        }
	}
}
