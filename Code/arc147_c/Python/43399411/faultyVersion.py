N = int(input())
T = [list(map(int,input().split())) for _ in range(N)]

L = [t[0] for t in T]
R = [t[1] for t in T]

L.sort(reverse=True)
R.sort()

ans = 0
for i in range(N):
    if L[i] > R[i]:
        ans += (L[i]-R[i])*(N-1)
print(ans)