import java.util.*;
class Main {
    static int n;
    static int m;
    static boolean ans;
    static String[]s;
    static List<Integer>list;
    
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		n=scanner.nextInt();
		m=scanner.nextInt();
		s=new String[n];
		for(int i=0;i<n;i++){
		    s[i]=scanner.next();
		}
		list=new ArrayList<>();
		
		dfs(0);
		
		
		if(ans){
		    System.out.println("Yes");
		} else{
		    System.out.println("No");
		}
	}
		
		public static void dfs(int t){
		    if(t==n){
		        check();
		        return;
		    }
		    for(int i=0;i<n;i++){
		        if(!list.contains(i)){
		            list.add(i);
		            dfs(t+1);
		            list.remove(list.size()-1);
		        }
		    }
		}
		
		public static void check(){
		    for(int i=0;i<n-1;i++){
		        int count=0;
		        for(int j=0;j<m;j++){
		            char a=s[list.get(i)].charAt(j);
		            char b=s[list.get(i+1)].charAt(j);
		            if(a!=b){
		                count++;
		            }
		        }
		        if(count>1){
		            return;
		        }
		      
		    }
		    ans=true;  
		}
}