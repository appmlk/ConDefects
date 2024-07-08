import java.util.Scanner;
public class Main{
  public static void main(String[] args){
    var sc = new Scanner(System.in);
    String word = sc.nextLine();
    int[] some = new int[26];
    for(int x = 0;x < word.length();x++){
      some[word.charAt(x) - 'a']++;
    }
    long ans = (long)word.length() * (word.length() - 1) / 2;
    boolean check = false;
    for(int n:some){
      if(n>=2){
        ans -= (long)n * (n - 1) / 2;
        check = true;
      }
    }
    if(check)
    ans++;
    System.out.println(ans);
  }
}