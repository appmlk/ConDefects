from more_itertools import*
a,b,c,d=map(int,input().split())
s={*sieve(205)}
ans=0
for i in range(a,b+1):
  t=1
  for j in range(c,d+1):
    if i+j in s:
      t=0
  ans|=t
if ans:
  print('Takahashi')
else:
  print('Aoki')