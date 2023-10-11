import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    int n = sc.nextInt();
    int q = sc.nextInt();
    int[] x = new int[n];
    for (int i = 0; i < n; i++) {
        x[i] = sc.nextInt();
    }

    List<ArrayList<Integer>> g = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      g.add(new ArrayList<>());
    }

    for (int i = 0; i < n - 1; i++) {
        int a = sc.nextInt() - 1;
        int b = sc.nextInt() - 1;
        g.get(a).add(b);
        g.get(b).add(a);
    }
    int[] V = new int[q];
    int[] K = new int[q];
    for (int i = 0; i < q; i++) {
        V[i] = sc.nextInt() - 1;
        K[i] = sc.nextInt() - 1;
    }
    sc.close();

    List<ArrayList<Integer>> subtree = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      subtree.add(new ArrayList<>());
    }

    fnc(g, 0, 0, subtree,x);

    for (int i = 0; i < q; i++) {
      pw.println(subtree.get(V[i]).get(K[i]));
    }
    pw.println(g);
    pw.println(subtree);
    pw.close();
  }

  public static ArrayList<Integer> fnc(List<ArrayList<Integer>> g, int pos, int n, List<ArrayList<Integer>> subtree, int[] x) {
    List<ArrayList<Integer>> st = new ArrayList<>();
    for (int node : g.get(n)) {
      if(pos != node){
        st.add(fnc(g, n, node, subtree, x));
      }
    }

    List<Integer> temp = new ArrayList<>();
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = 0; i < st.size(); i++) {
      temp.addAll(st.get(i));
    }
    temp.add(x[n]);
    Collections.sort(temp, Collections.reverseOrder());
    for (int i = 0; i < Math.min(20, temp.size()); i++) {
      ans.add(temp.get(i));
    }
    subtree.set(n, ans);
    return ans;
  }
}