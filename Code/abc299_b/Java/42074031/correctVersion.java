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

            //Set<Integer>set=new HashSet<>();
            //Map<Long,Integer>map=new HashMap<>();26
            //abcdefghijklmnopqrstuvwxyz
            //Map<Integer,List<Integer>>map=new HashMap<>();
            //TreeSet<Integer> set = new TreeSet<>();
            //int max=0;int min=2100000000;
            int n=in.nextInt();
            int m=in.nextInt();
            int arr[][]=new int [n][2];
            int max=0;int res=0;
            int t=0;
            for(int i=0;i<n;i++){
                arr[i][0]=in.nextInt();
                if(arr[i][0]==m)t++;
            }

            for(int i=0;i<n;i++){
                arr[i][1]=in.nextInt();
            }

            if(t!=0){

                for(int i=0;i<n;i++){
                    if(arr[i][0]==m&&arr[i][1]>max){
                        max=arr[i][1];res=i+1;
                    }
                }
            }
            else{
                m=arr[0][0];

                for(int i=0;i<n;i++){
                    if(arr[i][0]==m&&arr[i][1]>max){
                        max=arr[i][1];res=i+1;
                    }
                }
            }
            out.println(res);


            T--;
        }
        out.flush();
    }
}