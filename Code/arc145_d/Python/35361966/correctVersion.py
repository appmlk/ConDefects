n, m = map(int, input().split())
d = [1]
while len(d) < n:
  d = d + [sum(d) + 1] + d
d = d[:n - 1]
d += [sum(d) + 1]
for i in range(1, n):
  d[i] += d[i - 1]
sub = (sum(d) - m) // n + 1
for i in range(n):
  d[i] -= sub
d[-1] -= sum(d) - m
print(*d)