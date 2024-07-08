import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int q = sc.nextInt();
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            st.setValue(i, sc.nextInt());
        }
        String result = IntStream.range(0, q).mapToObj(i -> {
            if (sc.nextInt() == 1) {
                st.setValue(sc.nextInt() - 1, sc.nextInt());
                return null;
            } else {
                return String.valueOf(st.getSecondCount(sc.nextInt() - 1, sc.nextInt()));
            }
        }).filter(x -> x != null)
            .collect(Collectors.joining("\n"));
        System.out.println(result);
    }
    
    static class SegmentTree {
        int size;
        int[] firsts;
        int[] firstCount;
        int[] seconds;
        int[] secondCount;
        
        public SegmentTree(int x) {
            size = 2;
            while (size < x) {
                size <<= 1;
            }
            firsts = new int[size * 2 - 1];
            firstCount = new int[size * 2 - 1];
            seconds = new int[size * 2 - 1];
            secondCount = new int[size * 2 - 1];
        }
        
        public void setValue(int idx, int v) {
            firsts[size - 1 + idx] = v;
            firstCount[size - 1 + idx] = 1;
            calc((size - 1 + idx - 1) / 2);
        }
        
        private void calc(int idx) {
            int left = idx * 2 + 1;
            int right = idx * 2 + 2;
            if (firsts[left] == firsts[right]) {
                firsts[idx] = firsts[left];
                firstCount[idx] = firstCount[left] + firstCount[right];
                if (seconds[left] == seconds[right]) {
                    seconds[idx] = seconds[left];
                    secondCount[idx] = secondCount[left] + secondCount[right];
                } else if (seconds[left] > seconds[right]) {
                    seconds[idx] = seconds[left];
                    secondCount[idx] = secondCount[left];
                } else {
                    seconds[idx] = seconds[right];
                    secondCount[idx] = secondCount[right];
                }
            } else if (firsts[left] > firsts[right]) {
                firsts[idx] = firsts[left];
                firstCount[idx] = firstCount[left];
                if (seconds[left] == firsts[right]) {
                    seconds[idx] = seconds[left];
                    secondCount[idx] = secondCount[left] + firstCount[right];
                } else if (seconds[left] > firsts[right]) {
                    seconds[idx] = seconds[left];
                    secondCount[idx] = secondCount[left];
                } else {
                    seconds[idx] = firsts[right];
                    secondCount[idx] = firstCount[right];
                }
            } else {
                firsts[idx] = firsts[right];
                firstCount[idx] = firstCount[right];
                if (firsts[left] == seconds[right]) {
                    seconds[idx] = firsts[left];
                    secondCount[idx] = firstCount[left] + secondCount[right];
                } else if (firsts[left] > seconds[right]) {
                    seconds[idx] = firsts[left];
                    secondCount[idx] = firstCount[left];
                } else {
                    seconds[idx] = seconds[right];
                    secondCount[idx] = secondCount[right];
                }
            }
            if (idx > 0) {
                calc((idx - 1) / 2);
            }
        }
        
        public int getSecondCount(int left, int right) {
            return getCount(getValue(0, 0, size, left, right)[1], 0, 0, size, left, right);
        }
        
        private int[] getValue(int idx, int min, int max, int left, int right) {
            if (left <= min && max <= right) {
                return new int[]{firsts[idx], seconds[idx]};
            }
            if (right <= min || max <= left) {
                return new int[2];
            }
            int[] value1 = getValue(idx * 2 + 1, min, (min + max) / 2, left, right);
            int[] value2 = getValue(idx * 2 + 2, (min + max) / 2, max, left, right);
            int[] ans  = new int[2];
            if (value1[0] == value2[0]) {
                ans[0] = value1[0];
                if (value1[1] >= value2[1]) {
                    ans[1] = value1[1];
                } else {
                    ans[1] = value2[1];
                }
            } else if (value1[0] > value2[0]) {
                ans[0] = value1[0];
                if (value1[1] >= value2[0]) {
                    ans[1] = value1[1];
                } else {
                    ans[1] = value2[0];
                }
            } else {
                ans[1] = value2[0];
                if (value1[0] >= value2[1]) {
                    ans[1] = value1[0];
                } else {
                    ans[1] = value2[1];
                }
            }
            return ans;
        }
        
        private int getCount(int value, int idx, int min, int max, int left, int right) {
            if (left <= min && max <= right) {
                if (firsts[idx] == value) {
                    return firstCount[idx];
                } else if (seconds[idx] == value) {
                    return secondCount[idx];
                } else {
                    return 0;
                }
            }
            if (right <= min || max <= left) {
                return 0;
            }
            return getCount(value, idx * 2 + 1, min, (min + max) / 2, left, right) + 
                getCount(value, idx * 2 + 2, (min + max) / 2, max, left, right);
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Arrays.toString(firsts)).append("\n")
                .append(Arrays.toString(firstCount)).append("\n")
                .append(Arrays.toString(seconds)).append("\n")
                .append(Arrays.toString(secondCount)).append("\n");
            return sb.toString();
        }
    }
}
class Scanner {
    BufferedReader br;
    StringTokenizer st = new StringTokenizer("");
    StringBuilder sb = new StringBuilder();
    
    public Scanner() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            
        }
    }
    
    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    public long nextLong() {
        return Long.parseLong(next());
    }
    
    public double nextDouble() {
        return Double.parseDouble(next());
    }
    
    public String next() {
        try {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return st.nextToken();
        }
    }
    
}