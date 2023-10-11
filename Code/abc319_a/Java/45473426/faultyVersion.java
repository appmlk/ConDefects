import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
  public static void main(String[] args) throws Exception {
    Map<String,Integer> mp = new HashMap<>();
    
    mp.put("tourist",3858);
    mp.put("ksun48",3679);
    mp.put("Benq",3658);
    mp.put("Um_nik",3848);
    mp.put("apiad",3638);
    mp.put("Stonefeang",3630);
    mp.put("ecnerwala",3613);
    mp.put("mnbvmar",3555);
    mp.put("newbiedmy",3516);
    mp.put("semiexp",3481);
    
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String name = br.readLine();
    
    System.out.println(mp.get(name));
    
  }
}