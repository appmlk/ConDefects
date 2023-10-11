N, M = map(int, input().split())

if M == 0:
    for i in range(N, 0, -1):
        print(i)
    exit()

from collections import defaultdict, deque
AB = [list(map(int, input().split())) for _ in range(M)]
for i in range(M):
    AB[i] = sorted(AB[i], reverse=True)
AB.sort(key = lambda x: (x[1], x[0]), reverse=True)
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n
 
    def find(self, x):
        #親を探す
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]
 
    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x == y:
            #親が同じ　何もしない
            return
        if self.parents[x] > self.parents[y]:
            x, y = y, x
        self.parents[x] += self.parents[y]
        self.parents[y] = x
 
    def size(self, x):
        # x が属しているグループの要素数
        return -self.parents[self.find(x)]
 
    def same(self, x, y):
        # 親が同じかどうか True or False
        return self.find(x) == self.find(y)
 
    def roots(self):
        # 根の要素を出力
        return [i for i, x in enumerate(self.parents) if x < 0]
    
    def group_count(self):
        # 連結している要素の数
        return len(self.roots())
 
    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]
    
    def all_group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

ans = [0] * N
g = UnionFind(N)
visit = set(range(N))
now = N - 1
leng = M
count = 0
while True:
    for i in range(count, leng):
        if AB[i][0] >= now + 1 and AB[i][1] >= now + 1:
            if now != N - 1 and not g.same(AB[i][0] - 1, AB[i][1] - 1):
                result -= 1
            g.union(AB[i][0] - 1, AB[i][1] - 1)
            visit.discard(AB[i][0] - 1)
            visit.discard(AB[i][1] - 1)
            count += 1
        else:
            break

    visit.discard(now)
    if now == N - 1:
        result = g.group_count()
    ans[now - 1] = result - len(visit)
    now -= 1
    if now == 0:
        break

for i in range(N):
    print(ans[i])
