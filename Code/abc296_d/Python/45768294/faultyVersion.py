N,M = map(int,input().split())

if N**2 < M:
    print(-1)
    exit()
ans = 10**100

for i in range(1,int(M**0.5)+10):
    if M%i == 0 and M//i <= N and i<=N:
        ans = min(ans,M)
        continue
    elif i * (M//i+1) >= M and i<=N and M//i+1:
        ans = min(ans,i*(M//i+1))
        continue
        
if ans == 10**100:
    print(-1)
else:
    print(ans)

    