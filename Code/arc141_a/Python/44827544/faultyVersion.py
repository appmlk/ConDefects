T = int(input())

for _ in range(T):
  s = list(input())
  m = len(s)
  ans = -1
  for k in range(1,m//2+1):
    if m%k!=0:
      continue
    mini = s[:k]
    temp = mini*(m//k)
    if temp>s:
      mini = list(str(int("".join(mini))-1))
      temp = mini*(m//k)
    ans = max(ans,int("".join(temp)))
  print(ans)