import java.util.Scanner;
   public class Main {
      public static void main(String []args) {
          Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        int count = 0;
        int x = 0;
        int y = 0;
        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == 'R') {
               count++;
              count = count % 4;
            }
          if (s.charAt(i) == 'S'&& count % 4 == 0) {
               x++; 
          }
          else if (s.charAt(i) == 'S'&& count % 4 == 1) {
              y--; 
          }
          else if (s.charAt(i) == 'S' && count % 4 == 2) {
              x--; 
          }
          else if (s.charAt(i) == 'S' && count % 4 == 3) {
               y++; 
          }
        }
        System.out.println(x+" "+y);
      }
   }






