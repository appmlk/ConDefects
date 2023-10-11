import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s =scanner.next();
       if ((s.charAt(0) == s.charAt(1)) && (s.charAt(1) == s.charAt(2))){
       		System.out.println(-1);
       }
       else if  (s.charAt(0) == s.charAt(1)){
        	System.out.print(s.charAt(2));
        }
       else if (s.charAt(0) == s.charAt(2)){
       		System.out.print(s.charAt(1));
       }
      else if (s.charAt(1) == s.charAt(2)){
        System.out.println(s.charAt(0));
      }
      else{
      	System.out.println(s.charAt(0));
      }
}
}