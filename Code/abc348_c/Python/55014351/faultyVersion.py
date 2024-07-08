n = int(input())
dic = {}
for _ in range(n):
  a, c = map(int, input().split())
  dic[c] = min(dic.get(c, 10**10), a)
print(max(dic))