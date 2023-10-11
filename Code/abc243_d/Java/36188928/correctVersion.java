	import java.util.*;
  import java.io.*;
  class Main{	
		public static void main(String[] args) {
		FastScanner str=new FastScanner(System.in);
	  int n=str.nextInt();
		long x=str.nextLong();
		String s=str.next();
		Stack<Character>stack=new Stack<>();
		for(int i=0;i<n;i++){
      stack.push(s.charAt(i));
			if(stack.size()>=2&&stack.peek()=='U'){
				char u1=stack.peek();
				stack.pop();
				if(stack.peek()=='R'||stack.peek()=='L'){
					stack.pop();
				}else{
					stack.push(u1);
				}
			}
		}
		int a=stack.size();
    Stack<Character>stack2=new Stack<>();
		for(int i=0;i<a;i++){
      char key=stack.pop();
			stack2.push(key);
		}
    int a1=stack2.size();
		for(int i=0;i<a1;i++){
			char key=stack2.pop();
			if(x%2==0){
        if(key=='U')x=x/2;
				if(key=='R')x=x*2+1;
				if(key=='L')x=x*2;
			}else{
        if(key=='U')x=x/2;
        if(key=='R')x=x*2+1;
        if(key=='L')x=x*2;
			}
		}
		

		System.out.println(x);
	
		
		
  }	
	
}	



			
			
			

	
					
				
				
			
			
		

			
			
		class FastScanner implements Closeable {
		private final InputStream in;
		private final byte[] buffer = new byte[1024];
		private int ptr = 0;
		private int buflen = 0;
		public FastScanner(InputStream in) {
			this.in = in;
		}
		private boolean hasNextByte() {
			if (ptr < buflen) {
				return true;
			}else{
				ptr = 0;
				try {
					buflen = in.read(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (buflen <= 0) {
					return false;
				}
			}
			return true;
		}
		private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
		private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
		public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
		public String next() {
			if (!hasNext()) throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while(isPrintableChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}
		public long nextLong() {
			if (!hasNext()) throw new NoSuchElementException();
			long n = 0;
			boolean minus = false;
			int b = readByte();
			if (b == '-') {
				minus = true;
				b = readByte();
			}
			if (b < '0' || '9' < b) {
				throw new NumberFormatException();
			}
			while(true){
				if ('0' <= b && b <= '9') {
					n *= 10;
					n += b - '0';
				}else if(b == -1 || !isPrintableChar(b)){
					return minus ? -n : n;
				}else{
					throw new NumberFormatException();
				}
				b = readByte();
			}
		}
		public int nextInt() {
			long nl = nextLong();
			if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
			return (int) nl;
		}
		public double nextDouble() { return Double.parseDouble(next());}
		public void close() {
			try {
				in.close();
			} catch (IOException e) {
			}
		}

			}

			