import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] s;
        int tt = 1;
        for(int t=0; t<tt; ++t) {
            s = bu.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            s = bu.readLine().split(" ");
            char[] c = s[0].toCharArray();
            int[] a = new int[n];
            for(int i=0; i<n; ++i) {
                a[i] = c[i] - 'a';
                update(1,0,n-1,i,a[i]);
            }
            int l = 0, r = n-1;
            while(l<=r){
                while(l<=r && a[l]==min(1,0,n-1,l+1,r)) ++l;
                int smallest = min(1,0,n-1,l,r);
                while(l<=r && a[r]!=smallest) --r;
                if(l<=r) a[l] = a[l]^a[r]^(a[r]=a[l]);
                ++l; --r;
            }
            for(int i=0; i<n; ++i)
                sb.append((char)(a[i]+'a'));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] t = new int[(int)(1e6)];

    static void update(int v, int tl, int tr, int pos, int newVal){
        if(tl==tr) t[v] = newVal;
        else{
            int tm = (tl+tr)/2;
            if(pos<=tm) update(v*2, tl, tm, pos, newVal);
            else update(v*2+1, tm+1, tr, pos, newVal);
            t[v] = Integer.min(t[v*2],t[v*2+1]);
        }
    }

    static int min(int v, int tl ,int tr, int l, int r){
        if(l>r) return Integer.MAX_VALUE/2;
        if(tl==l && tr==r) return t[v];
        int tm = (tl+tr)/2;
        return Integer.min(min(v*2, tl, tm, l, Integer.min(r,tm)), min(v*2+1, tm+1, tr, Integer.max(l,tm+1), r));
    }
}
