import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

class Main{

    public static void main(String[] args){

        PrintWriter out = new PrintWriter(System.out);
        ArrayList<String> list = new ArrayList<>();
        list = lib.inputProcess();
        int N = Integer.parseInt(lib.arrayString(list.get(0), " ")[0]);
        int X = Integer.parseInt(lib.arrayString(list.get(0), " ")[1]);
        int Y = Integer.parseInt(lib.arrayString(list.get(0), " ")[2]);
        int Z = Integer.parseInt(lib.arrayString(list.get(0), " ")[3]);
        int[] a = lib.StringToIntArray(lib.arrayString(list.get(1), " "));
        int[] b = lib.StringToIntArray(lib.arrayString(list.get(2), " "));
        
        int cntx = 1, cnty = 1, cntz = 1;
        Set<Integer> pass = new TreeSet<>();

        //操作1
        Map<Integer, Integer> math = new HashMap<>();
        for( int i = 0 ; i < N ; i++){
            math.put(i+1, a[i]);
        }
        
        List<Map.Entry<Integer,Integer>> mathValuesList = new ArrayList<Map.Entry<Integer, Integer>>(math.entrySet());
        Collections.sort(mathValuesList, comparator);
        for( Map.Entry<Integer,Integer> v : mathValuesList){
            if( cntx > X) break;
            pass.add(v.getKey());
            cntx++;
        }

        //操作2
        Map<Integer, Integer> english = new HashMap<>();
        for( int i = 0 ; i < N ; i++){
            if( !pass.contains(i+1)){
                english.put(i+1, b[i]);
            }
        }

        List<Map.Entry<Integer,Integer>> englishValuesList = new ArrayList<Map.Entry<Integer, Integer>>(english.entrySet());
        Collections.sort(englishValuesList, comparator);
        for( Map.Entry<Integer,Integer> v : englishValuesList){
            if( cnty > Y) break;
            pass.add(v.getKey());
            cnty++;            
        }

        //操作3
        Map<Integer, Integer> total = new HashMap<>();
        for( int i = 0 ; i < N ; i++){
            if( !pass.contains(i+1)){
                total.put(i+1, a[i]+b[i]);
            }
        }

        List<Map.Entry<Integer,Integer>> totalValuesList = new ArrayList<Map.Entry<Integer, Integer>>(total.entrySet());
        Collections.sort(totalValuesList, comparator);
        for( Map.Entry<Integer,Integer> v : totalValuesList){
            if( cntz > Z) break;
            pass.add(v.getKey());
            cntz++;            
        }

        //回答出力
        for( int ans : pass){
            out.println(ans);
            out.flush();
        }
        

    }

    static Comparator<Map.Entry<Integer,Integer>> comparator = new Comparator<Map.Entry<Integer,Integer>>(){
        public int compare( Map.Entry<Integer,Integer> entry1, Map.Entry<Integer,Integer> entry2 ){
            if( entry2.getValue().compareTo(entry1.getValue()) == 0){
                return (entry1.getKey()).compareTo(entry2.getKey());
            }else{
                return (entry2.getValue().compareTo(entry1.getValue()));
            }
        }
    };
}

class lib{

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

    public static int[] StringToIntArray(String[] str){
        
        int N = str.length;
        int[] array = new int[N];
        for( int i = 0 ; i < N ; i++){
            array[i] = Integer.parseInt(str[i]);
        }
        return array;
    }
}
