N=int(input())
S=list(input())
C=list(map(int,input().split()))
ans=1<<64
oi=[0]*(N+1)
io=[0]*(N+1)
oi_=[0]*(N+1)
io_=[0]*(N+1)
for i in range(N):
    oi[i+1]=oi[i]
    io[i+1]=io[i]
    oi_[N-i-1]=oi_[N-i]
    io_[N-i-1]=io_[N-i]
    if i%2==0:
        if S[i]=='1':
            oi[i+1]+=C[i]
        else:
            io[i+1]+=C[i]
    else:
        if S[i]=='0':
            oi[i+1]+=C[i]
        else:
            io[i+1]+=C[i]
    if (N-i-1)%2==0:
        if S[N-i-1]=='0':
            oi_[N-i-1]+=C[N-i-1]
        else:
            io_[N-i-1]+=C[N-i-1]
    else:
        if S[N-i-1]=='1':
            oi_[N-i-1]+=C[N-i-1]
        else:
            io_[N-i-1]+=C[N-i-1]
for i in range(1,N):
    ans=min(ans,oi[i]+oi_[i],io[i]+io_[i])
print(ans)