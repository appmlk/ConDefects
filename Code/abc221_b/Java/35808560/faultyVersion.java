import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
public class Main{
    public static void main(String args[]) throws IOException{
        Read sc=new Read();
        String s=sc.next();
        String t=sc.next();
        sc.println(ok(s,t)?"Yes":"No");
        //sc.print(0);
        sc.bw.flush();
        sc.bw.close();
    }
    public static boolean ok(String s,String t){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=t.charAt(i)){
                list.add(i);
            }
        }
        if(list.size()==0){
            return true;
        }
        if(list.size()!=2){
            return false;
        }
        int a=list.get(0),b=list.get(1);
        return s.charAt(a)==t.charAt(b)&&s.charAt(b)==t.charAt(a);
    }
}
//记住看数字范围，需要开long吗，需要用BigInteger吗，需要手动处理字符串吗，复杂度数量级控制在1e7或者以下了吗
//开数组的数据范围最高 @可爱抱抱 不能超过1e7，数据范围再大就要用哈希表离散化了
//基本数据类型不能自定义sort排序，二维数组就可以了，顺序排序的时候是小减大，注意返回值应该是int
class Read{
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;
    public Read(){
        bf=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer("");
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public String nextLine() throws IOException{
        return bf.readLine();
    }
    public String next() throws IOException{
        while(!st.hasMoreTokens()){
            st=new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }
    public char nextChar() throws IOException{
        // 确定下一个@可爱抱抱 只有一个字符的时候再用
        return next().charAt(0);
    }
    public int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    public long nextLong() throws IOException{
        return Long.parseLong(next());
    }
    public double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
    public float nextFloat() throws IOException{
        return Float.parseFloat(next());
    }
    public byte nextByte() throws IOException{
        return Byte.parseByte(next());
    }
    public short nextShort() throws IOException{
        return Short.parseShort(next());
    }
    public BigInteger nextBigInteger() throws IOException{
        return new BigInteger(next());
    }
    public void println(int a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(int a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(String a) throws IOException{
        bw.write(a);
        bw.newLine();
        return;
    }
    public void print(String a) throws IOException{
        bw.write(a);
        return;
    }
    public void println(long a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(long a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(double a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(double a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void print(BigInteger a) throws IOException{
        bw.write(a.toString());
        return;
    }
    public void print(char a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(char a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
}