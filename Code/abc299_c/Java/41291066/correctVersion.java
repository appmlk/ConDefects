import java.util.Scanner;

import static java.lang.Math.max;

public class Main {public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    String s=sc.next();
    int a=s.indexOf('-');
    int res=-1;
    for(int i=0;i<s.length();i++){
        if(s.charAt(i)=='o'){
            int j=i;
            int ans=0;
            while(j<s.length()&&s.charAt(j)=='o') {
                ans++;
                j++;
            }
            if(j!=s.length())res=max(res,ans);
            if(i!=0)res=max(res,ans);
            i=j-1;
        }
    }

    System.out.println(res);
}

}
