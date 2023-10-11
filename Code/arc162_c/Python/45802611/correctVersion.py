import sys
sys.setrecursionlimit((1<<19)-1)

def dfs(pos):
    global ans
    cnt=0
    less=set()
    for i in tree[pos]:
        tocnt,toless=dfs(i)
        cnt+=tocnt
        less=less|toless
    if A[pos]==-1:
        cnt+=1
    elif A[pos]<=K:
        less.add(A[pos])
    if K not in less and len(less)>=K-cnt and cnt<=1:
        ans="Alice"
    return cnt,less

T=int(input())
for _ in range(T):
    N,K=map(int,input().split())
    P=list(map(int,input().split()))
    A=list(map(int,input().split()))
    ans="Bob"
    tree=[set() for i in range(N)]
    for i in range(N-1):
        tree[P[i]-1].add(i+1)
    dfs(0)
    print(ans)