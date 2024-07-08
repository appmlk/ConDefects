N = int(input())
G = [[] for _ in range(N)]
for _ in range(N - 1):
    u,v = map(int,input().split())
    u -= 1
    v -= 1
    G[u].append(v)
    G[v].append(u)
A = list(map(int,input().split()))
from collections import defaultdict

S = [defaultdict(int) for _ in range(N)]
num = [defaultdict(int) for _ in range(N)]
child = [1] * N
hight = [0] * N

stack = [(0,-1),(~0,-1)]
ans = 0

while stack:
    now,parent = stack.pop()
    if now < 0:
        now = ~now
        for v in G[now]:
            if v != parent:
                stack.append((v,now))
                stack.append((~v,now))
        continue
    a = A[now]
    l = []
    for v in G[now]:
        if v != parent:l.append(v)
    if len(l) == 0:
        S[now][a] = 0
        num[now][a] = 1
        continue
    v = l[0]
    ans += S[v][a] + num[v][a] * (hight[v] + 1)
    S[now] = S[v]
    num[now] = num[v]
    num[now][a] += 1
    hight[now] = hight[v] + 1
    S[now][a] -= hight[now]
    child[now] += child[v]
    

    for v in l[1:]:
        if child[now] >= child[v]:
            for b in S[v]:
                ans += (S[now][b] + num[now][b] * hight[now]) * num[v][b] + (S[v][b] + num[v][b] * (hight[v] + 1)) * num[now][b]
                num[now][b] += num[v][b]
                S[now][b] += S[v][b] + num[v][b] * (hight[v] + 1) - num[v][b] * hight[now]
        else:
            for b in S[now]:
                ans += (S[now][b] + num[now][b] * hight[now]) * num[v][b] + (S[v][b] + num[v][b] * (hight[v] + 1)) * num[now][b]
                num[v][b] += num[now][b]
                S[v][b] += S[now][b] + num[now][b] * hight[now] - num[now][b] * (hight[v] + 1)
            S[now] = S[v]
            hight[now] = hight[v] + 1
            num[now] = num[v]
        child[now] += child[v]
print(ans)
