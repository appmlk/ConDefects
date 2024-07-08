import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.stream.Collectors;

public class Main {
    static In in = new In();
    static Out out = new Out();
    static final long inf = 0x1fffffffffffffffL;
    static final int iinf = 0x3fffffff;
    static final double eps = 1e-9;
    static long mod = 1000000007;

    List<IntPair> list;
    void solve() {
        int n = in.nextInt();
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            list.add(new IntPair(x + y, x - y));
        }
        list.sort(Comparator.<IntPair>comparingInt(p -> p.second).thenComparingInt(p -> p.first));
        WaveletMatrix<IntPair> wm = new WaveletMatrix<>(list);
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = a + b;
            int d = a - b;
            int k = in.nextInt();
            int left = 0;
            int right = 500000;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                int ll = lowerBound(list, d - mid);
                int rr = upperBound(list, d + mid);
                int count = 0;
                if (ll < rr) {
                    count = wm.rangeFreq(new IntPair(c - mid, -iinf), new IntPair(c + mid + 1, -iinf), ll, rr);
                }
                if (count >= k) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            out.println(right);
        }
    }

    int lowerBound(List<IntPair> list, int value) {
        int left = -1;
        int right = list.size();
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (list.get(mid).second < value) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    int upperBound(List<IntPair> list, int value) {
        int left = -1;
        int right = list.size();
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (list.get(mid).second <= value) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    class IntPair implements Comparable<IntPair> {
        int first;
        int second;

        IntPair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof IntPair)) {
                return false;
            }
            IntPair that = (IntPair)o;
            return first == that.first && second == that.second;
        }

        @Override
        public int hashCode() {
            return first * 31 + second;
        }

        @Override
        public int compareTo(IntPair o) {
            return first == o.first ? Integer.compare(second, o.second) : Integer.compare(first, o.first);
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", first, second);
        }
    }

    class BitVector {
        private static final int CHUNK_WIDTH = 256;
        private static final int BLOCK_WIDTH = 32;
        private static final int BLOCK_PER_CHUNK = CHUNK_WIDTH / BLOCK_WIDTH;
        private final int length;
        private final int[] bit;
        private final byte[] blocks;
        private final int[] chunk;
        private boolean isBuilt;

        BitVector(int nbits) {
            this.length = nbits;
            this.bit = new int[nbits / BLOCK_WIDTH + 1];
            this.blocks = new byte[nbits / BLOCK_WIDTH + 1];
            this.chunk = new int[nbits / CHUNK_WIDTH + 1];
        }

        void flip(int bitIndex) {
            int blockPos = bitIndex / BLOCK_WIDTH;
            int offset = bitIndex % BLOCK_WIDTH;
            bit[blockPos] ^= 1 << offset;
        }

        void set(int bitIndex) {
            int blockPos = bitIndex / BLOCK_WIDTH;
            int offset = bitIndex % BLOCK_WIDTH;
            bit[blockPos] |= 1 << offset;
        }

        void clear(int bitIndex) {
            int blockPos = bitIndex / BLOCK_WIDTH;
            int offset = bitIndex % BLOCK_WIDTH;
            bit[blockPos] &= ~(1 << offset);
        }

        int get(int bitIndex) {
            int blockPos = bitIndex / BLOCK_WIDTH;
            int offset = bitIndex % BLOCK_WIDTH;
            return bit[blockPos] >> offset & 1;
        }

        void build() {
            int acc = 0;
            for (int i = 0; i <= length / BLOCK_WIDTH; i++) {
                if (i % BLOCK_PER_CHUNK == 0) {
                    chunk[i / BLOCK_PER_CHUNK] = acc;
                }
                blocks[i] = (byte)(acc - chunk[i / BLOCK_PER_CHUNK]);
                acc += Integer.bitCount(bit[i]);
            }
            this.isBuilt = true;
        }

        int rank(int b, int left, int right) {
            return b == 0 ? rankZero(left, right) : rankOne(left, right);
        }

        int rank(int b, int k) {
            return b == 0 ? rankZero(k) : rankOne(k);
        }

        int rankZero(int left, int right) {
            return rankZero(right) - rankZero(left);
        }

        int rankZero(int bitIndex) {
            return bitIndex - rankOne(bitIndex);
        }

        int rankOne(int left, int right) {
            return rankOne(right) - rankOne(left);
        }

        int rankOne(int bitIndex) {
            checkBuilt();
            int chunkPos = bitIndex / CHUNK_WIDTH;
            int blockPos = bitIndex / BLOCK_WIDTH;
            int offset = bitIndex % BLOCK_WIDTH;
            int masked = bit[blockPos] & ((1 << offset) - 1);
            return chunk[chunkPos] + Byte.toUnsignedInt(blocks[blockPos]) + Integer.bitCount(masked);
        }

        int select(int b, int k) {
            return b == 0 ? selectZero(k) : selectOne(k);
        }

        int select(int b, int k, int left) {
            return b == 0 ? selectZero(k, left) : selectOne(k, left);
        }

        int selectZero(int k, int left) {
            return selectZero(k + rankZero(left));
        }

        int selectZero(int k) {
            checkBuilt();
            if (rankZero(length) <= k) {
                return -1;
            }
            int left = 0;
            int right = length;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (rankZero(mid) <= k) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        int selectOne(int k, int left) {
            return selectOne(k + rankOne(left));
        }

        int selectOne(int k) {
            checkBuilt();
            if (rankOne(length) <= k) {
                return -1;
            }
            int left = 0;
            int right = length;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (rankOne(mid) <= k) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        int length() {
            return length;
        }

        @Override
        public String toString() {
            BitSet bitSet = new BitSet(length);
            for (int i = 0; i < length; i++) {
                bitSet.set(i, get(i) == 1);
            }
            return bitSet.toString();
        }

        private void checkBuilt() {
            if (!isBuilt) {
                throw new IllegalStateException("bit vector is not built");
            }
        }
    }

    class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair<?, ?> that = (Pair<?, ?>)o;
            return Objects.equals(first, that.first) && Objects.equals(second, that.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", first, second);
        }
    }

    class WaveletMatrix<T extends Comparable<T>> {
        private final int n;
        private final int m;
        private final int bitLen;
        private final int[] mids;
        private final List<BitVector> bitVectors = new ArrayList<>();
        private final List<T> values = new ArrayList<>();

        WaveletMatrix(List<T> list) {
            this.n = list.size();
            TreeMap<T, Integer> map = new TreeMap<>();
            for (T t : list) {
                map.put(t, 0);
            }
            int size = 0;
            for (Map.Entry<T, Integer> entry : map.entrySet()) {
                values.add(entry.getKey());
                entry.setValue(size);
                size++;
            }
            this.m = size;
            this.bitLen = Integer.bitCount(Integer.highestOneBit(m * 2 - 1) - 1);
            this.mids = new int[bitLen];
            int[] dataCurrent = new int[n];
            int[] dataNext = new int[n];
            for (int i = 0; i < n; i++) {
                dataCurrent[i] = map.get(list.get(i));
            }
            for (int bit = 0; bit < bitLen; bit++) {
                bitVectors.add(new BitVector(n));
                int pos = 0;
                for (int i = 0; i < n; i++) {
                    if ((dataCurrent[i] >> (bitLen - bit - 1) & 1) == 0) {
                        dataNext[pos++] = dataCurrent[i];
                    }
                }
                mids[bit] = pos;
                for (int i = 0; i < n; i++) {
                    if ((dataCurrent[i] >> (bitLen - bit - 1) & 1) == 1) {
                        bitVectors.get(bit).set(i);
                        dataNext[pos++] = dataCurrent[i];
                    }
                }
                bitVectors.get(bit).build();
                int[] temp = dataCurrent;
                dataCurrent = dataNext;
                dataNext = temp;
            }
        }

        // k 番目の値
        T get(int k) {
            rangeCheck(k, 0, n);
            return get0(k);
        }

        // [left, right) で最小の値
        T min() {
            return values.get(0);
        }

        T min(int left, int right) {
            rangeCheck(left, right);
            return kthMin(0, left, right);
        }

        // [left, right) で最大の値
        T max() {
            return values.get(m - 1);
        }

        T max(int left, int right) {
            rangeCheck(left, right);
            return kthMax(0, left, right);
        }

        // [left, right) の value の個数
        int rank(T value) {
            return rank(value, 0, n);
        }

        int rank(T value, int left, int right) {
            rangeCheck(left, right);
            return rankAll(value, left, right).rank;
        }

        // [left, right) の value に対して 等しい/小さい/大きい 値の個数
        RankResult rankAll(T value) {
            return rankAll(value, 0, n);
        }

        RankResult rankAll(T value, int left, int right) {
            rangeCheck(left, right);
            return rankAll0(value, left, right);
        }

        // [left, right) の k 番目の value の位置
        int select(T value, int k) {
            rangeCheck(k, 0, n);
            return select0(value, k);
        }

        int select(T value, int k, int left, int right) {
            rangeCheck(k, 0, n);
            rangeCheck(k, left, right);
            int prev = left == 0 ? 0 : rank(value, 0, left);
            int select = select0(value, prev + k);
            return select < right ? select : -1;
        }

        // [left, right) の k 番目に小さい値
        T kthMin(int k) {
            rangeCheck(k, 0, n);
            return kthMin(k, 0, n);
        }

        T kthMin(int k, int left, int right) {
            rangeCheck(k, 0, right - left);
            return kthMin0(k, left, right);
        }

        // [left, right) の k 番目に大きい値
        T kthMax(int k) {
            rangeCheck(k, 0, n);
            return kthMax(k, 0, n);
        }

        T kthMax(int k, int left, int right) {
            rangeCheck(k, 0, right - left);
            return kthMin(right - left - 1 - k, left, right);
        }

        // [left, right) の k 番目に小さい値のうち最も小さい index
        int kthMinIndex(int k) {
            rangeCheck(k, 0, n);
            return kthMinIndex(k, 0, n);
        }

        int kthMinIndex(int k, int left, int right) {
            rangeCheck(k, 0, right - left);
            return select(kthMin0(k, left, right), 0, left, right);
        }

        // [left, right) の k 番目に大きい値のうち最も小さい index
        int kthMaxIndex(int k) {
            return kthMaxIndex(k, 0, n);
        }

        int kthMaxIndex(int k, int left, int right) {
            rangeCheck(k, 0, right - left);
            return select(kthMax(k, left, right), 0, left, right);
        }

        // [left, right) で値が [a, b) 内のものの個数
        int rangeFreq(T a, T b) {
            return rankAll(b).rankLessThan - rankAll(a).rankLessThan;
        }

        int rangeFreq(T a, T b, int left, int right) {
            rangeCheck(left, right);
            return rankAll(b, left, right).rankLessThan - rankAll(a, left, right).rankLessThan;
        }

        // [left, right) で値が higher より小さく最大のもの（存在しない場合null）
        T prevValue(T higher) {
            int lower = lower(higher);
            return lower == -1 ? null : values.get(lower);
        }

        T prevValue(T higher, int left, int right) {
            rangeCheck(left, right);
            int count = rankAll(higher, left, right).rankLessThan;
            return count == 0 ? null : kthMin(count - 1, left, right);
        }

        // [left, right) で値が lower より大きく最小のもの（存在しない場合null）
        T nextValue(T lower) {
            int higher = higher(lower);
            return higher == m ? null : values.get(higher);
        }

        T nextValue(T lower, int left, int right) {
            rangeCheck(left, right);
            int count = rankAll(lower, left, right).rankMoreThan;
            return count == 0 ? null : kthMax(count - 1, left, right);
        }

        // [left, right) で出現頻度が多い順に k 個の値と頻度、出現頻度が同じ場合値が小さいほうから
        List<Pair<T, Integer>> topK(int k) {
            return topK(k, 0, n);
        }

        List<Pair<T, Integer>> topK(int k, int left, int right) {
            rangeCheck(left, right);
            rangeCheck(k, 0, n + 1);
            return topK0(k, left, right);
        }

        // [left, right) で値の小さい順に k 個の値と頻度
        List<Pair<T, Integer>> minK(int k) {
            return minK(k, 0, n);
        }

        List<Pair<T, Integer>> minK(int k, int left, int right) {
            rangeCheck(left, right);
            rangeCheck(k, 0, n + 1);
            return minK0(k, left, right);
        }

        // [left, right) で値の大きい順に k 個の値と頻度
        List<Pair<T, Integer>> maxK(int k) {
            return maxK(k, 0, n);
        }

        List<Pair<T, Integer>> maxK(int k, int left, int right) {
            rangeCheck(left, right);
            rangeCheck(k, 0, n + 1);
            return maxK0(k, left, right);
        }

        // [left, right) で値が [a, b) 内のものの値と頻度、値の昇順
        List<Pair<T, Integer>> rangeList(T a, T b) {
            return rangeList(a, b, 0, n);
        }

        List<Pair<T, Integer>> rangeList(T a, T b, int left, int right) {
            rangeCheck(left, right);
            return rangeList0(a, b, left, right);
        }

        // [left1, right1) と [left2, right2) に共通して出現するものの値と頻度、値の昇順
        List<IntersectResult> intersect(int left1, int right1, int left2, int right2) {
            rangeCheck(left1, right1);
            rangeCheck(left2, right2);
            return intersect0(left1, right1, left2, right2);
        }

        private T get0(int k) {
            int index = 0;
            for (int bit = 0; bit < bitLen; bit++) {
                int dir = bitVectors.get(bit).get(k);
                index = index << 1 | dir;
                k = bitVectors.get(bit).rank(dir, k) + dir * mids[bit];
            }
            return values.get(index);
        }

        private RankResult rankAll0(T value, int left, int right) {
            RankResult result = new RankResult();
            int floor = floor(value);
            if (floor == -1) {
                result.rankMoreThan = right - left;
                return result;
            }
            for (int bit = 0; bit < bitLen; bit++) {
                int dir = floor >> (bitLen - bit - 1) & 1;
                int leftCount = bitVectors.get(bit).rank(dir, left);
                int rightCount = bitVectors.get(bit).rank(dir, right);
                if (dir == 1) {
                    result.rankLessThan += (right - left) - (rightCount - leftCount);
                } else {
                    result.rankMoreThan += (right - left) - (rightCount - leftCount);
                }
                left = leftCount + dir * mids[bit];
                right = rightCount + dir * mids[bit];
            }
            result.rank = right - left;
            if (values.get(floor).compareTo(value) != 0) {
                result.rankLessThan += result.rank;
                result.rank = 0;
            }
            return result;
        }

        private int select0(T value, int k) {
            int id = getId(value);
            if (id == -1) {
                return -1;
            }
            int[] lefts = new int[bitLen];
            int[] rights = new int[bitLen];
            int left = 0;
            int right = n;
            for (int bit = 0; bit < bitLen; bit++) {
                lefts[bit] = left;
                rights[bit] = right;
                int dir = id >> (bitLen - bit - 1) & 1;
                left = bitVectors.get(bit).rank(dir, left) + dir * mids[bit];
                right = bitVectors.get(bit).rank(dir, right) + dir * mids[bit];
            }
            for (int bit = bitLen - 1; bit >= 0; bit--) {
                int dir = id >> (bitLen - bit - 1) & 1;
                k = bitVectors.get(bit).select(dir, k, lefts[bit]);
                if (k < 0 || rights[bit] <= k) {
                    return -1;
                }
                k -= lefts[bit];
            }
            return k;
        }

        private T kthMin0(int k, int left, int right) {
            int id = 0;
            for (int bit = 0; bit < bitLen; bit++) {
                int count = bitVectors.get(bit).rankZero(left, right);
                int dir = k >= count ? 1 : 0;
                id = id << 1 | dir;
                if (dir == 1) {
                    k -= count;
                }
                left = bitVectors.get(bit).rank(dir, left) + dir * mids[bit];
                right = bitVectors.get(bit).rank(dir, right) + dir * mids[bit];
            }
            return values.get(id);
        }

        private List<Pair<T, Integer>> topK0(int k, int left, int right) {
            List<Pair<T, Integer>> result = new ArrayList<>();
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.add(new Node(left, right, 0, 0));
            while (!queue.isEmpty() && result.size() < k) {
                Node node = queue.poll();
                if (node.depth == bitLen) {
                    result.add(new Pair<>(values.get(node.value), node.right - node.left));
                    continue;
                }
                int leftZero = bitVectors.get(node.depth).rankZero(node.left);
                int rightZero = bitVectors.get(node.depth).rankZero(node.right);
                if (leftZero < rightZero) {
                    queue.add(new Node(leftZero, rightZero, node.depth + 1, node.value));
                }
                int leftOne = node.left - leftZero + mids[node.depth];
                int rightOne = node.right - rightZero + mids[node.depth];
                if (leftOne < rightOne) {
                    queue.add(new Node(leftOne, rightOne, node.depth + 1, node.value | (1 << (bitLen - node.depth - 1))));
                }
            }
            return result;
        }

        private List<Pair<T, Integer>> minK0(int k, int left, int right) {
            List<Pair<T, Integer>> result = new ArrayList<>();
            Deque<Node> deque = new ArrayDeque<>();
            deque.addLast(new Node(left, right, 0, 0));
            while (!deque.isEmpty() && result.size() < k) {
                Node node = deque.pollLast();
                if (node.depth == bitLen) {
                    result.add(new Pair<>(values.get(node.value), node.right - node.left));
                    continue;
                }
                int leftZero = bitVectors.get(node.depth).rankZero(node.left);
                int rightZero = bitVectors.get(node.depth).rankZero(node.right);
                if (leftZero < rightZero) {
                    deque.addLast(new Node(leftZero, rightZero, node.depth + 1, node.value));
                }
                int leftOne = node.left - leftZero + mids[node.depth];
                int rightOne = node.right - rightZero + mids[node.depth];
                if (leftOne < rightOne) {
                    deque.addLast(new Node(leftOne, rightOne, node.depth + 1, node.value | (1 << (bitLen - node.depth - 1))));
                }
            }
            return result;
        }

        private List<Pair<T, Integer>> maxK0(int k, int left, int right) {
            List<Pair<T, Integer>> result = new ArrayList<>();
            Deque<Node> deque = new ArrayDeque<>();
            deque.addLast(new Node(left, right, 0, 0));
            while (!deque.isEmpty() && result.size() < k) {
                Node node = deque.pollLast();
                if (node.depth == bitLen) {
                    result.add(new Pair<>(values.get(node.value), node.right - node.left));
                    continue;
                }
                int leftOne = bitVectors.get(node.depth).rankOne(node.left) + mids[node.depth];
                int rightOne = bitVectors.get(node.depth).rankOne(node.right) + mids[node.depth];
                if (leftOne < rightOne) {
                    deque.addLast(new Node(leftOne, rightOne, node.depth + 1, node.value | (1 << (bitLen - node.depth - 1))));
                }
                int leftZero = node.left - leftOne + mids[node.depth];
                int rightZero = node.right - rightOne + mids[node.depth];
                if (leftZero < rightZero) {
                    deque.addLast(new Node(leftZero, rightZero, node.depth + 1, node.value));
                }
            }
            return result;
        }

        private List<Pair<T, Integer>> rangeList0(T a, T b, int left, int right) {
            if (a.compareTo(b) >= 0) {
                return new ArrayList<>();
            }
            List<Pair<T, Integer>> result = new ArrayList<>();
            Deque<Node> deque = new ArrayDeque<>();
            deque.addLast(new Node(left, right, 0, 0));
            while (!deque.isEmpty()) {
                Node node = deque.pollLast();
                if (node.depth == bitLen) {
                    if (a.compareTo(values.get(node.value)) <= 0) {
                        result.add(new Pair<>(values.get(node.value), node.right - node.left));
                    }
                    continue;
                }
                int leftOne = bitVectors.get(node.depth).rankOne(node.left) + mids[node.depth];
                int rightOne = bitVectors.get(node.depth).rankOne(node.right) + mids[node.depth];
                int nextValue = node.value | (1 << (bitLen - node.depth - 1));
                if (leftOne < rightOne && values.get(nextValue).compareTo(b) < 0) {
                    deque.addLast(new Node(leftOne, rightOne, node.depth + 1, nextValue));
                }
                int leftZero = node.left - leftOne + mids[node.depth];
                int rightZero = node.right - rightOne + mids[node.depth];
                if (leftZero < rightZero) {
                    deque.addLast(new Node(leftZero, rightZero, node.depth + 1, node.value));
                }
            }
            return result;
        }

        private List<IntersectResult> intersect0(int left1, int right1, int left2, int right2) {
            List<IntersectResult> result = new ArrayList<>();
            Deque<IntersectNode> deque = new ArrayDeque<>();
            deque.addLast(new IntersectNode(left1, right1, left2, right2, 0, 0));
            while (!deque.isEmpty()) {
                IntersectNode node = deque.pollLast();
                if (node.depth == bitLen) {
                    result.add(new IntersectResult(values.get(node.value), node.right1 - node.left1, node.right2 - node.left2));
                    continue;
                }
                int leftOne1 = bitVectors.get(node.depth).rankOne(node.left1) + mids[node.depth];
                int rightOne1 = bitVectors.get(node.depth).rankOne(node.right1) + mids[node.depth];
                int leftOne2 = bitVectors.get(node.depth).rankOne(node.left2) + mids[node.depth];
                int rightOne2 = bitVectors.get(node.depth).rankOne(node.right2) + mids[node.depth];
                if (leftOne1 < rightOne1 && leftOne2 < rightOne2) {
                    deque.addLast(new IntersectNode(leftOne1, rightOne1, leftOne2, rightOne2, node.depth + 1, node.value | (1 << (bitLen - node.depth - 1))));
                }
                int leftZero1 = node.left1 - leftOne1 + mids[node.depth];
                int rightZero1 = node.right1 - rightOne1 + mids[node.depth];
                int leftZero2 = node.left2 - leftOne2 + mids[node.depth];
                int rightZero2 = node.right2 - rightOne2 + mids[node.depth];
                if (leftZero1 < rightZero1 && leftZero2 < rightZero2) {
                    deque.addLast(new IntersectNode(leftZero1, rightZero1, leftZero2, rightZero2, node.depth + 1, node.value));
                }
            }
            return result;
        }

        private int getId(T value) {
            int left = -1;
            int right = m;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (values.get(mid).compareTo(value) <= 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left == -1 || values.get(left).compareTo(value) != 0 ? -1 : left;
        }

        private int lower(T value) {
            int left = -1;
            int right = m;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (values.get(mid).compareTo(value) < 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        private int higher(T value) {
            int left = -1;
            int right = m;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (values.get(mid).compareTo(value) <= 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return right;
        }

        private int floor(T value) {
            int left = -1;
            int right = m;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (values.get(mid).compareTo(value) <= 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        private int ceiling(T value) {
            int left = -1;
            int right = m;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (values.get(mid).compareTo(value) < 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            return right;
        }

        private void rangeCheck(int left, int right) {
            if (left >= right) {
                throw new IllegalArgumentException("left >= right");
            }
        }

        private void rangeCheck(int k, int left, int right) {
            rangeCheck(left, right);
            if (k < 0 || right - left <= k) {
                throw new IllegalArgumentException("k: " + k);
            }
        }

        private class IntersectNode {
            private final int left1;
            private final int right1;
            private final int left2;
            private final int right2;
            private final int depth;
            private final int value;

            private IntersectNode(int left1, int right1, int left2, int right2, int depth, int value) {
                this.left1 = left1;
                this.right1 = right1;
                this.left2 = left2;
                this.right2 = right2;
                this.depth = depth;
                this.value = value;
            }
        }

        private class Node implements Comparable<Node> {
            private final int left;
            private final int right;
            private final int depth;
            private final int value;

            private Node(int left, int right, int depth, int value) {
                this.left = left;
                this.right = right;
                this.depth = depth;
                this.value = value;
            }

            @Override
            public int compareTo(Node other) {
                if (right - left != other.right - other.left) {
                    return -Integer.compare(right - left, other.right - other.left);
                }
                if (depth != other.depth) {
                    return Integer.compare(depth, other.depth);
                }
                if (value != other.value) {
                    return Integer.compare(value, other.value);
                }
                return 0;
            }
        }

        class RankResult {
            int rank;
            int rankLessThan;
            int rankMoreThan;

            private RankResult() {
            }

            @Override
            public String toString() {
                return String.format("[%d<%d<%d]", rankLessThan, rank, rankMoreThan);
            }
        }

        class IntersectResult {
            T value;
            int freq1;
            int freq2;

            private IntersectResult(T value, int freq1, int freq2) {
                this.value = value;
                this.freq1 = freq1;
                this.freq2 = freq2;
            }

            @Override
            public String toString() {
                return String.format("[%s:{%d, %d}]", value, freq1, freq2);
            }
        }
    }

    public static void main(String... args) {
        new Main().solve();
        out.flush();
    }
}

class In {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 0x10000);
    private StringTokenizer tokenizer;

    String next() {
        try {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        } catch (IOException ignored) {
        }
        return tokenizer.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    char[] nextCharArray() {
        return next().toCharArray();
    }

    String[] nextStringArray(int n) {
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = next();
        }
        return s;
    }

    char[][] nextCharGrid(int n, int m) {
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = next().toCharArray();
        }
        return a;
    }

    int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    int[] nextIntArray(int n, IntUnaryOperator op) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsInt(nextInt());
        }
        return a;
    }

    int[][] nextIntMatrix(int h, int w) {
        int[][] a = new int[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextIntArray(w);
        }
        return a;
    }

    long[] nextLongArray(int n) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextLong();
        }
        return a;
    }

    long[] nextLongArray(int n, LongUnaryOperator op) {
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = op.applyAsLong(nextLong());
        }
        return a;
    }

    long[][] nextLongMatrix(int h, int w) {
        long[][] a = new long[h][w];
        for (int i = 0; i < h; i++) {
            a[i] = nextLongArray(w);
        }
        return a;
    }

    List<List<Integer>> nextEdges(int n, int m, boolean directed) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            res.get(u).add(v);
            if (!directed) {
                res.get(v).add(u);
            }
        }
        return res;
    }
}

class Out {
    private final PrintWriter out = new PrintWriter(System.out);
    boolean autoFlush = false;

    void println(Object... args) {
        if (args == null || args.getClass() != Object[].class) {
            args = new Object[] {args};
        }
        out.println(Arrays.stream(args).map(obj -> {
            Class<?> clazz = obj == null ? null : obj.getClass();
            return clazz == Double.class ? String.format("%.8f", obj) :
                   clazz == byte[].class ? Arrays.toString((byte[])obj) :
                   clazz == short[].class ? Arrays.toString((short[])obj) :
                   clazz == int[].class ? Arrays.toString((int[])obj) :
                   clazz == long[].class ? Arrays.toString((long[])obj) :
                   clazz == char[].class ? Arrays.toString((char[])obj) :
                   clazz == float[].class ? Arrays.toString((float[])obj) :
                   clazz == double[].class ? Arrays.toString((double[])obj) :
                   clazz == boolean[].class ? Arrays.toString((boolean[])obj) :
                   obj instanceof Object[] ? Arrays.deepToString((Object[])obj) :
                   String.valueOf(obj);
        }).collect(Collectors.joining(" ")));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(char[] s) {
        out.println(String.valueOf(s));
        if (autoFlush) {
            out.flush();
        }
    }

    void println(int[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i : a) {
            joiner.add(Integer.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void println(long[] a) {
        StringJoiner joiner = new StringJoiner(" ");
        for (long i : a) {
            joiner.add(Long.toString(i));
        }
        out.println(joiner);
        if (autoFlush) {
            out.flush();
        }
    }

    void flush() {
        out.flush();
    }
}
