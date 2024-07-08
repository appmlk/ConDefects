N = int(input())
P = [int(i) for i in input().split()]
Q = int(input())
queries = [[int(i) for i in input().split()] for _ in range(Q)]
# print(queries)

from collections import defaultdict
class UnionFind():
    def __init__(self,n):
        self.n=n
        self.parents=[-1]*n

    def find(self,x):
        if self.parents[x]<0:
            return x
        else:
            self.parents[x]=self.find(self.parents[x])
            return self.parents[x]

    def union(self,x,y):
        x=self.find(x)
        y=self.find(y)
        if x==y:
            return
        if self.parents[x]>self.parents[y]:
            x,y=y,x
        self.parents[x]+=self.parents[y]
        self.parents[y]=x

    def size(self,x):
        return -self.parents[self.find(x)]

    def same(self,x,y):
        return self.find(x)==self.find(y)

    def members(self,x):
        root=self.find(x)
        return [i for i in range(self.n) if self.find(i)==root]

    def roots(self):
        return [i for i,x in enumerate(self.parents) if x<0]

    def group_count(self):
        return len(self.roots())

    def all_group_members(self):
        group_members=defaultdict(list)
        for member in range(self.n):
            group_members[self.find(member)].append(member)
        return group_members

    def __str__(self):
        return '\n'.join(f'{r}: {m}' for r,m in self.all_group_members().items())

uf = UnionFind(N)
G = [set() for _ in range(N)]
rG = [-1 for _ in range(N)]

for i,p in enumerate(P):
    p-=1
    G[p].add(i+1)
    rG[i+1] = p
    
# print(G,rG)
ans = [i for i in range(N)]

# print(rG)
for q in queries:
    if q[0]==1:
        u,v = q[1:]
        u-=1; v-=1
        if uf.same(u,v):
            continue
        cu = u
        v = ans[uf.find(v)]
        # cnt = 0
        while cu!=v:
            # cnt+=1
            # if cnt>10:
            #     raise Exception
            # print('cu', cu)
            uf.union(cu, v)
            ans[uf.find(cu)]=v
            cu = ans[uf.find(rG[cu])]
        par = uf.find(u)
        ans[par] = v
        
    else:
        x = q[1]-1
        par = uf.find(x)
        print(ans[par]+1)