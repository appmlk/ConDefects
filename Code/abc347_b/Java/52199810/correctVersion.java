
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    String S = s.next();
    List<String> Slist = new ArrayList<String>();

    int count = 0;
    for(int i = 0;i < S.length();i++){
      for(int j = i+1;j <= S.length();j++){
        String newS = S.substring(i,j);
        if(!Slist.contains(newS)){
          Slist.add(newS);
          count++;

        }
      }
      
    }
    
    System.out.println(count);

    s.close();
  }
}