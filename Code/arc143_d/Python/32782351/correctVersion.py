import sys
readline = sys.stdin.readline

#n = int(readline())
n,m = map(int,readline().split())
*a, = map(lambda x:int(x)-1,readline().split())
*b, = map(lambda x:int(x)-1,readline().split())

g = [[] for _ in range(n)]
for i in range(m):
    ai = a[i]
    bi = b[i]
    g[ai].append((bi,i))
    g[bi].append((ai,i))

def dfs(i):
    st = [(i,-1)]
    while st:
        #print(st)
        v,idx = st.pop()
        #print(v,idx)
        if used[v]:
            #print(idx)
            if a[idx] == v:
                ans[idx] = 1
            continue
        if idx != -1 and b[idx] == v:
            ans[idx] = 1
        used[v] = 1
        for c,idx in g[v]:
            if used[c] == 0:
                st.append((c,idx))
                parent[c] = v


ans = [0]*m
used = [0]*n
parent = [0]*n
for i in range(n):
    if used[i]: continue
    dfs(i)


r = "".join(map(str,ans))
print(r)









