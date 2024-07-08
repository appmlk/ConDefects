import java.util.*;
public class Main{
    static String s,t,t1;
    static char c,d;
    static int n;
    static StringBuilder ans;
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        s=sc.next();
        char a[]=s.toCharArray();
        int q=sc.nextInt();
        char change[]=new char[26];
        for(int i=0;i<26;i++){
            change[i]=(char)(i+'a');
        }
        while(q-->0){
            t=sc.next();
            t1=sc.next();
            c=t.charAt(0);
            d=t1.charAt(0);
           for(int i=0;i<26;i++){
               if(c-'a'==i)change[i]=d;
           }
        }
        for(int i=0;i<n;i++){
            System.out.print(change[a[i]-'a']);
        }
    }


}