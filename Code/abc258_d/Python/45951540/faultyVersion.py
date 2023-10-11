N,X = map(int,input().split())
AB = [list(map(int,input().split()))for _ in range(N)]

ans = 10**18
ruiseki = 0
now=0
for i in range(N):
    ruiseki += sum(AB[i])
    now+=1
    if (X-now)>=0:
        ans = min(ans,ruiseki+(X-now)*AB[i][1])

print(ans)