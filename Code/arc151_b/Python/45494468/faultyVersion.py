class UnionFind:
    def __init__(self, n):
        self.par = [-1]*n
        self.rank = [0]*n
        self.size = [1]*n
    
    def root(self,x):
        if self.par[x]==-1:
            return x
        else:
            stack=[]
            while self.par[x]!=-1:
                stack.append(x)
                x=self.par[x]
            for y in stack:
                self.par[y]=x
            return x
        
    def unite(self, x, y):
        rx=self.root(x)
        ry=self.root(y)
        if rx==ry:
            return False
        if self.rank[y]>self.rank[x]:
            rx,ry=ry,rx
        self.par[ry]=rx
        if self.rank[rx] == self.rank[ry]: # rx 側の rank を調整する
            self.rank[rx] += 1
        self.size[rx] += self.size[ry] # rx 側の siz を調整する
        return True
    
    def getsize(self, x):
        return self.size[self.root(x)]
        
    def issame(self, x, y):
        if self.root(x)==self.root(y): return True
        else: return False

N,M=map(int,input().split())
P=[0]+list(map(int,input().split()))
unf=UnionFind(N+1)
MOD=998244353
for i in range(1,N+1):
    unf.unite(i,P[i])

se=set()
for i in range(1,N+1):
    se.add(unf.root(i))

print(((pow(M,N,MOD)-pow(M,len(se),MOD))//2)%MOD)
    
    