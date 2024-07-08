n = int(input())

x = 1
ans = x

while x**3 <= n:
  s = str(x**3)
  l = len(s)
  kai = True
  for i in range(l//2):
    # print(s, i, s[i], s[l-1])
    if s[i] != s[l-i-1]:
      kai = False
      break
  if kai:
    ans = s
  x += 1
print(ans)