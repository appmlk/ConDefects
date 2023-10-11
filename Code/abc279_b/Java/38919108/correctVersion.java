import java.util.Scanner;
class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		RollingHash S = new RollingHash(sc.next());
		RollingHash T = new RollingHash(sc.next());
		int lenS = S.length();
		int lenT = T.length();
		boolean isContain = false;
		for(int i=0;i<=lenS-lenT;i++)
			isContain |= S.equals(T,i,i+lenT,0,lenT);
		System.out.println(isContain?"Yes":"No");
	}
}

final class RollingHash implements Comparable<RollingHash>{
	private static final int base = 100;
	private static final int mod1 = 1_000_000_007;
	private static final int mod2 = Integer.MAX_VALUE-1;
	private long[] hash1,hash2;
	private String string;
	public RollingHash(String str){
		string = str;
		hash1 = new long[str.length()+1];
		hash2 = new long[str.length()+1];
		roll();
	}
	private void roll(){
		int len = string.length();
		for(int i=1;i<=len;i++){
			hash1[i] = hash1[i-1]*base+string.charAt(i-1)-' '+1;
			hash2[i] = hash2[i-1]*base+string.charAt(i-1)-' '+1;
			hash1[i] %= mod1;
			hash2[i] %= mod2;
		}
	}
	public long getHash1(int l,int r){
		return (hash1[r]-hash1[l]*modPow(base,r-l,mod1)%mod1+mod1)%mod1;
	}
	public long getHash2(int l,int r){
		return (hash2[r]-hash2[l]*modPow(base,r-l,mod2)%mod2+mod2)%mod2;
	}
	private long modPow(long a,long b,long mod){
		a %= mod;
		b %= mod-1;
		long ans = 1;
		while(b>0){
			if((b&1)==1){
				ans *= a;
				ans %= mod;
			}
			a *= a;
			a %= mod;
			b >>= 1;
		}
		return ans;
	}
	public boolean equals(RollingHash rh,int l1,int r1,int l2,int r2){
		if(r1-l1!=r2-l2)
			return false;
		long hashValue1 = getHash1(l1,r1);
		long hashValue2 = getHash2(l1,r1);
		return hashValue1==rh.getHash1(l2,r2)
			&&hashValue2==rh.getHash2(l2,r2)
			&&check(rh,l1,l2,r1-l1);
	}
	private boolean check(RollingHash rh,int l1,int l2,int len){
		return check(rh.toString(),l1,l2,len);
	}
	private boolean check(String str,int l1,int l2,int len){
		for(int i=0;i<len;i++)
			if(string.charAt(l1+i)!=str.charAt(l2+i))
				return false;
		return true;
	}
	public int length(){
		return string.length();
	}
	@Override
	public int hashCode(){
		return string.hashCode();
	}
	@Override
	public String toString(){
		return string;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof RollingHash){
			RollingHash rh = (RollingHash)o;
			return equals(rh,1,length(),1,rh.length());
		}
		return false;
	}
	@Override
	public int compareTo(RollingHash rh){
		return string.compareTo(rh.toString());
	}
	public int compareTo(String str){
		return string.compareTo(str);
	}
	public char charAt(int i){
		return string.charAt(i);
	}
	public int compareToIgnoreCase(RollingHash rh){
		return string.compareToIgnoreCase(rh.toString());
	}
	public int compareToIgnoreCase(String str){
		return string.compareToIgnoreCase(str);
	}
	public void concat(RollingHash rh){
		concat(rh.toString());
	}
	public void concat(String str){
		string.concat(str);
		hash1 = new long[string.length()+1];
		hash2 = new long[string.length()+1];
		roll();
	}
	public boolean contains(RollingHash rh){
		long hash1 = rh.getHash1(0,rh.length());
		long hash2 = rh.getHash2(0,rh.length());
		boolean isContain = false;
		int len = length()-rh.length();
		for(int i=0;i<=len;i++){
			if(hash1==getHash1(i,rh.length()+i)
			 &&hash2==getHash2(i,rh.length()+i))
				isContain |= check(rh,i,0,rh.length());
		}
		return isContain;
	}
	public boolean contains(String str){
		return indexOf(str)!=-1;
	}
	public int indexOf(int ch){
		return indexOf(ch,0);
	}
	public int indexOf(int ch,int fromIndex){
		int len = length();
		for(int i=fromIndex;i<len;i++)
			if(string.charAt(i)==ch)
				return i;
		return -1;
	}
	public int indexOf(String str){
		return indexOf(str,0);
	}
	public int indexOf(String str,int fromIndex){
		long hash1 = 0;
		long hash2 = 0;
		for(char c:str.toCharArray()){
			hash1 = hash1*base + c-' '+1;
			hash2 = hash2*base + c-' '+1;
			hash1 %= mod1;
			hash2 %= mod2;
		}
		boolean isContain = false;
		int len = length()-str.length();
		for(int i=fromIndex;i<=len;i++){
			if(hash1==getHash1(i,str.length()+i)
			 &&hash2==getHash2(i,str.length()+i)
			 &&check(str,i,0,str.length()))
				return i;
		}
		return -1;
	}
	public boolean isEmpty(){
		return length()==0;
	}
	public int lastIndexOf(int ch,int fromIndex){
		for(int i=fromIndex;i>=0;i--)
			if(string.charAt(i)==ch)
				return i;
		return -1;
	}
	public int lastIndexOf(int ch){
		return lastIndexOf(ch,length()-1);
	}
	public static RollingHash valueOf(boolean b){
		return new RollingHash(b?"true":"false");
	}
	public static RollingHash valueOf(char c){
		return new RollingHash(""+c);
	}
	public static RollingHash valueOf(char[] c){
		return new RollingHash(String.valueOf(c,0,c.length));
	}
	public static RollingHash valueOf(char[] c,int offset,int count){
		return new RollingHash(String.valueOf(c,offset,count));
	}
	public static RollingHash valueOf(double d){
		return new RollingHash(String.valueOf(d));
	}
	public static RollingHash valueOf(float f){
		return new RollingHash(String.valueOf(f));
	}
	public static RollingHash valueOf(int i){
		return new RollingHash(String.valueOf(i));
	}
	public static RollingHash valueOf(long l){
		return new RollingHash(String.valueOf(l));
	}
	public static RollingHash valueOf(Object obj){
		return new RollingHash(String.valueOf(obj));
	}
}
