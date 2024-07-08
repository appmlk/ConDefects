import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main{

    public static void main(String[] args){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buff = new BufferedReader(input); 

        try {
            final int N = Integer.parseInt( buff.readLine());
            final int N_max = 2 * N + 2;
            int[] ans = new int[N_max];
            ans[1] = 0;
            String[] str = buff.readLine().split(" ");

            for( int i = 1 ; i <= N ; i++){

                int a = Integer.parseInt(str[i-1]);
                ans[2*i] = ans[a] + 1;
                ans[2*i +1] = ans[a] + 1;

            }
            
            StringBuilder t = new StringBuilder();
            for (int i = 1; i < N_max; i++) {
                t.append(ans[i]);
                t.append("\n");
            }
    
            System.out.print(t.toString());
            
            buff.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}