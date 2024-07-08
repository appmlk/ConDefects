import java.util.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int ans = 0;
    int max = 0;
    int secound = 0;
    int[] array = new int[N];
    boolean flg = true;
    ArrayList<Integer> list = new ArrayList<>();
    
    for(int i = 0; i < N; i++){
      array[i] = sc.nextInt();
      list.add(array[i]);
      if(flg){
        max = array[i];
        flg = false;
      }else if(max < array[i]){
        max = array[i];
      }
    }
    list.removeAll(Collections.singleton(max));
    
    for(int num : list){
      if(secound < num){
        secound = num;
      }
    }
    System.out.print(secound);
  }
}