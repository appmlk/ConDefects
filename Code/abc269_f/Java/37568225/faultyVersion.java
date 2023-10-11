import java.util.*;

public class Main {
  	static final long mod = 998244353L;
  	static long m;
 	static long n;
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
      n = sc.nextLong();
      m = sc.nextLong();
      int q = sc.nextInt();
      while(q-- > 0) {
      	long r1 = sc.nextLong();
        long r2 = sc.nextLong();
        long c1 = sc.nextLong();
        long c2 = sc.nextLong();
        long res = 0;
        for(int j = 0; j <= 1; j++) {
        	long curRes=0;
            long amount=0;
            long firstRow=r1+j;
            if(firstRow>r2){
              continue;
            }
            if((firstRow+c1)%2==1){
              if(c1+1>c2){
                continue;
              }
              amount=(c2-c1-1)/2+1;
              curRes=help(((firstRow-1)%mod)*(m%mod)+c1+1,2,amount,mod);
            }else {
              amount=(c2-c1)/2+1;
              curRes=help(((firstRow-1)%mod)*(m%mod)+c1,2,amount,mod);
            }
            curRes=help(curRes,(((amount%mod)*(m%mod))%mod)*2,(r2-firstRow)/2+1,mod);
            res+=curRes;
            res%=mod;
          System.out.println(res);
        }
      }
    }
  
  public static long help(long first,long distance,long amount,long mod){
        first%=mod;
        distance%=mod;
        amount%=mod;
        long last=first+distance*(amount-1);
        last%=mod;
        long res=(first+last)*inverse(2,mod);
        res%=mod;
        res*=amount;
        res%=mod;
        return res;
    }
  
  private static long inverse(long a, long mod) {
        long res=1;
        long exponent=mod-2;
        long base=a;
        while (exponent!=0){
            if((exponent&1)==1){
                res*=base;
                res%=mod;
            }
            base*=base;
            base%=mod;
            exponent=exponent>>1;
        }
        return res;
    }
}