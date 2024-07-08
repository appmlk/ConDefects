h, w = map(int, input().split())
r, c = map(int, input().split())
ans = 4
if r == 1:
  ans -= 1
if r == h:
  ans -= 1
if w == 1:
  ans -= 1
if w == c:
  ans -= 1
print(ans)