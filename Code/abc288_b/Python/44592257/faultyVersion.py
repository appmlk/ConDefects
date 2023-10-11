n, k = map(int, input().split())
s = [input() for _ in range(n)]
s.sort()
for i in range(k):
  print(s[i])