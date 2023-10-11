import sys

input = sys.stdin.readline

n, l, r = map(int, input().split())

out = 0

for i in range(60):
    lo = max(l, 1 << i)
    hi = min(r + 1, 2 * lo)

    if hi >= lo and n & (1 << i):
        out += hi - lo

print(out)

    
