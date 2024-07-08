mods = {0: 6, 1: 2, 2: 4, 3: 8}

t = int(input())
for i in range(t):
    n, m, k = map(int, input().split())
    if n < k:
        ans = mods[n % 4]
    else:
        ans = mods[(k + (n - k) % (m - k)) % 4]
    print(ans)