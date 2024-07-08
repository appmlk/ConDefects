import java.io.*;
import java.util.*;
import java.math.*;
class Main {
    public static void main(String[] args)
    {
        FastReader sc = new FastReader();
        PrintWriter out=new PrintWriter(System.out);
        int n=sc.nextInt();
        int[] arr=new int[2*n];
        for(int i=0;i<n;++i) arr[i]=sc.nextInt();
        int c=0;
        for(int i=0;i<2*n-2;++i) {
          if(arr[i]==arr[i+2]) ++c;
        }
        out.println(c);
        out.flush();
    }
    static class FastReader 
    { 
        BufferedReader br;
        StringTokenizer st;
        public FastReader()
        {
            br = new BufferedReader(
                new InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements()) 
            {
                try 
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() 
        { 
            return Integer.parseInt(next()); 
        }

        long nextLong()
        { 
            return Long.parseLong(next()); 
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return (str);
        }
    }
}
