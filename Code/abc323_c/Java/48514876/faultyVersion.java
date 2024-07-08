
import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        // -------入力の読み取り-----
        int m = in.nextInt();
        int c = in.nextInt();
        int[] n = new int [c];
        int [][] nn = new int[c][c];
        for(int i = 0; i < c; i++){
            n[i] = in.nextInt();
            nn[i][0] = n[i];
            nn[i][1] = i;
        }

        boolean [][] box = new boolean [m][c];
        for(int i = 0; i < m; i++){
            char[] s = in.next().toCharArray();
            for(int j = 0; j < c; j++){
                if(s[j] == 'o'){
                    box[i][j] = true;
                }
            }
        }
        // -----readerを閉じる------
        in.close();
        
        // ----------処理-----------
        sortii(nn, 0);
        int[]sc = new int [m];
        int max = 0;
        for(int i = 0; i < m; i++){
            int a = i + 1;
            for(int j = 0; j < c; j++){
                if(box[i][j]){
                    a += n[j];
                }
            }
            if(a > max){
                max = a;
            }
            sc[i] = a;
        }
        for(int i = 0; i < m; i++){
            int ans = 0; 
            if(sc[i] == max){
                System.out.println(0);
                continue;
            }
            int b = sc[i];
            for(int j = c - 1; j >= 0; j--){
                if(box[i][nn[j][1]] == false){
                b += n[j];
                ans++;
                }
                if(b > max){
                    break;
                }
            }
            System.out.println(ans);
        }

        // ----------出力----------
         //System.out.println(ans);
    }
     
    // ----------------------以下メソッド--------------------------
    

    public static String yorn(boolean flag) {
        // trueならYes、falseならNoを返す
        String answer;
        if (flag) {
            answer = "Yes";
        } else {
            answer = "No";
        }
        return answer;
    }

   


    public static void dfs (int pos, ArrayList<ArrayList<Integer>> g, boolean[] seen){
        //深さ優先探索　訪問済みの区間をtrueにした配列を返す
        seen[pos] = true;
		for (int i : g.get(pos)) {
			if (seen[i] == false) {
				dfs(i, g, seen);
			}
		}
    }

    static List<Integer> int2List(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        Collections.sort(list);
        return list;
    }

    public static int[][] sortii (int [][] n, int target){
        //二次元配列をtarget行の値で昇順にソート
         Arrays.sort(n, (x, y) -> Integer.compare(x[target], y[target]));
         return n;
    }

    public static int[][] sortll(int[][] n, int target){
        // 二次元配列をtarget行の値で降順にソート
         Arrays.sort(n, (x, y) -> Integer.compare(y[target], x[target]));
        return n;
}


}

class FastReader{
    BufferedReader br;
    StringTokenizer st;

    public FastReader(){
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next(){
        while (st == null || !st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }

    long nextLong(){
        return Long.parseLong(next());
    }

    double nextDouble(){
        return Double.parseDouble(next());
    }

    String nextLine(){
        String str = "";
        try{
            str = br.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }
    void close() throws IOException{
        br.close();
    }
}

        
        