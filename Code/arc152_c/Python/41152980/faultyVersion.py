from math import gcd

N = int(input())
A = list(map(int, input().split()))
m = A[0]
d = A[-1] - m

g = m
for a in A[1:]:
    g = gcd(g, 2*(a-m))

ans = m%g + d
print(ans)
