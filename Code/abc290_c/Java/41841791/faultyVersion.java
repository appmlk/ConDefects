import  java.util.*;
class Main {
	public static void main(String[] args) {
	    Scanner scanner =new Scanner(System.in);
	    int n=scanner.nextInt();
	    int k=scanner.nextInt();
	    
	    Set<Integer>set =new HashSet<>();
	    for(int i=0;i<n;i++){
	        set.add(scanner.nextInt());
	    }
	    for(int i=0;i<=k;i++){
	        if(set.size()==i || set.contains(i)){
	            System.out.println(i);
	            return;
	        }
	    }
	    System.out.println(k);
	}
}