n, m = map(int, input().split())
A, B, C = map(lambda x : int(x) - 1, input().split())
G = [[] for _ in range(n)]
for _ in range(m) :
  u, v = map(lambda x : int(x) - 1, input().split())
  G[u].append(v); G[v].append(u)
dfn, low = [-1] * n, [-1] * n
tot = 0
bcc = []
stk_DFS = [(-1, A, 0)]
stk_v = []
while len(stk_DFS) > 0:
  f, u, vid = stk_DFS.pop()
  if vid == 0 :
    stk_v.append(u)
    dfn[u] = low[u] = tot
    tot += 1
  if vid < len(G[u]) and G[u][vid] == f : vid += 1
  if vid >= len(G[u]) :
    if f == -1 : continue
    low[f] = min(low[f], low[u])
    if low[u] < dfn[f]: continue
    else:
      nbcc = []
      while len(stk_v) > 0 and stk_v[-1] != u :
        nbcc.append(stk_v.pop())
      nbcc.append(stk_v.pop())
      nbcc.append(f)
      bcc.append(nbcc)
  else:
    v = G[u][vid]
    stk_DFS.append((f, u, vid + 1))
    if dfn[v] == -1:
      stk_DFS.append((u, v, 0))
    else:
      low[u] = min(low[u], low[v])
        
old_n = n
bcc_cnt = len(bcc)
nG = [[] for _ in range(n + bcc_cnt)]
for b in bcc:
  for v in b:
    nG[v].append(n)
    nG[n].append(v)
  n += 1

fa = [-1] * n
ndfn, nbot = [-1] * n, [-1] * n
tot = 0
stk_DFS = [(-1, A, 0)]
reach = [-1] * n
while len(stk_DFS) > 0:
  f, u, vid = stk_DFS.pop()
  if vid == 0:
    reach[u] = 1
    ndfn[u] = tot
    tot += 1 
  if vid < len(nG[u]) and nG[u][vid] == f : vid += 1
  if vid >= len(nG[u]):
    nbot[u] = tot - 1
  else:
    v = nG[u][vid]
    fa[v] = u
    stk_DFS.append((f, u, vid + 1))
    stk_DFS.append((u, v, 0))
    
q = 1
ans = [0] * q
if reach[B] and reach[C] and ndfn[C] >= ndfn[fa[B]] \
  and ndfn[C] <= nbot[fa[B]] : ans[0] = 1
else : ans[0] = 0

for i in range(q):
  print('Yes' if ans[i] else 'No')
