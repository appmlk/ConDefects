from functools import lru_cache

@lru_cache(maxsize=1000)
def f(a):
  return chr(a+97)
Q=int(input())
C=dict()
for _ in range(Q):
  T,P=input().split()
  P=int(P)
  C[T]=P
d=dict()
G=list()
M=26**3+26**2+1
L=[0]*M
N=26**3
for i in range(26):
  for j in range(26):
    for s in range(26):
      S=f(i)+f(j)+f(s)
      L[676*i+26*j+s]=S
      d[S]=676*i+26*j+s

for i in range(26):
  for j in range(26):
    S=f(i)+f(j)
    L[N+i*26+j]=S
    d[S]=N+i*26+j
    cost=0
    if f(i) in C:
      cost+=C[f(i)]
    if f(j) in C:
      cost+=C[f(j)]
    if S in C:
      cost+=C[S]
    G.append([M-1,N+i*26+j,-cost])
for i in range(N):
  s=L[i]
  a=d[s[1:]]
  b=d[s[:2]]
  cost=0
  if s in C:
    cost+=C[s]
  if s[1:] in C:
    cost+=C[s[1:]]
  if s[2] in C:
    cost+=C[s[2]]
  G.append([i,a,0])
  G.append([b,i,-cost])
inf=10**16

def bellman_ford(s=M-1):
    dd = [inf]*len(L) # 各頂点への最小コスト
    dd[s] = 0 # 自身への距離は0
    roop=N//26+10
    for i in range(roop):
        update = False # 更新が行われたか
        for x, y, z in G:
            if dd[y] > dd[x] + z:
                dd[y] = dd[x] + z
                update = True
        if not update:
            break
        # 負閉路が存在
        if i == roop - 1:
            print("Infinity")
            exit()
    return min(dd[:-1])
ans=-bellman_ford()
for i in range(26):
  if f(i) in C:
    ans=max(ans,C[f(i)])
print(ans)