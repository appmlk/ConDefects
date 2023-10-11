import java.util.*;

public class Main{
  
  public static void main(String[] args){
    Map<String,Integer> mp=new HashMap<>();
    Scanner sc =new Scanner(System.in);
    mp.put("tourist",mp.getOrDefault("tourist",3858));
    mp.put("ksun48", mp.getOrDefault("ksnu48",3679));
    mp.put("Benq",mp.getOrDefault("Benq",3658));
    mp.put("Um_nik", mp.getOrDefault("Um_nik",3648));
    mp.put("apiad" ,mp.getOrDefault("apiad",3638));
    mp.put("Stonefeang" ,mp.getOrDefault("Stonefeang",3630));
    mp.put("ecnerwala" ,mp.getOrDefault("ecnerwala",3613));
    mp.put("mnbvmar",mp.getOrDefault("mnbvmar",3555));
    mp.put("newbiedmy",mp.getOrDefault("newbiedmy",3516));
    mp.put("semiexp",mp.getOrDefault("semiexp",3481));
    
    String s =sc.nextLine();
    System.out.println(mp.get(s));
    
  }
}