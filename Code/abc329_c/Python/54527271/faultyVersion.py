from collections import defaultdict
N = int(input())
S = list(input())
d = defaultdict(int)
a = 1
for i in range(N-1):
  if S[i]==S[i+1]: a += 1
  else:            a = 1
  d[S[i]] = max(d[S[i]], a)
ans = 0
for i in d.values(): ans += i
print(ans)