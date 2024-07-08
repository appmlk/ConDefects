def calc(d: int) -> int:
    if d == 0:
        return 1
    arr = [2, 4, 8, 6]
    return arr[d % 4 - 1]


t = int(input())
ans = []
for _ in range(t):
    n, m, k = map(int, input().split())
    if n < k:
        ans.append(calc(n))
    elif m - k == 1:
        ans.append(0)
    else:
        d1 = (n - k) % (m - k)
        ans.append(calc(d1 + k))

for ansi in ans:
    print(ansi)
