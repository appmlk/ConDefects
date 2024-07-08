import sys

# sys.setrecursionlimit(200005)
# sys.set_int_max_str_digits(200005)
int1 = lambda x: int(x)-1
pDB = lambda *x: print(*x, end="\n", file=sys.stderr)
p2D = lambda x: print(*x, sep="\n", end="\n\n", file=sys.stderr)
def II(): return int(sys.stdin.readline())
def LI(): return list(map(int, sys.stdin.readline().split()))
def LLI(rows_number): return [LI() for _ in range(rows_number)]
def LI1(): return list(map(int1, sys.stdin.readline().split()))
def LLI1(rows_number): return [LI1() for _ in range(rows_number)]
def SI(): return sys.stdin.readline().rstrip()

# dij = [(0, 1), (-1, 0), (0, -1), (1, 0)]
dij = [(0, 1), (-1, 0), (0, -1), (1, 0), (1, 1), (1, -1), (-1, 1), (-1, -1)]
inf = -1-(-1 << 31)
# inf = -1-(-1 << 62)

# md = 10**9+7
md = 998244353

def z_algorithm(target):
    len_t = len(target)
    lcp = [-1]*len_t
    top = 1
    right = 0
    lcp[0] = 0
    while top < len_t:
        while top+right < len_t and target[right] == target[top+right]:
            right += 1
        lcp[top] = right
        left = 1
        if right == 0:
            top += 1
            continue
        while left+lcp[left] < right and left < right:
            lcp[top+left] = lcp[left]
            left += 1
        top += left
        right -= left
    return lcp

n=II()
ss=[[ord(c) for c in SI()] for _ in range(n)]

cost=[[-1]*n for _ in range(n)]
rem=set()
for i in range(n):
    if i in rem:continue
    s=ss[i]
    for j in range(n):
        if i==j:continue
        if j in rem:continue
        t=ss[j]
        cost[i][j]=len(t)
        lcp=z_algorithm(t+[0]+s)
        for k in range(len(t)+1,len(lcp)):
            if lcp[k]==len(t):
                rem.add(j)
                break
            if k+lcp[k]==len(lcp):
                cost[i][j]=len(t)-lcp[k]
                break

rem=sorted(rem,reverse=True)
for i in rem:
    ss.pop(i)
    cost.pop(i)
    n-=1
for i in range(n):
    for j in rem:cost[i].pop(j)
# p2D(cost)

def chmin(i,j,val):
    if val<dp[i][j]:dp[i][j]=val

dp=[[inf]*n for _ in range(1<<n)]
for i in range(n):dp[1<<i][i]=len(ss[i])
for s in range(1,1<<n):
    for i in range(n):
        pre=dp[s][i]
        if pre==inf:continue
        for j in range(n):
            if s>>j&1:continue
            ns=s|1<<j
            chmin(ns,j,pre+cost[i][j])

print(min(dp[-1]))
