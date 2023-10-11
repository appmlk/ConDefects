n = int(input())
s = [list(input()) for _ in range(n)]
ans = 100000
for i in range(10):
  num = str(i)

  slot = [False for _ in range(n)]
  for j in range(10*(n+5)):
    for k in range(n):
      if slot[k] == False and s[k][(j)%10] == num:
        slot[k] = True
        break
    if all(slot):
      ans = min(ans,j)
      break
print(ans)