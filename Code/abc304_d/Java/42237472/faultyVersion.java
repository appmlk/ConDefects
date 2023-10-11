import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        long W = arr.get(0);
        long H = arr.get(1);
        long N = Integer.parseInt(bufferedReader.readLine());
        List<ArrayList<Integer>> strawberries = new ArrayList<>((int) N);
        for(int i = 0;i < N;i++){
            arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());
            ArrayList<Integer> item = new ArrayList<>(2);
            item.add(arr.get(0));
            item.add(arr.get(1));
            strawberries.add(item);
        }
        long A = Integer.parseInt(bufferedReader.readLine());
        arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        Integer[] xLines = new Integer[(int) A+2];
        for(int i = 0;i < A;i++){
            xLines[i] = arr.get(i);
        }
        xLines[(int) (A)] = 0;
        xLines[(int) (A+1)] = Math.toIntExact(W);

        long B = Integer.parseInt(bufferedReader.readLine());
        arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        Integer[] yLines = new Integer[(int) B+2];
        for(int i = 0;i < B;i++){
            yLines[i] = arr.get(i);
        }
        yLines[(int) (B)] = 0;
        yLines[(int) (B+1)] = Math.toIntExact(H);
        Arrays.sort(xLines);
        Arrays.sort(yLines);
        Map<String,Long> map = new HashMap<>();
        Map<Long,Long> countMap = new HashMap<>();
        countMap.put((long)0,(A+1)*(B+1) );
        long max = 0;
        for(int i = 0; i < N;i++){
            int x = strawberries.get(i).get(0);
            int xInx = Arrays.binarySearch(xLines,x);
            int y = strawberries.get(i).get(1);
            int yInx = Arrays.binarySearch(yLines,y);
            String key = Math.abs(xInx) + "#" + Math.abs(yInx);
            map.put(key,map.getOrDefault(key,(long)0)+1);
            max = Math.max(max,map.get(key));
            long count = map.get(key);
            countMap.put(count,countMap.getOrDefault(count,(long)0)+1);
            countMap.put(count-1,countMap.get(count-1)-1);
        }
        long min = 0;
        while (!countMap.containsKey(min)){
            min ++;
        }
        System.out.println(min + " " + max);
        bufferedReader.close();
    }
}
