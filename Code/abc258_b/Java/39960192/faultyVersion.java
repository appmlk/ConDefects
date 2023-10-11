
import java.util.Scanner;

class Main {
	
    public static void main (String[] args) {
    	Scanner sc=new Scanner(System.in);
    	
    	int n=sc.nextInt();
    	//数値ではなく、数字をとる。取り出しは数字で。
    	char[][] s=new char[n][n];
    	for(int i=0;i<n;i++) {
    		s[i]=sc.next().toCharArray();
    	}
    	
    	//数値の配列をつくる。表
    	int[][] map=new int[n][n];
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			map[i][j]=s[i][j]-'0';//数字から数値にかえる
    		}
    	}
    	
    	//八方位（north, northeast, east, southeast, south, southwest, west, northwest）
    	int[] dx= {0,-1,-1,-1,0,1,1,1};
    	int[] dy= {-1,-1,0,1,1,1,0,-1};
    	
    	long ans=0;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			//8方向別れ
    			for(int k=0;k<8;k++) {
    				long sum=0;
    				//n-1回移動を繰り返す
    				for(int l=0;l<n;l++) {
    					int y=(i+dy[k]*l+n)%n;
    					int x=(j+dx[k]*l+n)%n;
    					
    					//トレース出力
//    					System.out.print("i="+i+",j="+j+"*");
//    					System.out.print("x="+x+",y="+y);
//    					System.out.println();
//    					sum=sum*10+map[y][x];
//    					System.out.print("sum="+sum+",");
    					
    					
    					ans=Math.max(ans, sum);
    					}
    			}
    		}
    	}
    	System.out.println(ans);
    	
    }
}