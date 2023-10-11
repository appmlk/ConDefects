import java.util.Scanner;
   public class Main{
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
          int a[] = new int[5]; 
      int n = a.length;
      for (int i = 0;i < n;i++) {
          a[i] = sc.nextInt(); 
      }
      int count = 0;
      for (int i = 0;i < n;i++) {
            for (int j = i + 1;j < n;j++) {
                 if (a[i] == a[j]){
                     count++;
                   break;
                 }
            }
      }System.out.println(count);
     }
    }
      