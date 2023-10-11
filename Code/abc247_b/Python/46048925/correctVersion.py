from collections import defaultdict
n = int(input())
s = [""] * n
t = [""] * n
d = defaultdict(int)
for i in range(n):
  s[i], t[i] = input().split()
  d[s[i]] += 1
  d[t[i]] += 1
for i in range(n):
  if (s[i] == t[i] and d[s[i]] > 2) or (s[i] != t[i] and d[s[i]] >= 2 and d[t[i]] >= 2):
    exit(print("No"))
print("Yes")