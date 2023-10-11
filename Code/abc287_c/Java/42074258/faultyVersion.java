import java.util.*;
class Main {
    
	public static void main(String[] args) { 
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int m=scanner.nextInt();
		
		if(n-1!=m){
		    System.out.println("No");
		    return;
		}
		ArrayList<ArrayList<Integer>>list=new ArrayList<>();
		
		for(int i=0;i<=n;i++){
		    list.add(new ArrayList());
		}
		for(int i=0;i<m;i++){
		    int u=scanner.nextInt();
		    int v=scanner.nextInt();
		    
		    list.get(u).add(v);
		    list.get(v).add(u);
		}
		boolean[]visited =new boolean[n+1];
		visited[0]=true;
		LinkedList<Integer> queue =new LinkedList<>();
		queue.add(1);
		
		while(!queue.isEmpty()){
		    int element=queue.pop();
		    ArrayList<Integer> l = list.get(element);
		    
		    if(l.size()>2){
		        System.out.println("No");
		        return;
		    }
		    for(int rec: l){
		        if(visited[rec]==false){
		            visited[rec]=true;
		            queue.add(rec);
		        }
		    }
		}
		for(int i=0;i<n+1;i++){
		    if(visited[i]=false){
		        System.out.println("No");
		        return;
		    }
		}
		System.out.println("Yes");
	}
}