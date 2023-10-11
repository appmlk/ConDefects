import java.io.*; import java.util.*;
public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		int[] pre = new int[n];
		for(int i=1; i<n; i++){
		    pre[i]+=pre[i-1];
		    if(s.charAt(i)!=s.charAt(i-1)){
		        pre[i]++;
		    }
		}
		for(int i=0; i<q; i++){
		    st = new StringTokenizer(br.readLine());
		    int l = Integer.parseInt(st.nextToken())-1;
		    int r = Integer.parseInt(st.nextToken())-1;
		    pw.println((((pre[r]-pre[l]-((s.charAt(l)==s.charAt(r))? 1 : 0)))/2)+Math.min(pre[r]-pre[l], 1));
		}
		pw.close();
	}
}
