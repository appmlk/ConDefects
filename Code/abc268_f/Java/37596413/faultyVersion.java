
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyScanner in = new MyScanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);

        int n = in.nextInt();
        String[] s=new String[n];
        for(int i=0;i<n;i++){
            s[i]=in.next();
        }

//        for(String ss:s){
//            out.print(ss);
//        }
        int[] sum=new int[n];
        int[] x=new int[n];
        long ans=0L;
        for(int i=0;i<n;i++){
            for(char c:s[i].toCharArray()){
                if(c=='X'){
                    x[i]++;
                }else{
                    ans+=(c-'0')*x[i];
                    sum[i]+=(c-'0');
                }
            }
        }

        Integer[] ids= IntStream.range(0,n).boxed().toArray(Integer[]::new);

        Arrays.sort(ids,(a,b)-> (sum[a]*x[b]==sum[b]*x[a])? (x[b] - x[a]) :(sum[a]*x[b]-sum[b]*x[a]));

        long temp=0L;
        for(int id:ids){
            ans+=temp*sum[id];
            temp+=x[id];
        }
        out.print(ans);

        out.close();
    }


    static class MyScanner {
        private BufferedReader br;
        private StringTokenizer tokenizer;

        public MyScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

}
