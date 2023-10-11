import java.util.*;
class Main {
    
	public static void main(String[] args){
	    Scanner scanner = new Scanner(System.in);
	    int q=scanner.nextInt();
	    
	    NavigableMap<Integer,Integer> s =new TreeMap<>();
	    
	    for(int i=0;i<q;i++){
	        int t=scanner.nextInt();
	        if(t==1){
	            int x=scanner.nextInt();
	            if(s.containsKey(x)){
	                s.put(x,s.get(x)+1);
	            } else{
	                s.put(x,1);
	            }
	        } else if(t==2){
	            int x=scanner.nextInt();
	            int c=scanner.nextInt();
	            if(s.containsKey(x)){
	                if(s.get(x)<=c){
	                    s.remove(x);
	                } else{
	                    s.put(x,s.get(x)-c);
	                }
	            }
	        } else{
	            int maxnum=s.lastEntry().getKey();
	            int minnum=s.firstEntry().getKey();
	            System.out.println(maxnum-minnum);
	        }
	    }
	}
}