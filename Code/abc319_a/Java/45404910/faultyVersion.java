import java.util.*;

class Main{
  public static void main(String [] args){
    HashMap <String,Integer> top = new HashMap<String,Integer>();
    top.put("tourist",3858);
    top.put("ksun48",3679);
    top.put("Benq",3658);
    top.put("Um_nik",3648);
    top.put("apiad",3638);
    top.put("Stonefeang",3630);
    top.put("ecnerwala",3613);
    top.put("mnbvmar",3555);
    top.put("newbiedmy",3516);
    top.put("semiexp",3581);
    
    Scanner scr = new Scanner(System.in);
    String a = scr.next();
    System.out.println(top.get(a));
  }
}