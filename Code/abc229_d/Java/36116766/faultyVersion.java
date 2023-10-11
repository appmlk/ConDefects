import java.util.*;
 
public class Main{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    String s = scan.nextLine(); int cnt = scan.nextInt();
    int start = 0; int end = 0; int maxCnt = 0;
    
    while(end > s.length()){
    	if(s.charAt(end) == 'X'){
        	end++;
          maxCnt = Math.max(maxCnt, end - start);
          continue;
        }
      else if(cnt > 0){
        end++;
        cnt--;
        maxCnt =  Math.max(maxCnt, end - start);
        continue;
      }
      else{
      if(s.charAt(start) == 'X'){
      start++;
      }
        else{
        cnt++;
          start++;
        }
      }
    }
    System.out.println(maxCnt);
  }
}