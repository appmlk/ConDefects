import java.util.*;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    ArrayDeque<Character> S = new ArrayDeque<>();
    for(char c:sc.next().toCharArray())
      S.add(c);
    ArrayDeque<Character> T = new ArrayDeque<>();
    for(char c:sc.next().toCharArray())
      T.add(c);
    char bef = '-';
    boolean flag = false;
    while(S.size()>0&&T.size()>0){
      char s = S.pollFirst();
      char t = T.pollFirst();
      if(s!=t){
        if(bef==t&&flag){
          S.addFirst(s);
        }
        else{
          System.out.println("No");
          return;
        }
      }
      else{
        flag = s==bef;
        bef = s;
      }
    }
    System.out.println(S.size()+T.size()==0?"Yes":"No");
  }
}
