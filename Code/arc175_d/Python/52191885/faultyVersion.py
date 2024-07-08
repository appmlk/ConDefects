import sys
sys.setrecursionlimit(10**8)
input = sys.stdin.readline
N,K = map(int,input().split())
UV = [tuple(map(int,input().split())) for _ in range(N-1)]
es = [[] for _ in range(N)]
for u,v in UV:
    u,v = u-1,v-1
    es[u].append(v)
    es[v].append(u)

depth = [-1] * N
depth[0] = 0
size = [1] * N
def rec(v,p=-1):
    for to in es[v]:
        if to==p: continue
        depth[to] = depth[v] + 1
        size[v] += rec(to,v)
    return size[v]
rec(0)

if not N <= K <= N + sum(depth):
    exit(print('No'))
print('Yes')

size_order = list(range(N))
size_order.sort(key=lambda x: -size[x])

rem = K-N
st = set()
for v in size_order:
    if size[v] > rem: continue
    rem -= size[v]
    st.add(v)
    if rem == 0: break

X = [None] * N
for v in range(N):
    if v in st:
        X[v] = depth[v]
    else:
        X[v] = -depth[v]

x_order = list(range(N))
x_order.sort(key=lambda x: X[x])

now = 1
ans = [None] * N
for v in x_order:
    ans[v] = now
    now += 1
print(*ans)