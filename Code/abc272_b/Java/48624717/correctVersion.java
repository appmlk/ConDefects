import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class Main{

    public static void main(String[] args){

        PrintWriter out = new PrintWriter(System.out);
        ArrayList<String> list = new ArrayList<>();
        list = lib.inputProcess();
        int N = Integer.parseInt(lib.arrayString(list.get(0), " ")[0]);
        int M = Integer.parseInt(lib.arrayString(list.get(0), " ")[1]);
        boolean[][] a = new boolean[N+1][N+1];

        for( int i = 1 ; i <= M ; i++){
            int[] t = lib.StringToIntArray(lib.arrayString(list.get(i), " "));
            int k = t[0];
            for( int j = 1 ; j <= k ; j++){
                for( int l = 1 ; l <= k; l++){
                    a[t[j]][t[l]] = true;
                    a[t[l]][t[j]] = true;
                }
            }
        }
        
        String ans = "Yes";
        r:for( int i = 1 ; i <= N ;i++){
            for( int j = 1 ; j <= N ;j++ ){
                if( i == j) continue;
                if( !a[i][j]){
                    ans = "No";
                    break r;
                }
            }
        }
        out.println(ans);
        out.flush();
    }
}

class lib{

    boolean[] seen;

    public static void outputData(List<String> list){
        for( String str: list){
            System.out.println(str);
        }
    }

    public static ArrayList<String> inputProcess(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(input);
        ArrayList<String> list = new ArrayList<>();
        try {

            while( true ){
                String str = buff.readLine();
                if( str == null) break;
                list.add(str);
            }
            buff.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static int[] changeIntArray(ArrayList<String> list){

        final int N = list.size();
        int[] array = new int[N];

        for( int i = 0 ; i < N ; i++ ){
            array[i] = Integer.parseInt( list.get(i));
        }

        return array;
    }

    public static double[] changeDoubleArray(ArrayList<String> list){

        final int N = list.size();
        double[] array = new double[N];

        for( int i = 0 ; i < N ; i++ ){
            array[i] = Double.parseDouble( list.get(i));
        }

        return array;
    }

    
    //標準入力をString型に置換
    public static String createString(ArrayList<String> list){
        
        final int N = list.size();
        StringBuilder stb = new StringBuilder();

        for( int i = 0 ; i < N ; i++ ){
            stb.append( list.get(i));
        }

        return stb.toString();
    }

    public static String[] arrayString(String str, String s){
        
        String[] array = str.split(s);
        return array;
    }

    
    //標準入力をint型配列に置換
    public static int[] StringToIntArray(String[] str){
        
        int N = str.length;
        int[] array = new int[N];
        for( int i = 0 ; i < N ; i++){
            array[i] = Integer.parseInt(str[i]);
        }
        return array;
    }

    //標準入力をLong型配列に置換
    public static long[] StringToLongArray(String[] str){
        
        int N = str.length;
        long[] array = new long[N];
        for( int i = 0 ; i < N ; i++){
            array[i] = Long.parseLong(str[i]);
        }
        return array;
    }

    //隣接リストの作成
    public static void graph(Map<Integer, List<Integer>> map, int v, int u){
            
        if( !map.containsKey(v)){
            List<Integer> li = new ArrayList<>();
            li.add(u);
            map.put(v, li);
        }else{
            List<Integer> li = map.get(v);
            li.add(u);
            map.put(v, li);
        }
    } 
}
