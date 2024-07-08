import sys
readline=sys.stdin.readline

def SCC(N,edges):
    start = [0] * (N + 1)
    elist = [0] * len(edges)
    for e in edges:
        start[e[0] + 1] += 1
    for i in range(1, N + 1):
        start[i] += start[i - 1]
    counter = start[:]
    for e in edges:
        elist[counter[e[0]]] = e[1]
        counter[e[0]] += 1
    N = N
    now_ord = group_num = 0
    visited = []
    low = [0] * N
    order = [-1] * N
    ids = [0] * N
    parent = [-1] * N
    stack = []
    for i in range(N):
        if order[i] == -1:
            stack.append(i)
            stack.append(i)
            while stack:
                v = stack.pop()
                if order[v] == -1:
                    low[v] = order[v] = now_ord
                    now_ord += 1
                    visited.append(v)
                    for i in range(start[v], start[v + 1]):
                        to = elist[i]
                        if order[to] == -1:
                            stack.append(to)
                            stack.append(to)
                            parent[to] = v
                        else:
                            low[v] = min(low[v], order[to])
                else:
                    if low[v] == order[v]:
                        while True:
                            u = visited.pop()
                            order[u] = N
                            ids[u] = group_num
                            if u == v:
                                break
                        group_num += 1
                    if parent[v] != -1:
                        low[parent[v]] = min(low[parent[v]], low[v])
    for i, x in enumerate(ids):
        ids[i] = group_num - 1 - x
    groups = [[] for _ in range(group_num)]
    for i, x in enumerate(ids):
        groups[x].append(i)
    return groups

class TwoSAT:
    def __init__(self,N=None):
        self.N=N
        self.edges=[]

    def Add_Clause(self,i,f,j,g):
        if self.N!=None:
            assert 0<=i<self.N
            assert 0<=j<self.N
        self.edges.append((2*i+(0 if f else 1),2*j+(1 if g else 0)))
        self.edges.append((2*j+(0 if g else 1),2*i+(1 if f else 0)))

    def Satisfiable(self):
        N=self.N if self.N!=None else max(max(i,j) for i,j in self.edges)//2+1
        scc=SCC(N*2,self.edges)
        idx=[None]*N*2
        for i,lst in enumerate(scc):
            for x in lst:
                idx[x]=i
        retu=[None]*N
        for i in range(N):
            if idx[2*i]==idx[2*i+1]:
                return None
            retu[i]=idx[2*i]<idx[2*i+1]
        return retu

N,M,Q=map(int,readline().split())
TSAT=TwoSAT(N*(M+2))
for n in range(N):
    for m in range(M+1):
        TSAT.Add_Clause(n*(M+2)+m,1,n*(M+2)+m+1,0)
    TSAT.Add_Clause(n*(M+2),1,n*(M+2),1)
    TSAT.Add_Clause(n*(M+2)+M,0,n*(M+2)+M,0)
for q in range(Q):
    A,B,L,R=map(int,readline().split())
    A-=1;B-=1
    for m in range(M+2):
        if 0<=L+1-m<=M+1:
            TSAT.Add_Clause(A*(M+2)+m,1,B*(M+2)+L+1-m,1)
        if 0<=R+1-m<=M+1:
            TSAT.Add_Clause(A*(M+2)+m,0,B*(M+2)+R+1-m,0)
lst=TSAT.Satisfiable()
if lst==None:
    print(-1)
else:
    ans_lst=[sum(lst[n*(M+2):(n+1)*(M+2)])-1 for n in range(N)]
    assert all(0<=ans<=M for ans in ans_lst)
    print(*ans_lst)