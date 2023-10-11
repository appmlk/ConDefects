import java.util.*;
public class Main{
  public static void main(String [] args){
    Map<String,Integer> tik=new HashMap<>();
    tik.put("tourist",3858);
    tik.put("ksun48",3679);
    tik.put("Benq",3658);
    tik.put("Um_nik",3648);
    tik.put("apiad",3638);
    tik.put("Stonefeang",3630);
    tik.put("ecnerwala", 3613);
    tik.put("mnbvmar", 3555);
    tik.put("newbiedmy", 3516);
    tik.put("semiexp", 3481);
    Scanner kl=new Scanner(System.in);
    String ma=kl.nextLine().trim();
    
    if(tik.containsKey(ma)){
      int pt=tik.get(ma);
      System.out.println(pt);
    }
  }
}