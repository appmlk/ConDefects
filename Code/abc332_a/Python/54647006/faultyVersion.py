n,s,k=map(int, input().split())
sum=0
for i in range(n):
  p,q=map(int, input().split())
  sum+=p*q

if s<sum:
  print(sum)
else:
  print(sum+k)