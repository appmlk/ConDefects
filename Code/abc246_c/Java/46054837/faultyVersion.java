import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int a = sc.nextInt();
        int b =sc.nextInt();
        int c =sc.nextInt();
        int arr[] =new int[a];
        int t=0;
        long sum=0;
        for(int i = 0; i<a;i++){
            arr[i]=
            sc.nextInt();
            sum+=(long)arr[i];
        }
        // System.out.println("r "+sum);
        for (int i = 0; i < arr.length; i++) {
            System.out.println("b1 "+b);
            if(b==0)break;
            t=Math.min(b,arr[i]/c);
            arr[i]-=t*c;
            sum-=(long)t*c;
            b-=t;
            // System.out.println("r "+sum);
        }
        // System.out.println(arr[0]+" "+arr[a-1]);
        Arrays.sort(arr);
        // System.out.println(arr[0]+" "+arr[a-1]);
        for (int i = arr.length-1; i >=0 ; i--) {
            // System.out.println("b1 "+b);
            if(b==0||arr[i]==0)break;
            sum-=(long)arr[i];
            arr[i]=0;
            b--;
        }
        System.out.println(sum);}
}        