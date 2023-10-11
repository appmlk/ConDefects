import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine());

        if ( N <= Math.pow(2, 31) && N >= - Math.pow(2, 31)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}