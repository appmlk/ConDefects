import java.util.Scanner;
   public class Main{
       public static void main(String []args) {
           Scanner sc = new Scanner(System.in);
         String s = sc.next();
         for (int i = 0;i < s.length();i++) {
           if (s.charAt(i) == '0')
             System.out.print('1');
           else if (s.charAt(i) == '1') 
             System.out.print('0');
         }
         
       }
   }