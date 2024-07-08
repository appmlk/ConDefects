import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class Main{
  public static void main(String[] args){
    var in = new MyReader(System.in);
    int N = in.it();
    int[] A = new int[N];
    int[] B = new int[N];
    int[] cntA = new int[N];
    int[] cntB = new int[N];
    boolean has2 = false;
    for (int i = 0;i < N;i++) {
      A[i] = in.it() -1;
      cntA[A[i]]++;
      if (cntA[A[i]] == 2)
        has2 = true;
    }
    for (int i = 0;i < N;i++) {
      B[i] = in.it() -1;
      cntB[B[i]]++;
    }
    if (!Arrays.equals(cntA,cntB)) {
      System.out.println("No");
      return;
    }

    if (has2) {
      System.out.println("Yes");
      return;
    }

    System.out.println(tento(A,N) == tento(B,N) ? "Yes" : "No");

  }

  static byte tento(int[] arr,int max){
    BIT bit = new BIT(max);
    byte ans = 0;
    for (var a:arr) {
      ans ^= bit.sum(a +1);
      bit.upd(a,(byte) 1);
    }
    return ans;
  }
}

class BIT{
  int n;
  byte[] bit;

  BIT(int n){
    this.n = n;
    bit = new byte[n +1];
  }

  void upd(int x,byte v){
    for (x++;x <= n;x += x &-x)
      bit[x] += v;
  }

  byte sum(int x){
    byte ret = 0;
    for (;x > 0;x -= x &-x)
      ret ^= bit[x];
    return ret;
  }

}

class MyReader{
  byte[] buf = new byte[1 <<16];
  int ptr = 0;
  int tail = 0;
  InputStream in;

  MyReader(InputStream in){ this.in = in; }

  byte read(){
    if (ptr == tail)
      try {
        tail = in.read(buf);
        ptr = 0;
      } catch (IOException e) {}
    return buf[ptr++];
  }

  boolean isPrintable(byte c){ return 32 < c && c < 127; }

  boolean isNum(byte c){ return 47 < c && c < 58; }

  byte nextPrintable(){
    byte ret = read();
    while (!isPrintable(ret))
      ret = read();
    return ret;
  }

  int it(){
    byte i = nextPrintable();
    boolean negative = i == 45;
    int n = negative ? 0 : i -'0';
    while (isPrintable(i = read()))
      n = 10 *n +i -'0';
    return negative ? -n : n;
  }

}
