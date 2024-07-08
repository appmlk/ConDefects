n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))

c = [(i, 0) for i in a] + [(i, 1) for i in b]
c.sort()

ans = "No"
for i in range(n + m - 1):
    if c[i][1] == c[i + 1][1] == 0:
        ans = "Yes"

print(ans)
