N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]
ans = []
for i in range(N):
    for j in range(N):
        if A[i][j] == 1:
            ans.append(j+1)
    print(*ans)