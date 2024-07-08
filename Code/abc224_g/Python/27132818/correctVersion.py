def expect(k):
  """expect"""
  global n, s, t, a, b
  p = t - k + 1
  return b * (n / p) + a * (p - 1) / 2


n, s, t, a, b = [int(x) for x in input().split()]

l = 1
r = t
while r - l >= 3:
  d = (r - l) // 3
  p1 = l + d * 1
  p2 = l + d * 2

  e1 = expect(p1)
  e2 = expect(p2)
  if e1 >= e2:
    l = p1
  else:
    r = p2

# 0 <= r-l <=2
mn = l
for i in range(l + 1, r + 1):
  if expect(i) < expect(mn):
    mn = i

ans = expect(mn)
if s <= t:
  ans = min(ans, (t - s) * a)

print(ans)
