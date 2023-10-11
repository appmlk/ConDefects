n,m=map(int,input().split())
for _ in range(n):
  a=list(input())
  for i in range(m-1):
    if a[i]=='T' and a[i+1]=='T':
      a[i],a[i+1]='P','T'
  print(''.join(a))
  