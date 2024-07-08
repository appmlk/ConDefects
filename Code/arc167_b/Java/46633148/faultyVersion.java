import java.util.*;
import java.io.*;

public class Main
{
       static class Reader { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream( 
                new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) { 
                if (c == '\n') { 
                    if (cnt != 0) { 
                        break; 
                    } 
                    else { 
                        continue; 
                    } 
                } 
                buf[cnt++] = (byte)c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') { 
                c = read(); 
            } 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') { 
                while ((c = read()) >= '0' && c <= '9') { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, 
                                 BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
    
    static long pow(long a , long b , long c)
    {
           if(b == 0)
           return 1;
           
           long ans = pow(a,b/2,c);
           if(b%2 == 0)
           return ans*ans%c;
           
           return ans*ans%c*a%c;
    }
    
       public static void main(String []args) throws IOException
       {
              Reader sc = new Reader();
              long a = sc.nextLong();
              long b = sc.nextLong();
              long pr = 1;
              boolean found = false;
              long mod = 998244353;
              long lim = (long)Math.sqrt(a);
              for(int i = 2 ; i <= lim ; i++)
              {
                     if(a%i == 0)
                     {
                            int cnt = 0;
                            while(a%i == 0)
                            {
                                   a /= i;
                                   cnt++;
                            }
                            
                            if(cnt%2 == 1 && b%2 == 1)
                            {
                            found = true;
                           // System.out.println(cnt + " " + b + " " + i);
                            }
                            
                            
                            long prr = b%mod*(long)cnt%mod;
                            
                            pr *= (1+prr)%mod;
                            pr %= mod;
                     }
              }
              
             // System.out.println(pr);
              if(a > 1)
              {
                     pr *= (1+(b%mod));
                     pr %= mod;
              }
              
              if(b%2 == 0)
              found = true;
              
              pr = pr*(b%mod)%mod;
              pr %= mod;
              
              //System.out.println(pr + " " + found);
              if(!found)
              {
                     pr--;
                     pr = (pr+mod)%mod;
              }
              
              pr *= pow(2,mod-2,mod);
              pr %= mod;
              System.out.println(pr);
       }
}