N = int(input())
m = [0]*N
p = [*map(int,input().split())]
for n in range(N):
    m[(p[n]-n)%N]+=1
mid = N//2+N%2
fnsum,lnsum = sum(m[n] for n in range(mid)),sum(m[n] for n in range(mid,N))
fsum,lsum = sum(m[n]*n for n in range(mid)),sum(m[n]*(N-n) for n in range(mid,N))
ans = fsum+lsum
for n in range(N-1):
    fsum = fsum - fnsum + m[(n+mid)%N]*(mid-1) + m[n]
    fnsum = fnsum + m[(n+mid)%N] - m[n]
    lsum = lsum + lnsum + m[n] - m[(n+mid)%N]*(N-mid+1)
    lnsum = lnsum + m[n] - m[(n+mid)%N]
    ans = min(ans,fsum+lsum)
print(ans)