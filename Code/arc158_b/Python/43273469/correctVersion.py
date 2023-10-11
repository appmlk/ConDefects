from itertools import combinations

N = int(input())
x = list(map(int, input().split()))

x.sort(key=lambda x: 1/x)

inf = float('inf')
mx, mn = -inf, inf

if N >=6:
    x = x[:3] + x[-3:]

for xi, xj, xk in combinations(x, 3):
    mx = max(mx, (xi+xj+xk)/(xi*xj*xk))
    mn = min(mn, (xi+xj+xk)/(xi*xj*xk))

print(mn, mx)