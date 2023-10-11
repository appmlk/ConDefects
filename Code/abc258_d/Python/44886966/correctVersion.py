n, x = map(int, input().split())
ab = [list(map(int, input().split())) for _ in range(n)]
t = 0
c = 0
mn = 10 ** 19
for i in range(n):
    if c >= x:
        break
    a = ab[i][0]
    b = ab[i][1]

    t += a
    ans = (x - c) * b + t
    mn = min(ans, mn)
    c += 1
    t += b

print(mn)