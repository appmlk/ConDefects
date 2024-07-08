from math import isqrt
n = int(input())
s = input()
S = [int(s[i]) for i in range(n)]
S.sort(reverse=True)
max_perfect = 10**n
ans = 0
i = 1
while i**2 < max_perfect:
  st = str(i**2)
  j = [int(z) for z in str(i**2)]
  j.sort(reverse=True)
  if len(j) < n:
    j += [0]*(n-len(j))
  if S == j:
    ans += 1
  i+=1
print(ans)