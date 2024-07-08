import collections,sys,math,functools,operator,itertools,bisect,heapq,decimal,string,time,random
#sys.setrecursionlimit(10**9)
#sys.set_int_max_str_digits(0
class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = [-1] * n

    def find(self, x):
        if self.parents[x] < 0:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if x == y:
            return

        if self.parents[x] > self.parents[y]:
            x, y = y, x

        self.parents[x] += self.parents[y]
        self.parents[y] = x

    def size(self, x):
        return -self.parents[self.find(x)]

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def members(self, x):
        root = self.find(x)
        return [i for i in range(self.n) if self.find(i) == root]

    def roots(self):
        return [i for i, x in enumerate(self.parents) if x < 0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members = collections.defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return ''.join(f'{r}: {m}' for r, m in self.all_group_members().items())
input = sys.stdin.readline
#n = int(input())
#alist = list(map(int,input().split()))
#alist = []
#s = input()
n,m,k = map(int,input().split())
if k % 2 == 1:
    exit(print('No'))
edge = [[] for i in range(n)]
uf = UnionFind(n)
c = collections.defaultdict(int)
for i in range(m):
    u,v = map(int,input().split())
    u-=1
    v-=1
    if uf.same(u,v) == True:
        continue
    uf.union(u,v)
    c[(u,v)] = i

    edge[u].append(v)
    edge[v].append(u)
#for i in range(n):
#    alist.append(list(map(int,input().split())))
ans = []
lamp = [0 for i in range(n)]
l = 0
p = [-1 for i in range(n)]
for i in range(n):
    if i != uf.find(i):
        continue
    d = collections.deque()
    d.append(~i)
    d.append(i)
    if l == k:
        break
    while d:
        if l == k:
            break
        now = d.pop()
        if now < 0:
            now = ~now
            if now == i:
                continue
            if lamp[now] == 0:
                if l < k:
                    l -= lamp[now] + lamp[p[now]]
                    lamp[now] ^= 1
                    lamp[p[now]] ^= 1
                    l += lamp[now] + lamp[p[now]]
                    ans.append((now,p[now]))
        else:
            for j in edge[now]:
                if j == p[now]:
                    continue
                p[j] = now
                d.append(~j)
                d.append(j)
if l != k:
    print('No')
else:
    assert sum(lamp) == k
    print('Yes')
    print(len(ans))
    for i,j in ans:
        print(c[min(i,j),max(i,j)]+1,end=' ')