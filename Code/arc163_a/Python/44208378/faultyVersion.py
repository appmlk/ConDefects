from collections import Counter

t=int(input())

for _ in range(t):
  n=int(input())
  s=list(input())
  c=Counter(s)
  s0=s[0]
  if n==2:
    s1=s[1]
    if s0==s1 or s0>s1:
      print('No')
      continue
    else:
      print('Yes')
      continue
  
  for i in range(1,n):
    ss=s[i]
    if s0<ss or (s0==ss and s0<''.join(s[i:])):
      print('Yes')
      break
  else:
    print('No')
  