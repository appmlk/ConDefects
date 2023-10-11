import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> mp = new HashMap<>();
        String line = br.readLine();
        if(line.charAt(0)=='1'){
            System.out.println("No");
            return;
        }
        boolean[] a= new boolean[7];
        a[0]=(line.charAt(6)=='1');
        a[1]=(line.charAt(3)=='1');
        a[2]=(line.charAt(1)=='1'||line.charAt(7)=='1');
        a[3]=(line.charAt(0)=='1'||line.charAt(4)=='1');
        a[4]=(line.charAt(2)=='1'||line.charAt(8)=='1');
        a[5]=(line.charAt(5)=='1');
        a[6]=(line.charAt(9)=='1');
        int val=0;
        for(int i=0;i<6;i++)
            if((val%2==0&&a[i])||(val%2==1&&!a[i]))val++;
        if(val>2){
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
        br.close();
    }
}
