import java.io.*;
import java.util.*;
 
 
public class Main {
    public static void main(String[] args) throws IOException {
        var in=new FastInput();
		var np=in.getIntArray(2);
		long[][][] dp=new long[np[0]-1][np[0]+1][4];
		dp[0][0][0]=1;
		dp[0][1][0]=4;
		dp[0][2][1]=1;
		dp[0][2][2]=1;
		dp[0][2][3]=1;
		for (int i = 1; i < dp.length; i++) {
			for (int j = np[0]-1; j >= 0; j--) {
				if(j>1){
					dp[i][j][0]=dp[i-1][j-1][0]*3+dp[i-1][j][0]+dp[i-1][j][1]+dp[i-1][j][2]+dp[i-1][j][3];
					dp[i][j][0]%=np[1];
					dp[i][j][1]=dp[i-1][j-1][1];
					dp[i][j][2]=dp[i-1][j-1][2]+dp[i-1][j-2][0];
					dp[i][j][3]=dp[i-1][j-1][3]+dp[i-1][j-2][0];
				}else if(j==1){
					dp[i][j][0]=dp[i-1][j-1][0]*3+dp[i-1][j][0]+dp[i-1][j][1]+dp[i-1][j][2]+dp[i-1][j][3];
					dp[i][j][0]%=np[1];
					dp[i][j][1]=dp[i-1][j-1][1];
					dp[i][j][2]=dp[i-1][j-1][2];
					dp[i][j][3]=dp[i-1][j-1][3];
				}else{
					dp[i][j][0]=dp[i-1][j][0];
				}
			}
		}
		StringBuffer res=new StringBuffer();
		for (int i = 1; i < dp[0].length; i++) {
			res.append(dp[dp.length-1][i][0]);
			if(i+1!=np[0]){
				res.append(' ');
			}
		}
		System.out.println(res);
    }
}
 
class FastInput {
    BufferedReader in = null;
 
    public FastInput() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }
 
    public int[] getIntArray(int len) throws IOException {
        int[] res = new int[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.valueOf(data[i]);
        }
        return res;
    }
 
    public long[] getLongArray(int len) throws IOException {
        long[] res = new long[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Long.valueOf(data[i]);
        }
        return res;
    }
 
    public double[] getDoubleArray(int len) throws IOException {
        double[] res = new double[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Double.valueOf(data[i]);
        }
        return res;
    }
}