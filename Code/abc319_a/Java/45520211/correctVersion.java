import java.util.*;

public class Main {
  public static void main(String[] args){
  
    Scanner scanner = new Scanner(System.in);
  	// 整数の入力
  	String S = scanner.next();
  
    // みんなの成績
    Map<String, Integer> map = new HashMap<>();
    
    // 手打ち
    map.put("tourist", 3858);
    map.put("ksun48", 3679);
    map.put("Benq", 3658);
    map.put("Um_nik", 3648);
    map.put("apiad", 3638);
    map.put("Stonefeang", 3630);
    map.put("ecnerwala", 3613);
    map.put("mnbvmar", 3555);
    map.put("newbiedmy", 3516);
    map.put("semiexp", 3481);
    
    // Mapからデータを取得する
    System.out.println(map.get(S));
  }
}