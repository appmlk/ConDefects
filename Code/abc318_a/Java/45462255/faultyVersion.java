import java.util.*;

public class Main {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(); // 1からn日目まで
    int m = sc.nextInt(); // 今日からm日目に満月
    int p = sc.nextInt(); // p日ごとに満月
    int watch = 0;
    
    n = n - m;
    
    while(n > 0){
      n = n - p;
      watch++;
    }
    
    System.out.println(watch);
  }
}