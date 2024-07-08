import java.util.*;

class DisjointSet {
  List<Integer> parent = new ArrayList<>();
  List<Integer> size = new ArrayList<>();

  void initialize(int n) {
    for (int i = 0; i < n; i++) {
      parent.add(-1);
      size.add(1);
    }
  }

  int root(int x) {
    while (true) {
      if (parent.get(x) == -1) {
        break;
      }
      x = parent.get(x);
    }

    return x;
  }

  void merge(int u, int v) {
    int rootU = root(u);
    int rootV = root(v);

    if (rootU == rootV) {
      return ;
    }
    if (size.get(rootU) < size.get(rootV)) {
      parent.set(rootU, rootV);
      size.set(rootV, size.get(rootU) + size.get(rootV));
    } else if (size.get(rootU) >= size.get(rootV)) {
      parent.set(rootV, rootU);
      size.set(rootU, size.get(rootU) + size.get(rootV));
    }
  }

  boolean isSame(int u, int v) {
    if (root(u) == root(v)) {
      return true;
    }
    return false;
  }
} 

class Main {

  public static long gcd(long a, long b) {
    while (a >= 1 && b >= 1) {
      if (a >= b) {
        a = (a % b);
      } else {
        b = (b % a);
      }
    }
    if (a != 0) {
      return a;
    }
    return b;
  }

  public static void main(String[] args) { Scanner sc = new Scanner(System.in);
    int h = Integer.parseInt(sc.next());
    int w = Integer.parseInt(sc.next());

    List<String> str = new ArrayList<>();
    for (int i = 0; i < h; i++) {
      str.add(sc.next());
    }

    List<Character> cha = new ArrayList<>();
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        cha.add(str.get(i).charAt(j));
      }
    }  

    List<Integer> id = new ArrayList<>();
    for (int i = 0; i < h * w; i++) {
      id.add(-1);
    }

    int n = 0;
    for (int i = 0; i < h * w; i++)  {
      if (cha.get(i) == '#') {
        n++;
      }
    }

    DisjointSet ds = new DisjointSet();
    ds.initialize(h * w);

    int base = n;

    for (int i = 0; i < h * w; i++) {
      if (cha.get(i) == '#') {
        if (i >= w && cha.get(i - w) == '#' && ds.isSame(i, i - w) == false) {
          base--;
          ds.merge(i, i - w);
        }
        if (i % w != w - 1 && cha.get(i + 1) == '#' && ds.isSame(i, i + 1) == false) {
          base--;
          ds.merge(i, i + 1);
        }
        if (i < (h - 1) * w && cha.get(i + w) == '#' && ds.isSame(i, i + w) == false) {
          base--;
          ds.merge(i, i + w);
        }
        if (i % w != 0 && cha.get(i - 1) == '#' && ds.isSame(i, i - 1) == false) {
          base--;
          ds.merge(i, i - 1);
        }
      }
    }

    long sum = 0;
    long cnt = 0;
    for (int i = 0; i < h * w; i++) {
      if (cha.get(i) != '#') {
        cnt++;
        int now = base;
        now++;
        Set<Integer> st = new HashSet<>();
        if (i >= w && cha.get(i - w) == '#') {
          st.add(ds.root(i - w));
        } 
        if (i % w != w - 1 && cha.get(i + 1) == '#') {
          st.add(ds.root(i + 1));
        } 
        if (i < (h - 1) * w && cha.get(i + w) == '#') {
          st.add(ds.root(i + w));
        } 
        if (i % w != 0 && cha.get(i - 1) == '#') {
          st.add(ds.root(i - 1));
        }
        now -= st.size();
        sum += now;
      }
    }
    
    long m = gcd(sum, cnt);
    sum /= m;
    cnt /= m;

    for (long i = 0; i < 998244353; i++) {
      if ((cnt % 998244353) * i % 998244353 == sum % 998244353) {
        System.out.println(i);
      }
    }

  }
}
