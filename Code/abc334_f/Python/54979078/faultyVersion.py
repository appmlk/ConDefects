from atcoder.segtree import SegTree
N,K=map(int,input().split())
s=tuple(map(int,input().split()))
C=[tuple(map(int,input().split())) for _ in range(N)]
#from random import randint
#N,K=200000,200000
#s=(randint(-10**9,10**9),randint(-10**9,10**9))
#C=[(randint(-10**9,10**9),randint(-10**9,10**9)) for _ in range(N)]
def dist(p,q):
  return ((p[0]-q[0])**2+(p[1]-q[1])**2)**0.5
base=dist(s,C[0])+dist(C[N-1],s)
for i in range(N-1):base+=dist(C[i],C[i+1])
A=[0]+[dist(C[i],s)+dist(s,C[i+1])-dist(C[i],C[i+1]) for i in range(N-1)]+[0]
#dp[i]:iまで見て、A[i]を含むAの元を間隔K以下で選ぶ合計の最小値
#dp[i]=min(dp[i-1],...,dp[i-K])+A[i]
INF=10**12
dp=SegTree(min,INF,N+1)
dp.set(0,0)
for i in range(1,N+1):
  dp.set(i,dp.prod(max(0,i-K),i)+A[i])
print(dp.get(N)+base)