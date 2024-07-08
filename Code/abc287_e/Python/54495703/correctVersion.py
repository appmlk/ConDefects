CHAR_SIZE = 26

class Trie:
    def __init__(self):
        self.isLeaf = False
        self.children = [None] * CHAR_SIZE
        self.cnt = 0
 
    def insert(self, key):
        curr = self
        for i in range(len(key)):
            index = ord(key[i]) - ord('a')
            if curr.children[index] is None:
                curr.children[index] = Trie()
            curr = curr.children[index]
            curr.cnt += 1
        curr.isLeaf = True

    def search(self, key):
        curr = self
        for c in key:
            index = ord(c) - ord('a')
            curr = curr.children[index]
            if curr is None:
                return False
        return curr.cnt
N = int(input())
trie = Trie()
S = [None]*N
for i in range(N):
    S[i] = input()
    trie.insert(S[i])

for i in range(N):
    l, r = 0, len(S[i])+1
    while r-l > 1:
        m = (l+r)//2
        if trie.search(S[i][:m]) > 1:
            l = m
        else:
            r = m
    print(l)
        