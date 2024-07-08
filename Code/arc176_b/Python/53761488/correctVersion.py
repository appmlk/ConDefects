n=int(input())
for _ in range(n):
  n, m,k = map(int, input().split())
  if m-1==k and n>=m-1:
    print(0)
    continue
  if n >= m:
    sa=n-m
    num=sa//(m-k)+1
    n=n-num*(m-k)
 
  print(pow(2,n,10))