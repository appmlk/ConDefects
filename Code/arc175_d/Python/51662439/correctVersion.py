import sys
readline = sys.stdin.readline

n,K = map(int,readline().split())
g = [[] for _ in range(n)]

for _ in range(n-1):
    u,v = map(int,readline().split())
    g[u-1].append(v-1)
    g[v-1].append(u-1)

order = []
st = [0]
parent = [-1]*n
size = [1]*n
while st:
    v = st.pop()
    order.append(v)
    for c in g[v]:
        if c != parent[v]:
            st.append(c)
            parent[c] = v

for i in order[::-1]:
    if i:
        size[parent[i]] += size[i]

K -= n
res = [0]*n
for i in order[1:]:
    p = parent[i]
    if size[i] <= K:
        K -= size[i]
        res[i] = 1

#print(res)

L = 1
M = n
ans = [0]*n
for i in order[::-1]:
    if res[i]:
        ans[i] = M
        M -= 1
    else:
        ans[i] = L
        L += 1

if K == 0:
    print("Yes")
    print(*ans)
else:
    print("No")
    
    
    