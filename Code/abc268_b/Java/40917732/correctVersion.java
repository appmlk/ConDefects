import java.util.Scanner;
   public class Main{
       public static void main(String []args) {
           Scanner sc = new Scanner(System.in);
         String s = sc.next();
         String t = sc.next();
         if (t.startsWith(s)) {
           System.out.println("Yes");
         }
         else {
           System.out.println("No"); 
         }
       }
   }