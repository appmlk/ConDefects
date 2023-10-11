import java.util.*;
class Main {
    
	public static void main(String[] args) { 
	    Scanner scanner = new Scanner(System.in);
	    int n=scanner.nextInt();
	    int m=scanner.nextInt();
	    int h=scanner.nextInt();
	    int k=scanner.nextInt();
	    String s=scanner.next();
	    
	    Set<String>item=new HashSet<>();
	    for(int i=0;i<m;i++){
	        item.add(scanner.nextInt()+" "+scanner.nextInt());
	    }
	    
	    int x=0;
	    int y=0;
	    
	    for(int i=0;i<n;i++){
	        if(s.charAt(i)=='R')x++;
	        if(s.charAt(i)=='L')x--;
	        if(s.charAt(i)=='U')y++;
	        if(s.charAt(i)=='D')y--;
	        h--;
	        if(h<0){
	            System.out.println(-1);
	            return;
	        }
	        if(item.contains(x+" "+y) && h<k){
	            h=k;
	            item.remove(x+" "+y);
	        }
	    }
	    System.out.println("Yes");
	}
}