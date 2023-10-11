import java.util.Scanner;
import java.util.HashSet;
class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    HashSet<String> set = new HashSet<>();
    boolean flag = true;
    for(int i=0;i<N;i++){
      String S = sc.next();
      flag &= S.charAt(0)=='H'||S.charAt(0)=='D'||S.charAt(0)=='C'||S.charAt(0)=='S';
      flag &= ('2'<=S.charAt(1)&&S.charAt(1)<='9')||S.charAt(1)=='A'||S.charAt(1)=='T'||S.charAt(1)=='J'||S.charAt(1)=='Q'||S.charAt(1)=='K';
      set.add(S);
    }
    System.out.println(flag&&set.size()==N?"Yes":"No");
  }
}
