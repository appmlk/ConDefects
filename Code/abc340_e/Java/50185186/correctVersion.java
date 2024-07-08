
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Throwable {
        Main main = new Main();
        main.solve();
    }
    public void solve() throws Throwable {
        FastScan scan = new FastScan(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        long[] A = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextLong();
        }
        SegmentTree<Long> tree = new SegmentTree<>(N, 0L, Long::sum);
        for (int i = 0; i < N; i++) {
            tree.update(i, i == 0 ? A[i] : (A[i]-A[i-1]));
        }
        long[] ans = new long[N];
        for (int i = 0; i < M; i++) {
            int index = scan.nextInt();
            long value = tree.query(0, index+1);
            tree.update(index, tree.query(index, index+1)-value);
            if (index+1 < N) {
                tree.update(index+1, tree.query(index+1, index+2)+value);
            }
            int count_to_last = N - 1 - index;
            if (0 < count_to_last) {
                tree.update(index + 1, tree.query(index + 1, index + 2) + 1L);
                if (value < count_to_last) {
                    tree.update(index+1+(int)value, tree.query(index+1+(int)value, index+1+(int)value+1) - 1L);
                }
                value -= Math.min(value,count_to_last);
            }
            long count_full = value / N;
            if (0 < count_full) {
                tree.update(0, tree.query(0,1)+count_full);
            }
            if (value % N == 0) {
                continue;
            }
            long remain = value % N;
            int idx = (int) remain;
            tree.update(0, tree.query(0,1)+1L);
            tree.update(idx, tree.query(idx,idx+1)-1L);
        }

        for (int j = 0; j < N; j++) {
            ans[j] = tree.query(0, j+1);
        }
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(Arrays.stream(ans).mapToObj(i -> Long.toString(i)).collect(Collectors.joining(" ")));
        pw.flush();
        pw.close();
    }
    class SegmentTree<T> {
        int size;
        T initialValue;
        List<T> list;
        //T[] list;
        BiFunction<? super T, ? super T, ? extends T> biFunction;
        SegmentTree(int n, T initialValue, BiFunction<? super T, ? super T, ? extends T> biFunction) {
            int size = 1;
            while (size < n) {
                size *= 2;
            }
            this.size = size;
            List<T> list = new ArrayList<>();
            for (int i = 0; i < size * 2; i++) {
                list.add(initialValue);
            }
            //T[] list = (T[]) new Object[size * 2];
            //Arrays.fill(list, initialValue);
            this.list = list;
            this.initialValue = initialValue;
            this.biFunction = biFunction;
        }
        void update(int index, T value) {
            index += this.size - 1;// index番目の要素は実際にはthis.size - 1 + indexのところに存在する
            list.set(index, value);
            //this.list[index] = value;
            while (0 < index) {
                // 更新すべき場所を親の方にたどるのは (index - 1) / 2 でできる
                index = (index - 1) / 2;
                // その親の要素の子供は 2 * index + 1, 2 * index + 2 となる
                // それらの要素にbiFunctionを適用した値を保持する
                list.set(index, biFunction.apply(list.get(2 * index + 1), list.get(2 * index + 2)));
                //this.list[index] = biFunction.apply(this.list[2 * index + 1], this.list[2 * index + 2]);
            }
        }
        T query(int start, int end) {
            return query_top_down(start, end, 0, 0, this.size);
        }
        T query_bottom_up(int start, int end) {
            int left = start + this.size - 1;
            int right = end + this.size - 1;
            T result = this.initialValue;
            while (left < right) {
                if ((left & 1) == 0) {
                    result = this.biFunction.apply(result, list.get(left));
                    //result = this.biFunction.apply(result, list[left]);
                }
                if ((right & 1) == 0) {
                    result = this.biFunction.apply(result, list.get(right - 1));
                    //result = this.biFunction.apply(result, list[right - 1]);
                }
                left /= 2;
                right = (right - 1) / 2;
            }
            return result;
        }
        T query_top_down(int start, int end, int index, int left, int right) {
            if (right <= start || end <= left) {
                // 範囲外の場合は初期値を返却する
                return this.initialValue;
            }
            if (start <= left && right <= end) {
                return list.get(index);
                //return this.list[index];
            }
            T value_left = query_top_down(start, end, index * 2 + 1, left, (left + right) / 2);
            T value_right = query_top_down(start, end, index * 2 + 2, (left + right) / 2, right);
            return biFunction.apply(value_left, value_right);
        }
    }
    class FastScan {
        BufferedReader br;
        StringTokenizer st;
        FastScan(InputStream is) {
            InputStreamReader isr = new InputStreamReader(is);
            this.br = new BufferedReader(isr);
        }
        String next() throws IOException {
            while (this.st == null || !this.st.hasMoreTokens()) {
                this.st = new StringTokenizer(br.readLine().trim());
            }
            return st.nextToken();
        }
        long nextLong() throws IOException {
            return Long.parseLong(this.next());
        }
        int nextInt() throws IOException {
            return Integer.parseInt(this.next());
        }
    }
}
