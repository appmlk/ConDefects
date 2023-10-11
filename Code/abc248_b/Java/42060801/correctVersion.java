import java.io.*;
import java.util.*;
import java.math.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(),b = sc.nextLong(), k = sc.nextLong(),ans=0;
        while(a<b){
            a*=k;
            ans++;
        }
        System.out.println(ans);
    }
}
