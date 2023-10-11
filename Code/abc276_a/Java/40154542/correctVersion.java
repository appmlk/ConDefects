import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        //BufferedReader br = new BufferedReader(new FileReader("atcoder_abc/input.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        for(int i = arr.length - 1;i >= 0;i--){
            if(arr[i] == 'a'){
                System.out.println(i+1);
                return;
            }            
        }
        System.out.println(-1);
    }
}