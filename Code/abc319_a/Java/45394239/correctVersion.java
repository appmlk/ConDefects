import java.util.*;
public class Main{
  public static void main(String[] args)
  {
    Scanner sc=new Scanner(System.in);
    Map<String,Integer> user=new HashMap<String,Integer>();
    user.put("tourist",3858);
    user.put("ksun48",3679);
    user.put("Benq",3658);
    user.put("Um_nik",3648);
    user.put("apiad",3638);
    user.put("Stonefeang",3630);
    user.put("ecnerwala",3613);
    user.put("mnbvmar",3555);
    user.put("newbiedmy",3516);
    user.put("semiexp",3481);
        String s=sc.nextLine();
        if(user.containsKey(s)){
          int r=user.get(s);
          System.out.println(r);
        }
        else
        {
          System.out.println("0");
        }
  }
}