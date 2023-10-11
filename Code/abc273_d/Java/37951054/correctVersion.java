import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    FastInput in = new FastInput();
    StringBuffer res = new StringBuffer();
    var hw=in.getAutoIntArray();
    var n=in.getAutoIntArray()[0];
    HashMap<Integer,TreeSet<Integer>> row=new HashMap<>();
    HashMap<Integer,TreeSet<Integer>> col=new HashMap<>();
    for (int i = 0; i < n; i++) {
        var mid=in.getAutoIntArray();
        if(row.containsKey(mid[0]))
            row.get(mid[0]).add(mid[1]);
        else {
            TreeSet<Integer> set=new TreeSet<>();
            set.add(mid[1]);
            row.put(mid[0], set);
        }
        if(col.containsKey(mid[1]))
            col.get(mid[1]).add(mid[0]);
        else {
            TreeSet<Integer> set=new TreeSet<>();
            set.add(mid[0]);
            col.put(mid[1], set);
        }
    }
    var q=in.getAutoIntArray()[0];
    int x=hw[2];
    int y=hw[3];
    TreeSet<Integer> D=new TreeSet<>();
    for (int i = 0; i < q; i++) {
        var mid=in.in.readLine();
        var s=Integer.valueOf(mid.split(" ")[1]);
        switch (mid.charAt(0)) {
            case 'L': var v1=row.getOrDefault(x,D).lower(y); if(v1==null) {y=Math.max(1, y-s);} else{y=Math.max(v1+1, y-s);} break;
            case 'R': var v2=row.getOrDefault(x,D).higher(y); if(v2==null) {y=Math.min(hw[1], y+s);} else{y=Math.min(v2-1, y+s);} break;
            case 'U': var v3=col.getOrDefault(y,D).lower(x); if(v3==null) {x=Math.max(1, x-s);} else{x=Math.max(v3+1, x-s);} break;
            case 'D': var v4=col.getOrDefault(y,D).higher(x); if(v4==null) {x=Math.min(hw[0], x+s);} else{x=Math.min(v4-1, x+s);} break;
            default:
                break;
        }
        res.append(x+" "+y);
        res.append("\n");
    }
    System.out.println(res.toString().substring(0,res.length()-1));
    }
}
class FastInput {
    BufferedReader in = null;

    public FastInput() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public FastInput(String path) {
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int[][] zip(int[] x, int[] y) {
        int[][] res = new int[x.length][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = x[i];
            res[i][1] = y[i];
        }
        return res;
    }

    public int[] getAutoIntArray() throws IOException {
        String[] data = in.readLine().split(" ");
        int[] res = new int[data.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.valueOf(data[i]);
        }
        return res;
    }

    public long[] getAutoLongArray() throws IOException {
        String[] data = in.readLine().split(" ");
        long[] res = new long[data.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Long.valueOf(data[i]);
        }
        return res;
    }

    public double[] getAutoDoubleArray() throws IOException {
        String[] data = in.readLine().split(" ");
        double[] res = new double[data.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Double.valueOf(data[i]);
        }
        return res;
    }

    public int[] getIntArray(int len) throws IOException {
        int[] res = new int[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.valueOf(data[i]);
        }
        return res;
    }

    public ArrayList<Integer> getIntArrayList(int len) throws IOException {
        ArrayList<Integer> res = new ArrayList<>();
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            res.add(Integer.valueOf(data[i]));
        }
        return res;
    }

    public Integer[] getIntegerArray(int len) throws IOException {
        Integer[] res = new Integer[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Integer.valueOf(data[i]);
        }
        return res;
    }

    public long[] getLongArray(int len) throws IOException {
        long[] res = new long[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Long.valueOf(data[i]);
        }
        return res;
    }

    public ArrayList<Long> getLongArrayList(int len) throws IOException {
        ArrayList<Long> res = new ArrayList<>();
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < len; i++) {
            res.add(Long.valueOf(data[i]));
        }
        return res;
    }

    public double[] getDoubleArray(int len) throws IOException {
        double[] res = new double[len];
        String[] data = in.readLine().split(" ");
        for (int i = 0; i < res.length; i++) {
            res[i] = Double.valueOf(data[i]);
        }
        return res;
    }

    public int[] getStringToIntArray(int len) throws IOException {
        int[] res = new int[len];
        String s = in.readLine();
        for (int i = 0; i < res.length; i++) {
            if (s.charAt(i) == '0') {
                res[i] = 0;
            } else {
                res[i] = 1;
            }
        }
        return res;
    }

    public static void printArray(int[] x) {
        System.out.println(getArrayString(x));
    }

    public static void printArray(long[] x) {
        System.out.println(getArrayString(x));
    }

    public static void printArray(double[] x) {
        System.out.println(getArrayString(x));
    }

    public static String getArrayString(double[] x) {
        if (x == null) {
            return "null";
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < x.length; i++) {
            res.append(x[i]);
            if (i + 1 != x.length)
                res.append(" ");
        }
        return res.toString();
    }

    public static String getArrayString(long[] x) {
        if (x == null) {
            return "null";
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < x.length; i++) {
            res.append(x[i]);
            if (i + 1 != x.length)
                res.append(" ");
        }
        return res.toString();
    }

    public static String getArrayString(int[] x) {
        if (x == null) {
            return "null";
        }
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < x.length; i++) {
            res.append(x[i]);
            if (i + 1 != x.length)
                res.append(" ");
        }
        return res.toString();
    }
}
