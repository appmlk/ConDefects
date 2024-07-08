MOD = 998244353
n = int(input())
p = list(map(lambda x: int(x) - 1, input().split()))
s = list(input())
sp = [True for i in range(n)]
ans1 = 1
for i in p:
  sp[i] = False
  if sp[(i + 1) % n]:
    if s[i] == "R":
      ans1 = 0
      break
  else:
    if s[i] == "?":
      ans1 *= 2
      ans1 %= MOD
sp = [True for i in range(n)]
ans2 = 1
for i in p:
  sp[(i + 1) % n] = False
  if sp[i]:
    if s[i] == "L":
      ans2 = 0
      break
  else:
    if s[i] == "?":
      ans2 *= 2
      ans2 %= MOD
print((ans1 + ans2) % MOD)