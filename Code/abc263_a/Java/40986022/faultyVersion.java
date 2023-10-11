import java.util.Scanner;
import java.util.Arrays;
  public class Main{
     public static void main(String []args) {
         Scanner sc = new Scanner(System.in);
       int a[] = new int[5];
       for (int i = 0;i < a.length - 1;i++) {
            a[i] = sc.nextInt(); 
       }
       Arrays.sort(a);
       int a1 = a[0];
       int count1 = 1;
       int a2 = 0;
       int count2 = 0;
       
       for (int i = 1;i < a.length;i++) {
           if (a[i] == a1) {
            count1++; 
           }
         else if (a[i] != a1&& a2 == 0) {
          a2 = a[i];
           count2++;
         }
         else if (a[i] == a2) {
          count2++; 
         }
       }
       if (count1 == 3&& count2 == 2||count1 == 2&&count2==3) {
        System.out.println("Yes"); 
       }
       else {
           System.out.println("No"); 
       }
     }
  }



