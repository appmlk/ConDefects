import java.util.*;
class Main {
    
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    String s=scanner.next();
	    String[]arr=s.split("");
	    int n=s.length()-1;
	    int i=0;
	    
	    while(i<n){
	        if(arr[i].equals("a") && arr[n].equals("a")){
	            i++;
	            n--;
	        } else if(arr[n].equals("a")){
	            n--;
	        } else if(arr[i].equals(arr[n])){
	            i++;
	            n--;
	        } else break;
	    }
	    if(i>=n){
	        System.out.println("Yes");
	    } else{
	        System.out.println("No");
	    }
	}
}