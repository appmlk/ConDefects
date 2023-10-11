n = int(input())
sec = [[] for _ in range(n)]
for i in range(n):
    sec[i] = list(map(int, input().split()))

sec.sort()
now = 0
nex = 1
ans = []
while now < n:
    left = sec[now][0]
    right = sec[now][1]
    while nex < n and left <= sec[nex][0] <= right:
        right = sec[nex][1]
        nex += 1
    ans.append([left, right])
    now = nex
    nex += 1

for i in range(len(ans)):
    print(*ans[i])