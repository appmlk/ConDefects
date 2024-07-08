import sys; sys.setrecursionlimit(10**6); import pypyjit; pypyjit.set_param('max_unroll_recursion=-1')

class DubTree:
    def __init__(self,N):
        self.N, self.K = N, 1
        while (1<<self.K)<N : self.K += 1
        self.node,self.wei = [None]*self.N, [None]*self.N
        self.edge = [[] for n in range(self.N)]
        self.parent = [[None]*N for k in range(self.K)]
        self.euler = []
        self.eunum = [[] for n in range(N)]
        self.tree = []
    def append_edge(self,a,b,w=1):
        self.edge[a].append((b,w)); self.edge[b].append((a,w))
    def dfs(self,s):
        stack, self.node[s],self.wei[s] = [s], 0, 0
        self.parent[0][s] = None
        self._dfs(s)
        self.tree = [0]*(len(self.euler)+1)
    def _dfs(self,pos):
        self.euler.append(pos)
        self.eunum[pos].append(len(self.euler))
        for p,w in self.edge[pos]:
            if self.node[p] is None:
                self.node[p], self.wei[p] = self.node[pos]+1, self.wei[pos]+w
                self.parent[0][p] = pos
                self._dfs(p)
                self.euler.append(pos)
                self.eunum[pos].append(len(self.euler))
    def doubling(self):
        for k in range(self.K-1):
            for n in range(self.N):
                if self.parent[k][n] is None : self.parent[k+1][n] = None
                else : self.parent[k+1][n] = self.parent[k][self.parent[k][n]]
    def lca(self,a,b):
        if self.node[a]<self.node[b] : a,b=b,a
        for k in range(self.K-1,-1,-1):
            if self.node[a]-self.node[b]>=(1<<k) : a = self.parent[k][a]
        if a==b : return a
        for k in range(self.K-1,-1,-1):
            if self.parent[k][a]!=self.parent[k][b] : 
                a,b = self.parent[k][a],self.parent[k][b]
        return self.parent[0][a]
    def dist(self,a,b):
        lca = self.lca(a,b)
        alen = self.wei[a] + self._sum(self.eunum[a][0]) - self.wei[lca] - self._sum(self.eunum[lca][0])
        blen = self.wei[b] + self._sum(self.eunum[b][0]) - self.wei[lca] - self._sum(self.eunum[lca][0])
        return alen + blen
    def add(self,a,i):
        n = self.eunum[a][0]
        while n<=len(self.tree)-1:
            self.tree[n]+=i
            n += n&-n
        n = self.eunum[a][-1]+1
        while n<=len(self.tree)-1:
            self.tree[n]-=i
            n += n&-n
        
    def _sum(self,n):
        ans = 0
        while n>0:
            ans += self.tree[n]
            n -= n&-n
        return ans


N = int(input())
dt = DubTree(N)
edge = [None for n in range(N-1)]
wei = [0 for n in range(N-1)]
for n in range(N-1):
    a,b,w = map(int,input().split())
    dt.append_edge(a-1,b-1,w)
    edge[n] = (a-1,b-1)
    wei[n] = w
dt.dfs(0)
dt.doubling()
for _ in range(int(input())):
    q,x,y = map(int,input().split())
    if q==1:
        a,b = edge[x-1]
        if dt.parent[0][b]==a : a,b=b,a
        dt.add(a,y-wei[x-1])
        wei[x-1]=y
    else:
        print(dt.dist(x-1,y-1))