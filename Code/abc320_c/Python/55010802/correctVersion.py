import sys
sys.setrecursionlimit(10**6)
# sys.set_int_max_str_digits(10**6)

# mod = 998244353
# ds = [(-1,0),(0,1),(1,0),(0,-1)]
# inf = float('inf')
# ni,nj=i+di,j+dj
# 0<=ni<H and 0<=nj<W
# alph = 'abcdefghijklmnopqrstuvwxyz'
def rint(offset=0,base=10): return list(map(lambda x: int(x, base)+offset, input().split())) 
def full(s, f=int, *args): return [full(s[1:], f) if len(s) > 1 else f(*args) for _ in range(s[0])]
def shift(*args,offset=-1): return (a+offset for a in args)

M, = rint()
# S = []
S = [list(map(int, list(input()))) for _ in range(3)]
# S = input()
from collections import deque
import heapq

inf = float("inf")

def next_chance(i,t,d):
    for dt in range(M):
        if S[i][(t+dt)%M] == d:
            return dt
    return inf

def options(t,d, vis):
    best = inf
    best_i = []
    for i in range(3):
        if not vis[i]:
            score = next_chance(i,t,d)
            if score < best:
                best_i = [i]
                best = score
            elif score == best:
                best_i.append(i)
    return best, best_i


ans = inf
for d in range(10):
    vis = [False]*3

    def dfs(t,d):
        if all(vis):
            return -1
        dt, opts = options(t,d,vis)
        if dt == inf:
            return inf
        cost = inf
        for s in opts:
            if not vis[s]:
                vis[s] = True
                cost = min(cost, dfs(t+dt+1,d)+1)
                vis[s] = False
        return dt+cost
    
    ans = min(ans, dfs(0,d))

print(ans if ans != inf else -1)




# ans = False
# print("Yes" if ans else "No")