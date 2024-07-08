import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(input.readLine());

        Trie trie = new Trie();

        st = new StringTokenizer(input.readLine());

        for (int i = 0; i < N; i++) {
            String inputStr = st.nextToken();
            trie.insert(inputStr);
        }

        System.out.println(trie.res);
    }
}

class Trie {

    int res;

    private TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode();
    }

    public void insert(String inputStr) {
        TrieNode curNode = rootNode;

        for (int i = 0; i < inputStr.length(); i++) {
            int idx = inputStr.charAt(i) - 'a';

            if (curNode.childNodes[idx] == null) {
                curNode.childNodes[idx] = this.makeNode();
            }
            curNode = curNode.childNodes[idx];
            res += curNode.cnt;
            curNode.cnt++;
        }
    }

    private TrieNode makeNode() {
        return new TrieNode();
    }


}

class TrieNode {
    TrieNode[] childNodes;
    int cnt;

    public TrieNode() {
        childNodes = new TrieNode[26];
        Arrays.fill(childNodes, null);
    }


}
