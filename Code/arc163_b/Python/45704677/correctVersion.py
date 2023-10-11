n, m = map(int, input().split())
a1, a2, *a = list(map(int, input().split()))

a.sort()

ans = 10**20
for i in range(n-2):
    if i+m-1 > n-3:
        break
    pre = max((a1 - a[i]), 0) + max(a[i+m-1] - a2, 0)

    ans = min(ans, pre)

print(ans)



