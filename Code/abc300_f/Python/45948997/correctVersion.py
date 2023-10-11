def II() : return int(input())
def MI() : return map(int,input().split())
def MS() : return map(str,input().split())
def LMI() : return list(MI())
def LMS() : return list(MS())
def LLI(N) : return [LMI() for _ in range(N)]
def LLS(N): return [LMS() for _ in range(N)]
def LS(N) : return [input() for _ in range(N)]
def LI(N) : return [II() for _ in range(N)]
#入力
def ruiseki(i):
    if i<=N:
        return a[i]
    return a[-1]*((i-1)//N)+a[(i-1)%N+1]
def solve(x,i):
    if x>N*M:
        return 0
    if ruiseki(x)-ruiseki(i)<=K:
        return 1
    return 0
N,M,K=MI()
S=input()
a=[0]*(N+1)
for i in range(N):
    if S[i]=='x':
        a[i+1]+=1
for i in range(1,N+1):
    a[i]+=a[i-1]
ans=0
for i in range(N):
    ok=i
    ng=10**15
    while abs(ok-ng)>1:
        mid=(ok+ng)//2
        if solve(mid,i):
            ok=mid
        else:
            ng=mid
    ans=max(ok-i,ans)
print(ans)
