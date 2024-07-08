import sys,random,bisect
from collections import deque,defaultdict
from heapq import heapify,heappop,heappush
from itertools import permutations
from math import gcd,log

input = lambda :sys.stdin.readline().rstrip()
mi = lambda :map(int,input().split())
li = lambda :list(mi())

def cmb(n, r, mod):
    if ( r<0 or r>n ):
        return 0
    return (g1[n] * g2[r] % mod) * g2[n-r] % mod


mod = 998244353
N = 2*10**5
g1 = [1]*(N+1)
g2 = [1]*(N+1)
inverse = [1]*(N+1)

for i in range( 2, N + 1 ):
    g1[i]=( ( g1[i-1] * i ) % mod )
    inverse[i]=( ( -inverse[mod % i] * (mod//i) ) % mod )
    g2[i]=( (g2[i-1] * inverse[i]) % mod )
inverse[0]=0

def solve_fixed_sg(edges):
    
    N = 8
    in_deg = [0] * N
    out_deg = [0] * N
    for i in range(N):
        for j in range(N):
            in_deg[j] += edges[i][j]
            out_deg[i] += edges[i][j]
    
    vertex = [v for v in range(N) if in_deg[v]+out_deg[v]!=0]
    s,g = -1,-1
    for v in vertex:
        if in_deg[v] == out_deg[v]-1:
            if s!=-1:
                return 0
            else:
                s = v
        elif in_deg[v] == out_deg[v] + 1:
            if g!=-1:
                return 0
            else:
                g = v
        elif in_deg[v]!=out_deg[v]:
            return 0

    if s == -1 or g == -1:
        return 0

    
        
    edges[g][s] += 1
    in_deg[s] += 1
    out_deg[g] += 1
    assert all(in_deg[v] == out_deg[v] for v in vertex)

    deg_prod = 1
    for v in vertex:
        deg_prod = deg_prod * g1[out_deg[v]-1] % mod
    
    #print(in_deg)

    
    edge_set = [(i,j) for i in range(N) for j in range(N) if edges[i][j]]
    #print(edge_set)
    E = len(edge_set)
    n = len(vertex)

    res = 0

    #print(edge_set)

    
    

    

    for S in range(1<<E):
        tmp_edge = [[] for v in range(N)]
        deg = [0] * N
        tmp_prod = 1
        check = []
        for i in range(E):
            if S>>i & 1:
                u,v = edge_set[i]
                check.append((u,v))
                tmp_edge[v].append(u)
                deg[u] += 1
                tmp_prod = tmp_prod * edges[u][v] % mod
        
        
        if sum(deg)!=n-1 or tmp_prod == 0:
            continue

        visit = [False] * N
        st = [s]
        visit[s] = True
        while st:
            v = st.pop()
            for nv in tmp_edge[v]:
                if not visit[nv]:
                    visit[nv] = True
                    st.append(nv)
        
        if any(not visit[v] for v in vertex):
            continue

        #print("OK",tmp_prod)
        #print(s,check)
        
        res += tmp_prod * deg_prod % mod
        res %= mod
    
    for i in range(N):
        for j in range(N):
            if (i,j)!=(g,s):
                res *= g2[edges[i][j]]
                res %= mod
            else:
                res *= g2[edges[i][j]-1]
                res %= mod
    
    return res

def solve(A):
    N = 8
    edges = [[0]*N for i in range(N)]
    in_deg = [0] * N
    out_deg = [0] * N

    for S in range(16):
        u = S>>1
        v = S & 7
        if 1:
            edges[u][v] += A[S]
            in_deg[v] += A[S]
            out_deg[u] += A[S]
    
    vertex = [v for v in range(N) if in_deg[v]+out_deg[v]!=0]
    s,g = -1,-1
    for v in vertex:
        if in_deg[v] == out_deg[v]-1:
            if s!=-1:
                return 0
            else:
                s = v
        elif in_deg[v] == out_deg[v] + 1:
            if g!=-1:
                return 0
            else:
                g = v
        elif in_deg[v]!=out_deg[v]:
            return 0
    
    
    #print(s,g)
    if s!=-1 and g!=-1:
        return solve_fixed_sg(edges)

    if s!=-1 and g == -1:
        return 0
    if s == -1 and g!=-1:
        return 0
    
    self_loop_prod = 1
    for v in range(N):
        loop = edges[v][v]
        use_time = in_deg[v] - loop
        if use_time!=0:
            self_loop_prod *= cmb(loop+use_time-1,use_time-1,mod)
        elif loop != 0:
            self_loop_prod = 0
        
    
    
    res = 0
    for s in range(N):
        for g in range(N):
            tmp_edges = [[edges[i][j] if i!=j else 0 for j in range(N)] for i in range(N)]
            if edges[g][s] and s!=g:
                tmp_edges[g][s] -= 1
                self_loop_prod = 1
                for v in range(N):
                    if v!=s:
                        loop = edges[v][v]
                        use_time = in_deg[v] - loop
                    else:
                        loop = edges[v][v]
                        use_time = in_deg[v] - loop + 1
                    
                    if use_time!=0:
                        self_loop_prod *= cmb(use_time+loop-1,loop,mod)
                    elif loop!=0:
                        self_loop_prod = 0
                    self_loop_prod %= mod

                res += solve_fixed_sg(tmp_edges) * self_loop_prod % mod
                res %= mod
    return res % mod

A = li()
print(solve(A))









        
