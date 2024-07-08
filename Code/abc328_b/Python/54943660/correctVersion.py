N = int(input())
D = list(map(int, input().split()))
ans = 0
for i in range(1, N+1):
    m = list(str(i))
    for j in range(1, D[i-1]+1):
        d = list(str(j))
        if len(set(m + d)) == 1:
            ans += 1
print(ans)