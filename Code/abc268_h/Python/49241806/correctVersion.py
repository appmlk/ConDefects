from collections import deque

class Trie_with_Aho_Corasick():
    def __init__(self, keywords, sigma = 26):
        self.keywords = keywords
        self.sigma = sigma
        self.tree = [[-1] * (sigma + 2) for _ in range(sum(len(_str) for _str in keywords) + 1)]
        self.depth = [0] * len(self.tree)
        self.num = 1
        ### _build_trie ###
        for i, word in enumerate(keywords):
            self._register(i, word)
        self.init_failure()

    def _register(self, i, word):
        pos = 0
        for c in word:
            c = ord(c) - ord_a
            if self.tree[pos][c] == -1:
                self.tree[pos][c] = self.num
                self.depth[self.num] = self.depth[pos] + 1
                self.num += 1
            pos = self.tree[pos][c]
        self.tree[pos][-1] = i

    def init_failure(self):
        self.tree[0][-2] = 0
        que = deque([0])
        while que:
            v = que.popleft()
            f = self.tree[v][-2]
            if self.tree[f][-1] != -1:
                self.tree[v][-1] = self.tree[f][-1]
            for i in range(self.sigma):
                if self.tree[v][i] == -1:
                    f = self.tree[v][-2]
                    if self.tree[f][i] == -1:
                        self.tree[v][i] = 0
                    else:
                        self.tree[v][i] = self.tree[f][i]
                else:
                    c = self.tree[v][i]
                    f = self.tree[v][-2]
                    if self.tree[f][i] == c:
                        self.tree[c][-2] = 0
                    else:
                        self.tree[c][-2] = self.tree[f][i]
                    que.append(c)

    def match(self, s):
        res = [0] * len(s)
        pos = 0
        for i in range(len(s)):
            c = ord(s[i]) - ord_a
            res[i] = pos = self.tree[pos][c]
        return res

s = str(input())
n = int(input())
t = [str(input()) for _ in range(n)]

ord_a = ord('a')
aho = Trie_with_Aho_Corasick(t)
ans = 0
m = [aho.tree[i][-1] for i in aho.match(s)]
l = -1
for i in range(len(s)):
    if m[i] == -1:
        continue
    x = len(t[m[i]])
    if l < i - x + 1:
        l = i
        ans += 1
print(ans)
