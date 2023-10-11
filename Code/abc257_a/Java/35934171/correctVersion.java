import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    
    int repeat = sc.nextInt();
    int target = sc.nextInt();
    
    char[] alph = new char[26];
    char c = 'A';
    for(int i = 0; i < 26; i++)
      alph[i] = c++;
    
    char[] output = new char[26*repeat];
    
    int num = 0;

    for(int j = 0; j < 26; j++)
      for(int k = 0; k < repeat; k++)
        output[num++] = alph[j];
    
    String out = new String(output);
        
    System.out.println(out.substring(target - 1, target));
  }
}