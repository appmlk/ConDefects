import java.util.*;
public class Main {
	public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
		String t = sc.next();
		String[] stArr = new String[n];
		ArrayList<Integer> ansArr = new ArrayList();
		for(int i = 0; i<n; i++){
		  // stArr[i] = sc.next();
		  String str = sc.next();
		  if(check(str, t))ansArr.add(i);
		}
		System.out.println(ansArr.size());
		for(int nuuu: ansArr)System.out.print(nuuu+1+" ");
		
	}
	
	public static boolean check(String str, String t){
	  if(str.equals(t))return true;
	  if(Math.abs(str.length()-t.length())>=2)return false;
	  int mis = 0;
	  if(str.length() > t.length()){
	    for(int i = 0, j = 0; i<t.length(); i++){
	      if(t.charAt(i)!=str.charAt(j)){
	        mis++;
	        i--;
	      }
	      if(mis>1)return false;
	      j++;
	    }
	    
	    return true;
	    
	  }else if(str.length() < t.length()){
	    for(int i = 0, j = 0; i<str.length(); i++){
	     if(str.charAt(i)!=t.charAt(j)){
	        mis++;
	        i--;
	      }
	      if(mis>1)return false;
	      j++;
	    }
	    return true;
	  }
	  for(int i = 0; i<str.length(); i++){
	    if(str.charAt(i)!=t.charAt(i))mis++;
	    if(mis>1)break;
	  }
	  
	  return mis<2;
	}
	
	
}