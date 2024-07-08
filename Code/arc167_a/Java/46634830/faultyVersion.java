import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{

    public static void main(String[] args){

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(input);

        try{

            String[] str = buff.readLine().split(" ");
            int N = Integer.parseInt(str[0]);
            int M = Integer.parseInt(str[1]);
            long ans = 0L;
            String[] tmp = buff.readLine().split(" ");
            long[] dish = new long[M];
            int[] aa = new int[N];
            
            for( int i = 0; i < N; i++){
                aa[i] = Integer.parseInt(tmp[i]);
            }

            Arrays.sort(aa);
            System.out.println(Arrays.toString(aa));

            for( int i = N - 1, min = N - M, j = 0 ; i >= min; i--, j++){
                dish[j] += aa[i];
            }

            for( int i = N - M - 1, j = M - 1; i >= 0 ; i--, j-- ){
                dish[j] += aa[i];
            }

            for( int i = 0 ; i < M; i++){
                long a = dish[i];
                ans += a*a;
            }

            System.out.print(ans);
            

        buff.close();

        }catch( IOException e){
            System.out.println(e);
        }
    }

}