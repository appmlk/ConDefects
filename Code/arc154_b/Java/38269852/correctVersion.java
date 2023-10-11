import java.io.*; import java.util.*;
public class Main
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		String a = br.readLine();
		String b = br.readLine();
		int[] temp = new int[26];
		int[] temp2 = new int[26];
		for(int i=0; i<n; i++){
		    temp[(int)a.charAt(i)-97]++;
		    temp2[(int)b.charAt(i)-97]++;
		}
		boolean found = true;
		for(int i=0; i<26; i++){
		    if(temp[i]!=temp2[i]){
		        found=false;
		        break;
		    }
		}
		int start = -1;
		int end = n-1;
		while(start<end-1){
		    int find = start+(end-start)/2;
		    int index = 0;
		    boolean k = true;
		    for(int i=find; i<n; i++){
		        while(index!=n&&b.charAt(index)!=a.charAt(i)){
		            index++;
		        }
		        if(index==n){
		            k=false;
		            break;
		        }
		        else{
		            index++;
		        }
		    }
		    if(k){
		        end=find;
		    }
		    else{
		        start=find;
		    }
		}
		if(found){
		    pw.println(end);
		}
		else{
		    pw.println(-1);
		}
		pw.close();
	}
}
