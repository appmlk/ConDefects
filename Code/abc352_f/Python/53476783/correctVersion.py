n, m = map(int, input().split())
G = [[] for _ in range(n)]
for _ in range(m):
    a, b, c = map(int, input().split())
    a -= 1
    b -= 1
    G[a].append((b, -c))
    G[b].append((a, c))

used = [False for _ in range(n)]
cost = [0 for _ in range(n)]
def dfs(u, d):
    res = [u]
    used[u] = True
    cost[u] = d
    for v, c in G[u]:
        if used[v]: continue
        res += dfs(v, d + c)
    return res

g = []
st = []
for i in range(n):
    if used[i]: continue
    comp = dfs(i, 0)
    m = min(cost[u] for u in comp)
    for u in comp:
        cost[u] -= m
    bit = 0
    for u in comp:
        bit |= (1 << cost[u])
    g.append(bit)
    st.append(comp)

bitset=[[]for _ in range(n+1)]
for bit in range(1<<n):
    cnt=bit.bit_count()
    bitset[cnt].append(bit)

ans=[-1]*n
for i in range(len(g)):
    now=0
    dp=[False]*(1<<n)
    dp[0]=True 
    x=g[i]
    for j in range(len(g)):
        if i==j:
            continue 
        for bit in bitset[now]:
            if dp[bit]==False:
                continue 
            for sft in range(n):
                if bit|(g[j]<<sft)>=(1<<n):
                    break
                dp[bit|(g[j]<<sft)]=True 
        now+=g[j].bit_count()
    # print(dp)
    ok=[]
    for bit in bitset[n-x.bit_count()]:
        if dp[bit]==False:
            continue 
        # print(x,bit)
        for sft in range(n):
            if x<<sft>=(1<<n):
                break 
            if bit^(x<<sft)==(1<<n)-1:
                ok.append(sft)
    if len(ok)==1:
        # print(ok)
        # print(st[i])
        for s in st[i]:
            ans[s]=cost[s]+ok[0]+1

print(*ans)