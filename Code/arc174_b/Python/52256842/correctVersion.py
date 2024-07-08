T = int(input())
for i in range(T):
  A = list(map(int, input().split()))
  P = list(map(int, input().split()))
  tmp = 0
  for i in range(1, 6):
    tmp += (i-3)*A[i-1]
  if tmp >= 0:
    print(0)
    continue
  x = (-tmp+1)//2
  ans = -tmp*P[3]
  if 2*P[3]-P[4] >= 0:
    ans = min(ans, x*(2*P[3]-P[4])-tmp*(P[4]-P[3]))
  ans = min(ans, -tmp*P[4])
  ans = min(ans, x*P[4])
  print(ans)