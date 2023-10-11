import java.util.*;
import java.util.HashMap;
import java.io.*;
import java.math.*;

public class Main {
    
    private static PrintWriter wr = new PrintWriter(System.out);

    private static long privateNum;
    
    public static void write(Object obj) {
        wr.println(obj);
    }
    public static void flush() {
        wr.flush();
    }
    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static long numGetter() {
        return privateNum;
    }

    public static void numSetter(long input) {
        privateNum = input;
    }


    public static void main(String[] args) {    

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int n_len = Integer.parseInt(reader.readLine());

            // String[] inputArr = reader.readLine().split(" ");

            long[][] log_in_out_arr = new long[n_len][2];
            
            TreeMap<Long, Integer> logger_map = new TreeMap<>();


            for(int i = 0; i < n_len; i++) {

                String[] inputArr = reader.readLine().split(" ");

                long logIn_date = Long.parseLong(inputArr[0]);
                long logOut_data = logIn_date + Long.parseLong(inputArr[1]);

                log_in_out_arr[i][0] = logIn_date;
                log_in_out_arr[i][1] = logOut_data;

                logger_map.put(logIn_date, 0);
                logger_map.put(logOut_data, 0);

            }
            
            for(int i = 0; i < n_len; i++) {
                long logIn_date = log_in_out_arr[i][0];
                logger_map.put(logIn_date, logger_map.get(logIn_date) + 1);
                
                long logOut_date = log_in_out_arr[i][1];
                logger_map.put(logOut_date, logger_map.get(logOut_date) - 1);
            }

            long[] population_arr = new long[n_len + 1];
            Arrays.fill(population_arr, 0);

            TreeSet<Long> date_set = new TreeSet<>(logger_map.keySet());

            long prev_date = 0;

            int player_sum = 0;

            for(long date: date_set) {

                long current_date = date;

                long sub_date = current_date - prev_date;

                population_arr[player_sum] += sub_date;

                player_sum += logger_map.get(date);


                prev_date = current_date;

            }

            String[] output_arr = new String[n_len];

            for(int i = 0; i < n_len; i++) {
                output_arr[i] = "" + population_arr[i + 1];
            }

            println(String.join(" ", output_arr));


            
        }catch(IOException e) {
            println(e);
        }

    }
    // main

}
