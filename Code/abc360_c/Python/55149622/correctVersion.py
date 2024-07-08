N = int(input())
A = list(map(int,input().split()))
W = list(map(int,input().split()))
flg = [-1]*(N)
cost = 0
for i in range(N):
  if flg[A[i]-1] == -1:
    flg[A[i]-1] = W[i]
    continue
  cost += min(flg[A[i]-1],W[i])
  flg[A[i]-1] = max(flg[A[i]-1],W[i])
print(cost)