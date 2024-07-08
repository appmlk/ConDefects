from collections import defaultdict, deque, Counter
from itertools import combinations, permutations, product, accumulate
from heapq import heapify, heappop, heappush
import math
import bisect
import sys
# sys.setrecursionlimit(700000)
input = lambda: sys.stdin.readline().rstrip('\n')
inf = float('inf')
mod1 = 10**9+7
mod2 = 998244353
def ceil_div(x, y): return -(-x//y)

#################################################

# ABC004-D

from heapq import heapify, heappop, heappush

class Flow:
    def __init__(self, n, inf=float("inf")):
        self.V = n
        self.inf = inf
        self.G = [[] for _ in range(n)]
        self.H = [] #ポテンシャル
        self.pre = [] # dijkstra経路復元用
    
    def add_edge(self, from_, to, cap, cost):
        #cost(1つ目):負辺除去後コスト, cost(2つ目):元コスト
        self.G[from_].append([to, cap, len(self.G[to]), cost, cost])
        self.G[to].append([from_, 0, len(self.G[from_])-1, -cost, -cost])
    
    def dijkstra(self, s):
        self.pre = [None]*self.V
        hq = [(0, s)]
        self.H[s] = 0
        while hq:
            d, now = heappop(hq)
            if d > self.H[now]:
                continue
            for i in range(len(self.G[now])):
                next, cap, r, cost, c = self.G[now][i]
                if cap > 0 and self.H[now]+cost < self.H[next]:
                    self.H[next] = self.H[now]+cost
                    self.pre[next] = (now, i)
                    heappush(hq, (self.H[next], next))
    
    def rest(self, s, t, flow):
        now = t
        while now != s and self.pre[now] is not None:
            p, i = self.pre[now]
            flow = min(flow, self.G[p][i][1])
            now = p
        if flow == 0 or now != s:
            return 0, 0
        now = t
        ret = 0
        while now != s:
            p, i = self.pre[now]
            next, cap, rev, c, cost = self.G[p][i]
            self.G[p][i][1] -= flow
            self.G[now][rev][1] += flow
            ret += cost*flow
            now = p
        return flow, ret
    
    def minimum_cost_flow(self, s, t, F, x):
        flow = 0
        ans = x
        while F-flow > 0:
            self.H = [self.inf]*self.V
            self.dijkstra(s)
            for i in range(self.V):
                for j in range(len(self.G[i])):
                    self.G[i][j][3] += self.H[i]-self.H[self.G[i][j][0]]
            f, a = self.rest(s, t, F-flow)
            if f == 0:
                return None
            ans += a
            flow += f
        return ans

N = int(input())
g = Flow(302)
s, t = 300, 301
for i in range(150):
    g.add_edge(s, i, 1, 0)
    g.add_edge(i+150, t, 1, 0)

INF = 0
E = []
for _ in range(N):
    a, b, c = map(int, input().split())
    a -= 1; b -= 1
    INF += c
    E.append((a, b, c))
for a, b, c in E:
    g.add_edge(a, b+150, 1, INF-c)
ans = [0]
for i in range(1, N+1):
    c = g.minimum_cost_flow(s, t, 1, -(ans[-1]-INF*(i-1)))
    if c is None: break
    ans.append(INF*i-c)
print(len(ans)-1)
print(*ans[1:], sep="\n")