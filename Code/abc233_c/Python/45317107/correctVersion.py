def dfs(pos, seki, n, x, a):
    global ans
    if pos == n:
        if seki == x:
            ans += 1
        return

    for c in a[pos]:
        if seki * c > x:
            continue
        dfs(pos+1, seki*c, n, x, a)

n, x = map(int, input().split())
L = []
a = []

for _ in range(n):
    ball = list(map(int, input().split()))
    L.append(ball[0])
    a.append(ball[1:])

ans = 0
dfs(0, 1, n, x, a)
print(ans)