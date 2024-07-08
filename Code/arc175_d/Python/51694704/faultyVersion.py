import sys
input = sys.stdin.readline

from operator import itemgetter
from collections import Counter

N,K=map(int,input().split())

E=[[] for i in range(N)]

for i in range(N-1):
    u,v=map(int,input().split())
    u-=1
    v-=1
    E[u].append(v)
    E[v].append(u)

# 木のHL分解+LCA

ROOT=0

QUE=[ROOT] 
Parent=[-1]*N
Parent[ROOT]=N # ROOTの親を定めておく.
Child=[[] for i in range(N)]
TOP_SORT=[] # トポロジカルソート

while QUE: # トポロジカルソートと同時に親を見つける
    x=QUE.pop()
    TOP_SORT.append(x)
    for to in E[x]:
        if Parent[to]==-1:
            Parent[to]=x
            Child[x].append(to)
            QUE.append(to)

Children=[1]*N

for x in TOP_SORT[::-1]: #（自分を含む）子ノードの数を調べる
    if x==ROOT:
        break
    Children[Parent[x]]+=Children[x]

C=[]

for i in range(N):
    C.append([Children[i],i])

C.sort(key=itemgetter(0),reverse=True)

ANS2=[1]*N

K-=N

for cx,c in C:
    if c==0:
        continue
    if K>=cx:
        ANS2[c]=ANS2[Parent[c]]+1
        K-=cx
    else:
        ANS2[c]=ANS2[Parent[c]]

CS=Counter(ANS2)
S=[0]*(N+1)

now=0
for i in range(N+1):
    now+=CS[i]
    S[i]=now

ANS=[0]*N
#print(ANS,S)
for cx,c in C:
    ANS[c]=S[ANS2[c]]
    S[ANS2[c]]-=1

if K==0:
    print("Yes")
    print(*ANS)
