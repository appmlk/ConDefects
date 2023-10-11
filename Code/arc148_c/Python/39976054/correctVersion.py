from collections import defaultdict

class UnionFind():

    def __init__(self, n):
        self.n = n
        self.root = [-1]*(n+1)
        self.rank = [0]*(n+1)

    def find(self, x):
        if(self.root[x] < 0):
            return x
        else:
            self.root[x] = self.find(self.root[x])
            return self.root[x]

    def unite(self, x, y):
        x = self.find(x)
        y = self.find(y)

        if(x == y):
            return
        elif(self.rank[x] > self.rank[y]):
            self.root[x] += self.root[y]
            self.root[y] = x
        else:
            self.root[y] += self.root[x]
            self.root[x] = y
            if(self.rank[x] == self.rank[y]):
                self.rank[y] += 1

    def same(self, x, y):
        return self.find(x) == self.find(y)

    def size(self, x):
        return -self.root[self.find(x)]

    def roots(self):
        return [i for i, x in enumerate(self.root) if x < 0]

    def group_size(self):
        return len(self.roots())

    def group_members(self):
        group_members = defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members
        
N,Q=map(int,input().split())
P=list(map(int,input().split()))
con=[0 for _ in range(N+1)]
for i in range(N-1):
    con[P[i]]+=1
    con[i+2]+=1
for _ in range(Q):
    q=list(map(int,input().split()))
    uf=UnionFind(q[0])
    dic={}
    ans=0
    for i in range(1,q[0]+1):
        dic.setdefault(q[i],i)
        ans+=con[q[i]]
    for i in range(1,q[0]+1):
        if q[i]==1:
            continue
        if dic.get(P[q[i]-2])!=None:
            uf.unite(dic[P[q[i]-2]],i)
    add=0
    if 1 in q[1:]:
        add=1
    print(ans-(q[0]-len(uf.roots())+1)*2+add)
    