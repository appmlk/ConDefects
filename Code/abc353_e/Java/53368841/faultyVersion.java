import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int n;

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int cnt = 0;
        int depth = 0;
        char ch;

        public long getValue() {
            return cnt * (cnt - 1) / 2;
        }

        public TrieNode(char ch) {
            this.ch = ch;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            this.cnt = 0;
            this.depth = 0;
        }

        public TrieNode getChild(char ch) {
            return children[ch - 'a'];
        }

        public TrieNode(int parentDepth, char ch) {
            this.depth = parentDepth + 1;
            this.ch = ch;
            this.cnt = 1;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }

        public void addChild(TrieNode child) {
            this.children[child.ch - 'a'] = child;
        }
    }


    static TrieNode dummy = new TrieNode('a');

    public static void build(String str) {
        TrieNode curr = dummy;
        for (int i = 0; i < str.length(); i++) {
            int parentDepth = curr.depth;
            char ch1 = str.charAt(i);

            TrieNode child = curr.getChild(ch1);

            if (child != null) {
                child.cnt++;
            } else {
                child = new TrieNode(parentDepth, ch1);
                curr.addChild(child);
            }
            curr = child;
        }
    }

    public static long travel() {
        ArrayDeque<TrieNode> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(dummy);

        long ans = 0;
        while (!arrayDeque.isEmpty()) {
            TrieNode node = arrayDeque.poll();
            long val1 = node.getValue();
            long val2 = 0;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    val2 += node.children[i].getValue();
                    arrayDeque.add(node.children[i]);
                }
            }
            ans += (val1 - val2) * node.depth;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        n = rd.nextInt();
        String[] str = rd.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            build(str[i]);
        }
        long ans = travel();
        System.out.println(ans);
    }

    static class rd {
        static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokenizer = new StringTokenizer("");

        // nextLine()读取字符串
        static String nextLine() throws IOException {
            return reader.readLine();
        }

        // next()读取字符串
        static String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) tokenizer = new StringTokenizer(reader.readLine());
            return tokenizer.nextToken();
        }

        // 读取一个int型数值
        static int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        // 读取一个double型数值
        static double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        // 读取一个long型数值
        static long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        // 读取一个BigInteger
        static BigInteger nextBigInteger() throws IOException {
            BigInteger d = new BigInteger(rd.nextLine());
            return d;
        }
    }
}