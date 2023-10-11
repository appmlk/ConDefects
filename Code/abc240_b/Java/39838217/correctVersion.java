import java.util.Scanner;
import java.util.Arrays;
  public class Main{
      public static void main(String []args) {
          Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[] = new int[n];
        int count = 1;
        for (int i = 0;i < n;i++)  {
          a[i] = sc.nextInt();
      }
        Arrays.sort(a);
        int compare = a[0];
        for (int i = 1;i < n;i++) {
            if (compare != a[i]) {
             count++; 
            }
          compare = a[i];
        }
        System.out.println(count);
      }
  
  
  
  
  }