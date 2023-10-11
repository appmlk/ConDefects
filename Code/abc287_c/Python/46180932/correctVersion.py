N, M = map(int, input().split())
raw_path = [list(map(lambda i: int(i) - 1, input().split())) for _ in range(M)]

class UnionFind:
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n
    
    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x]) # 経路圧縮
        return self.parents[x]
    
    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)
        
        if x == y:
            return
        
        if self.parents[x] > self.parents[y]: # ランクによる軽量化
            x, y = y, x
        
        self.parents[x] += self.parents[y] # 負の数が大きくなるほど、ノードの数が多いということ
        self.parents[y] = x

unionFind = UnionFind(N)
result_flag = True

counter = [0] * N

for p in raw_path:
    counter[p[0]] += 1
    counter[p[1]] += 1
    if unionFind.find(p[0]) == unionFind.find(p[1]):
        result_flag = False
        break
    unionFind.union(p[0], p[1])

count_parent = 0
for i in unionFind.parents:
    if i < 0:
        count_parent += 1
if count_parent == 1 and result_flag and (N == 2 or set(counter) == set([1, 2])):
    print("Yes")
else:
    print("No")
    