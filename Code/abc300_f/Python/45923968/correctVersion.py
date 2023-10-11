N,M,K=list(map(int, input().split()))
S=input()

Sa=[0]
for i in range(N):
    a=0
    if S[i]=="x":
        a=1
    Sa.append(Sa[-1]+a)
t=Sa[-1]
ans=0
for i in range(N):
    ok=-1
    ng=M*N+1
    while abs(ok-ng)>1:
        c=(ok+ng)//2
        total=0
        total+=(c//N)*t
        total+=Sa[(c%N)]
        total-=Sa[i]
        if total<=K:
            ok=c
        else:
            ng=c
    ans=max(ans,ok-i)
print(ans)