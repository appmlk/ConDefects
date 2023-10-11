import java.io.*;
import java.util.*;
public class Main {
    
    static PrintWriter out = new PrintWriter(System.out);
    static Scanner in = new Scanner(System.in);
    static BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    //String[] strs = re.readLine().split(" "); int a = Integer.parseInt(strs[0]);
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        //String[] strs = re.readLine().split(" ");
        //int T=Integer.parseInt(strs[0]);
        
        //int T=in.nextInt();
        int T=1;
        while(T>0){
            //String[] strs1 = re.readLine().split(" ");
            //int n=Integer.parseInt(strs1[0]);
            //String s=re.readLine();
            //char arr[]=s.toCharArray();

            //long[][] p = new long[n][2];//对 long 二维数组排序
            //Arrays.sort(p, (a, b) -> a[0] <= b[0] ? -1 : 1);
            //Set<Integer>set=new HashSet<>();
            //Map<Long,Integer>map=new HashMap<>();
            //Map<Integer,List<Integer>>map=new HashMap<>();
            //TreeSet<Integer> set = new TreeSet<>();
            //int max=0;int min=2100000000;
            int a=in.nextInt();
            int b=in.nextInt();
            int c=in.nextInt();
            int x=in.nextInt();
            if(x>a&&x<=b){
                double aa=b-a;
                double res;
                if(c==aa){
                    out.println("1.000000000000");
                }
                else{
                    res=c/(aa*1.0);
                    out.println(res);
                }
            }
            else{
                if(x<=a)out.println("1.000000000000");
                else out.println("0.000000000000");
            }
            T--;
        }
        out.flush();
    }
}