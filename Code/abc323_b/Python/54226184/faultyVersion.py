n = int(input())
s = [str(input()) for _ in range(n)]

dic = {}

for i in range(n):
  dic[i] = 0

for i in range(n):
  for k in range(n):
    if i > k and s[i][k] == "o":
      dic[i] += 1
      
dic = sorted(dic.items(), key=lambda x:x[1], reverse=True)

for i in dic:
  print(i[0]+1, end=" ")
