import java.util.*;
public class Main{
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
   String s = sc.next();
   
   for(int i=0 ;i<s.length(); i++){
     if(s.charAt(s.length()-(i+1))=='a'){
       System.out.println(s.length()-(i+1));
       return;
     }
   }
   System.out.println(-1);
   
 }
}