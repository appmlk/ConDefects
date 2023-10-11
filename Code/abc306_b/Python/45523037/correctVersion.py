import sys
input = lambda: sys.stdin.readline().strip()

a = list(map(int, input().split()))

ans = 0
for i in range(64):
    if a[i] == 0:
        continue
    else:
        ans += (2 ** i)
print(ans)