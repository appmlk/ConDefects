import java.util.Scanner;
   public class Main {
       public static void main(String []args) {
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int m = sc.nextInt();
         int count = 0;
         String s[] = new String[n];
         String t[] = new String[m];
         for (int i = 0;i < n;i++) {
            s[i] = sc.next(); 
         }
         for (int i = 0;i < m;i++) {
             t[i] = sc.next(); 
         }
         for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
               if (s[i].endsWith(t[j])) {
                   count++; 
                 continue;
               }
            }
         }
                   System.out.println(count);
       }
   }






