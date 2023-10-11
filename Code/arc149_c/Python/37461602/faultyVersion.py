n = int(input())
if n == 3:
  ans = [[5,9,1],[3,7,8],[6,2,4]]
elif n == 4:
  ans = [[15,11,16,12],[13,3,6,9],[14,7,8,1],[4,2,10,5]]
elif n == 5:
  ans = [[1,7,11,13,17],[19,23,25,21,5],[3,9,15,24,10],[6,12,18,2,4],[8,10,14,16,20]]
else:
  seen = [False for i in range(n ** 2 + 1)]
  ans = [0 for i in range(n ** 2)]
  l = []
  for i in range(3,n**2 - 2,6):
    if len(l) == n:
      break
    l.append([i,i+3])
    seen[i] = True
    seen[i+3] = True
  for i in range(n):
    ans[(n**2 + 1) // 2 - i - 1] = l[i][0]
    ans[(n**2 + 1) // 2 - i + n - 1] = l[i][1]
  
  now = 0
  for i in range(1,n**2 + 1,2):
    if now == (n**2 + 1) // 2:
      break
    if not seen[i]:
      ans[now] = i
      now += 1
  now = (n ** 2 + 1) // 2 + n
  for i in range(2,n ** 2 + 1,2):
    if now == n ** 2:
      break
    if not seen[i]:
      ans[now] = i
      now += 1
    
  ans = [[ans[i + j * n] for i in range(n)] for j in range(n)]
ANS = [" ".join(map(str,i)) for i in ans]
print("\n".join(ANS))