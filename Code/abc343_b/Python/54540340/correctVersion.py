N = int(input())
A = [list(map(int, input().split())) for _ in range(N)]
for i in range(N):
    ans = []
    for j in range(N):
        if A[i][j] == 1:
            ans.append(j+1)
    print(*ans)