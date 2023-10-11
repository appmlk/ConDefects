import java.util.*;
public class Main {
	public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      String a = sc.next();
      a = a.substring(a.length()-2);
      if(a.equals("er")){
        System.out.println(a);
      }else{
        System.out.println("ist");
      }
    }
}