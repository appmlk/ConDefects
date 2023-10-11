import java.util.*;
 public class Main {
    public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
   Map<String,Integer> mymap=new HashMap<>();
   mymap.put("tourist",3858);
   mymap.put("ksun48",3679);
   mymap.put("Benq",3658);
   mymap.put("Um_nik",3648);
   mymap.put("apiad",3638);
   mymap.put("Stonefeang",3630);
   mymap.put("ecnerwala",3613);
   mymap.put("mnbvmar",3555);
   mymap.put("ecnerwala",3516);
   mymap.put("semiexp",3481);
          
  String input=sc.next();
  
  System.out.println(mymap.get(input));
     
   
   
   
    }
}