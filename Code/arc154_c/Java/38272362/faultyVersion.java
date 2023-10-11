import java.io.*; import java.util.*;
public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		while(t-->0){
		    int n = Integer.parseInt(br.readLine());
		    int[] arr = new int[n];
		    int[] brr = new int[n];
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    ArrayList<Integer> string = new ArrayList<>();
		    boolean circle = false;
		    for(int i=0; i<n; i++){
		        arr[i]=Integer.parseInt(st.nextToken());
		        if(i==0){
		            string.add(arr[i]);
		        }
		        else{
		            if(!string.get(string.size()-1).equals(arr[i])){
		                string.add(arr[i]);
		            }
		            else{
		                circle = true;
		            }
		        }
		    }
		    if(string.size()!=1&&string.get(0).equals(string.get(string.size()-1))){
		        string.remove(string.size()-1);
		        circle = true;
		    }
		    st = new StringTokenizer(br.readLine());
		    ArrayList<Integer> brace = new ArrayList<>();
		    for(int i=0; i<n; i++){
		        brr[i]=Integer.parseInt(st.nextToken());
		        if(i==0){
		            brace.add(brr[i]);
		        }
		        else{
		            if(!brace.get(brace.size()-1).equals(brr[i])){
		                brace.add(brr[i]);
		            }
		            else{
		                circle = true;
		            }
		        }
		    }
		    if(brace.size()!=1&&brace.get(0).equals(brace.get(brace.size()-1))){
		        brace.remove(brace.size()-1);
		        circle = true;
		    }
		    String ans = "Yes";
		    if(circle){
		        for(int z=0; z<string.size(); z++){
		            int start = z;
		            ans="Yes";
		            for(int i=0; i<brace.size(); i++){
		                while(start!=z+string.size()&&string.get(start%string.size())!=brace.get(i)){
		                    start++;
		                }
		                if(start==z+string.size()){
		                    ans="No";
		                    break;
		                }
		            }
		            if(ans.equals("Yes"))break;
		        }
		    }
		    else{
		        for(int i=0; i<n; i++){
		            if(arr[i]!=brr[i]){
		                ans="No";
		                break;
		            }
		        }
		    }
		    pw.println(ans);
		}
		pw.close();
	}
}
