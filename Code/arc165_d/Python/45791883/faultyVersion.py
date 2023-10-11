import sys
input = sys.stdin.readline

class SCC:

    def __init__(self,n):
        self.n = n
        self.edges = []

    def csr(self):
        self.start = [0]*(self.n+1)
        self.elist = [0]*len(self.edges)
        for e in self.edges:
            self.start[e[0]+1] += 1
        for i in range(1,self.n+1):
            self.start[i] += self.start[i-1]
        counter = self.start[:]
        for e in self.edges:
            self.elist[counter[e[0]]] = e[1]
            counter[e[0]] += 1

    def add_edge(self,u,v):
        self.edges.append((u,v))

    def scc_ids(self):
        self.csr()
        n = self.n
        now_ord = group_num = 0
        visited = []
        low = [0]*n
        order = [-1]*n
        ids = [0]*n
        parent = [-1]*n
        stack = []
        for i in range(n):
            if order[i] == -1:
                stack.append(i)
                stack.append(i)
                while stack:
                    v = stack.pop()
                    if order[v] == -1:
                        low[v] = order[v] = now_ord
                        now_ord += 1
                        visited.append(v)
                        for i in range(self.start[v],self.start[v+1]):
                            to = self.elist[i]
                            if order[to] == -1:
                                stack.append(to)
                                stack.append(to)
                                parent[to] = v
                            else:
                                low[v] = min(low[v],order[to])
                    else:
                        if low[v] == order[v]:
                            while True:
                                u = visited.pop()
                                order[u] = n
                                ids[u] = group_num
                                if u == v:
                                    break
                            group_num += 1
                        if parent[v] != -1:
                            low[parent[v]] = min(low[parent[v]],low[v])
        for i,x in enumerate(ids):
            ids[i] = group_num-1-x

        return group_num,ids


    def scc(self):
        group_num,ids = self.scc_ids()
        groups = [[] for i in range(group_num)]
        for i,x in enumerate(ids):
            groups[x].append(i)

        return groups


class Unionfind:
     
    def __init__(self,n):
        self.uf = [-1]*n
 
    def find(self,x):
        if self.uf[x] < 0:
            return x
        else:
            self.uf[x] = self.find(self.uf[x])
            return self.uf[x]
 
    def same(self,x,y):
        return self.find(x) == self.find(y)
 
    def union(self,x,y):
        x = self.find(x)
        y = self.find(y)
        if x == y:
            return False
        if self.uf[x] > self.uf[y]:
            x,y = y,x
        self.uf[x] += self.uf[y]
        self.uf[y] = x
        return True
 
    def size(self,x):
        x = self.find(x)
        return -self.uf[x]

n,m = map(int,input().split())
ABCD = [[int(x)-1 for x in input().split()] for i in range(m)]
uf = Unionfind(n)
pos = [0]*m

while True:
    scc = SCC(n)
    upd = 0
    for i,(a,b,c,d) in enumerate(ABCD):
        si = min(b-a,d-c)+1
        if pos[i] >= si:
            continue
        
        ok = 0
        while pos[i] < si:
            if uf.same(a+pos[i],c+pos[i]):
                pos[i] += 1
                upd = 1
            else:
                scc.add_edge(a+pos[i],c+pos[i])
                ok = 1
                break

        if ok == 0 and b-a >= d-c:
            print("No")
            exit()
    
    grs = scc.scc()
    ok = 1
    for g in grs:
        if len(g) == 1:
            continue
        for x,nx in zip(g,g[1:]):
            uf.union(x,nx)
        ok = 0
        upd = 1

    if ok:
        print("Yes")
        exit()

    if upd == 0:
        break

print("No")